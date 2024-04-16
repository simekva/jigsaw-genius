/*
package com.gdx.jigsawgenius;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.gdx.jigsawgenius.main;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setTitle("Jigsaw Genius");
		new Lwjgl3Application(new main(), config);
	}
}
*/

/*
package com.gdx.jigsawgenius;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URI;
import java.nio.charset.StandardCharsets;

public class DesktopLauncher {
    public static void main (String[] arg) {
        sendToFirebase();  // Send data to Firebase when the application starts
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setForegroundFPS(60);
        config.setTitle("Jigsaw Genius");
        new Lwjgl3Application(new main(), config);
    }

    public static void sendToFirebase() {
        try {
            // Update this URL with your Firebase project's details
           URI uri = new URI("https://jigsawgame-e855b-default-rtdb.europe-west1.firebasedatabase.app/test.json");

        	// Convert URI to URL
        	URL url = uri.toURL();

            // Data you want to send, as a JSON string
            String data = "{\"message\": \"Hello, Firebase from Desktop!\"}";

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("PUT"); // PUT to overwrite data
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");

            byte[] out = data.getBytes(StandardCharsets.UTF_8);
            connection.getOutputStream().write(out);
            connection.getOutputStream().close();

            // Check response code and message
            System.out.println("Response Code: " + connection.getResponseCode());
            System.out.println("Response Message: " + connection.getResponseMessage());

            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
 */

package com.gdx.jigsawgenius;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

public class DesktopLauncher {
    public static void main (String[] arg) {
        // Send data to Firebase
        FirebaseSender.sendData("Det funker!");

		String message = FirebaseReader.readData();
        System.out.println("Message from Firebase: " + message);

		//Host new session with random pin
		FirebaseHost.sendData();

		//Get session with pin
		String pinMessage = FirebaseJoin.readData("4799");
		System.out.println("Message from Firebase: " + pinMessage);

        // Configure LibGDX application
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setForegroundFPS(60);
        config.setTitle("Jigsaw Genius");

        // Start LibGDX application
        new Lwjgl3Application(new main(), config);
    }
}


