package it.planetgeeks.mclauncher.updater;

import it.planetgeeks.mclauncher.Launcher;
import it.planetgeeks.mclauncher.utils.DirUtils;
import it.planetgeeks.mclauncher.utils.FileUtils;
import java.io.File;
class UpdateThread implements Runnable
{

	@Override
	public void run()
	{
		Updater.startUpdate();
	}
}

public class Updater
{
	public static void startUpdateThread()
	{
		Thread thread = new Thread(new UpdateThread());
		thread.start();
	}

	protected static void startUpdate()
	{
		if (downloadLauncher())
		{
			LauncherUpdater.openLauncher();
		}
		else
		{
	        Launcher.main(new String[] { "start", "ERROR1" });
		}
	}

	private static boolean downloadLauncher()
	{
		File launcher = new File(DirUtils.getLauncherDirectory() + File.separator + "launcher.jar");
		if (launcher.exists())
		{
			launcher.delete();
		}

		return FileUtils.downloadFile(LauncherUpdater.downloadUrl, launcher);
	}
	
	
}
