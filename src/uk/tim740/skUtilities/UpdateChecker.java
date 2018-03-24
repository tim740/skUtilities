package uk.tim740.skUtilities;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

public class UpdateChecker implements Runnable {

    private static final String UPDATE_URL = "https://raw.githubusercontent.com/tim740/skUtilities/master/latest.ver";
    private static final String RELEASES_URL = "https://github.com/tim740/skUtilities/releases/";
    private static final String DOWNLOAD_URL = RELEASES_URL + "download/v";
    private static final String LATEST_RELEASE = RELEASES_URL + "latest";

    private final skUtilities plugin;

    private final boolean broadcastUpdates;
    private final boolean downloadUpdates;
    private final boolean downloadChangelog;

    UpdateChecker(skUtilities plugin, boolean broadcast, boolean downloadUpdates, boolean downloadChangelog) {
        this.plugin = plugin;
        this.broadcastUpdates = broadcast;
        this.downloadUpdates = downloadUpdates;
        this.downloadChangelog = downloadChangelog;
    }

    @Override
    public void run() {
      System.out.print("broadcast " + broadcastUpdates);
      System.out.print("downloadUpdates" + downloadUpdates);
      System.out.print("downloadChangelog" + downloadChangelog);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new URL(UPDATE_URL).openStream()))) {
            String remoteVersion = reader.readLine();
            if (Objects.equals(plugin.getDescription().getVersion(), remoteVersion)) {
                return;
            }
            skUtilities.prSysI("A new version of v" + remoteVersion + " is out.");
            String versionUrlPrefix = DOWNLOAD_URL + remoteVersion + '/';
            if (Objects.equals(downloadUpdates, true)) {
              if (!new File(String.valueOf(plugin.getDataFolder().toPath().resolve(buildFilePrefix(remoteVersion, '.') + ".jar"))).exists()) {
                downloadUpdate(versionUrlPrefix, buildFilePrefix(remoteVersion, '.'));
              }
            } else {
                prSysU("Download v" + remoteVersion + ": " + LATEST_RELEASE);
                skUtilities.prSysI("Option: 'downloadUpdates' is disabled in the config.");
            }
            if (Objects.equals(downloadChangelog, true)) {
              if (!new File(String.valueOf(plugin.getDataFolder().toPath().resolve(buildFilePrefix(remoteVersion, '_') + "_Changelog.sk"))).exists()) {
                downloadChangelog(versionUrlPrefix, buildFilePrefix(remoteVersion, '_'));
              }
            } else {
                skUtilities.prSysI("View changelog here: " + LATEST_RELEASE);
            }
        } catch (Exception e) {
            skUtilities.prSysE("Failed to get latest version number!", "Main", e);
        }
    }

    private void downloadUpdate(String urlPrefix, String filePrefix) {
        String pluginFileName = filePrefix + ".jar";
        Path pluginJar = plugin.getDataFolder().toPath().resolve(pluginFileName);
        if (Files.notExists(pluginJar)) {
            skUtilities.prSysI("Starting download of " + pluginJar);
            skUtilities.downloadFile(pluginJar, urlPrefix + pluginFileName);
            skUtilities.prSysI("Finished download of " + pluginJar);
        }
    }

    private void downloadChangelog(String urlPrefix, String filePrefix) {
        String changelogFileName = filePrefix + "_Changelog.sk";
        Path changelogFile = plugin.getDataFolder().toPath().resolve(changelogFileName);
        if (Files.notExists(changelogFile)) {
            skUtilities.downloadFile(changelogFile, urlPrefix + changelogFileName);
        }
        skUtilities.prSysI("View changelog for '" + changelogFile);
    }

    private String buildFilePrefix(String remoteVersion, char versionSeparator) {
        return plugin.getName() + versionSeparator + 'v' + remoteVersion;
    }

    private void prSysU(String s) {
        if (broadcastUpdates) {
            String message = ChatColor.AQUA + "[" + plugin.getName() + ": Update] " + ChatColor.GRAY + s;
            Bukkit.broadcast(message, plugin.getName() + ".update");
        }
        skUtilities.prSysI(s);
    }
}
