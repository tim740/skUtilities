package uk.tim740.skUtilities;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class UpdateChecker implements Runnable {

    private static final String UPDATE_URL = "https://raw.githubusercontent.com/tim740/skUtilities/master/latest.ver";
    private static final String RELEASES_URL = "https://github.com/tim740/skUtilities/releases/";
    private static final String DOWNLOAD_URL = RELEASES_URL + "download/v";
    private static final String LATEST_RELEASE = RELEASES_URL + "latest";

    private final skUtilities plugin;

    private final boolean broadcastUpdates;
    private final boolean downloadUpdates;
    private final boolean downloadChangelog;

    public UpdateChecker(skUtilities plugin, boolean broadcast, boolean downloadUpdates, boolean downloadChangelog) {
        this.plugin = plugin;

        this.broadcastUpdates = broadcast;
        this.downloadUpdates = downloadUpdates;
        this.downloadChangelog = downloadChangelog;
    }

    @Override
    public void run() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new URL(UPDATE_URL).openStream()))) {
            String remoteVersion = reader.readLine();
            if (Objects.equals(plugin.getDescription().getVersion(), remoteVersion)) {
                // we are already up to date
                return;
            }

            // notify about the new version
            skUtilities.prSysI("A new version of v" + remoteVersion + " is out.");

            String versionUrlPrefix = DOWNLOAD_URL + remoteVersion + '/';
            if (downloadUpdates) {
                downloadUpdate(versionUrlPrefix, buildFilePrefix(remoteVersion, '.'));
            } else {
                prSysU("Download v" + remoteVersion + ": " + LATEST_RELEASE);
                skUtilities.prSysI("Option: 'downloadUpdates' is disabled in the config.");
            }

            // check for the changelog downloads outside of downloadUpdates in case the user only
            // wants to see the changelog
            if (downloadChangelog) {
                downloadChangelog(versionUrlPrefix, buildFilePrefix(remoteVersion, '_'));
            } else {
                skUtilities.prSysI("View changelog here: " + LATEST_RELEASE);
            }
        } catch (Exception e) {
            skUtilities.prSysE("Failed to get latest version number, you might be offline!", "Main", e);
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
            //broadcast to all players that are allowed to see the message
            String message = ChatColor.AQUA + "[" + plugin.getName() + ": Update] " + ChatColor.GRAY + s;
            Bukkit.broadcast(message, plugin.getName() + ".update");
        }

        skUtilities.prSysI(s);
    }
}
