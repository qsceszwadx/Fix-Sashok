package net.launcher.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.PrintStream;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.Timer;

//import net.minecraft.Launcher;
import net.launcher.run.Settings;
import net.launcher.run.Starter;
import net.launcher.utils.BaseUtils;
import net.launcher.utils.EncodingUtils;
import net.launcher.utils.GuardUtils;
import net.launcher.utils.ThreadUtils;

public class Game extends JFrame
{
	private static final long serialVersionUID = 1L;
	//public static Launcher mcapplet;
	
	public Game(final String answer)
	{
		String user = Frame.main.offline.isSelected() ? Settings.defaultUsername : answer.split("<br>")[1].split("<:>")[0];
		String session = Frame.main.offline.isSelected() ? Settings.defaultSession : EncodingUtils.xorencode(EncodingUtils.inttostr(answer.split("<br>")[1].split("<:>")[1]), Settings.protectionKey);
		
		GuardUtils.checkMods(answer, true);
		
		if(Settings.useModCheckerTimer ) new Timer(30000, new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				GuardUtils.checkMods(answer, false);
			}
		}).start();
		
		try
           {
	          String cps;
	          if (System.getProperty("os.name").toLowerCase().contains("win")) {
	        	cps = ";";
	          } else {
	        	cps=":";
	          }
			  int memory = BaseUtils.getPropertyInt("memory", 512);
	          System.out.println("Running Minecraft");
	          String jarpath = BaseUtils.getMcDir().toString() + File.separator + ThreadUtils.b + File.separator;
	          String minpath = BaseUtils.getMcDir().toString() + File.separator;
	          ArrayList<String> params = new ArrayList<String>();
	          params.add("java");
	          params.add("-Xmx" + memory + "m");
	          params.add("-Dfml.ignoreInvalidMinecraftCertificates=true");
	          params.add("-Dfml.ignorePatchDiscrepancies=true");
	          params.add("-Djava.library.path="+jarpath+"natives");
	          params.add("-cp");
	          params.add(jarpath+"libraries.jar"+cps+jarpath+"Forge.jar"+cps+jarpath+"minecraft.jar");
	          params.add("net.minecraft.launchwrapper.Launch");
	          params.add("--username");
	          params.add(user);
	          params.add("--session");
	          params.add(session);
	          params.add("--version");
	          params.add("1.6.2");
	          params.add("--gameDir");
	          params.add(minpath);
	          params.add("--assetsDir");
	          params.add(minpath+File.separator+"assets");
	          params.add("--tweakClass");
	          params.add("cpw.mods.fml.common.launcher.FMLTweaker");
	          ProcessBuilder pb = new ProcessBuilder(params);
	          pb.start();
	          System.exit(0);
	       } catch (Exception e) {
	          ;
	       }
		/*	addWindowListener(new WindowListener()
			{
				public void windowOpened(WindowEvent e) {}
				public void windowIconified(WindowEvent e) {}
				public void windowDeiconified(WindowEvent e) {}
				public void windowDeactivated(WindowEvent e) {}
				public void windowClosed(WindowEvent e) {}
				public void windowActivated(WindowEvent e) {}
				public void windowClosing(WindowEvent e)
				{
					//mcapplet.stop();
					//mcapplet.destroy();
					System.exit(0);
				}
			});
			
			String bin = BaseUtils.getMcDir().toString() + File.separator + ThreadUtils.b + File.separator;
			setForeground(Color.BLACK);
			setBackground(Color.BLACK);
			URL[] urls = new URL[4];

			urls[0] = new File(bin, net.launcher.utils.ThreadUtils.m).toURI().toURL();
			urls[1] = new File(bin, net.launcher.utils.ThreadUtils.lw).toURI().toURL();
			urls[2] = new File(bin, net.launcher.utils.ThreadUtils.j).toURI().toURL();
			urls[3] = new File(bin, net.launcher.utils.ThreadUtils.lwu).toURI().toURL();
			
			//mcapplet = new Launcher(bin, urls);
			//mcapplet.customParameters.put("username", user);
			//mcapplet.customParameters.put("sessionid", session);
			//mcapplet.customParameters.put("stand-alone", "true");
			if(Settings.useAutoenter && !Frame.main.offline.isSelected())
			{
				//mcapplet.customParameters.put("server", Settings.servers[Frame.main.servers.getSelectedIndex()].split(", ")[1]);
				//mcapplet.customParameters.put("port", Settings.servers[Frame.main.servers.getSelectedIndex()].split(", ")[2]);
			}
			setTitle(Settings.titleInGame);
			if(Frame.main != null)
			{
				Frame.main.setVisible(false);
				setBounds(Frame.main.getBounds());
				setExtendedState(Frame.main.getExtendedState());
				setMinimumSize(Frame.main.getMinimumSize());
			} else
			{
				setSize(850, 480);
				setMinimumSize(new Dimension(850, 480));
			}
			//mcapplet.setForeground(Color.BLACK);
			//mcapplet.setBackground(Color.BLACK);
			setLayout(new BorderLayout());
			//add(mcapplet, BorderLayout.CENTER);
			validate();
			if(BaseUtils.getPropertyBoolean("fullscreen"))
			setExtendedState(JFrame.MAXIMIZED_BOTH);
			setIconImage(BaseUtils.getLocalImage("favicon"));
			setVisible(true);
			
			if(Settings.useConsoleHider)
			{
				System.setErr(new PrintStream(new NulledStream()));
				System.setOut(new PrintStream(new NulledStream()));
			}
			//mcapplet.init();
			//mcapplet.start();
		} catch(Exception e)
		{
			e.printStackTrace();
		}*/
	}
}