package edu.ou.cs.hci.stages;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.URL;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import edu.ou.cs.hci.resources.*;

public class AboutPanel extends JPanel implements  HyperlinkListener
{
  public AboutPanel()
  {
    JEditorPane info;
    URL url = Resources.getResource("about/about.html");

    try {
      info = new JEditorPane(url);
    } catch (IOException ex) {
      info = new JEditorPane("text/plain", "[loading info failed.]");
    }

    info.setEditable(false);
    info.setPreferredSize(new Dimension(550, 550));
    info.addHyperlinkListener(this);
    add(new JScrollPane(info), BorderLayout.CENTER);
  }

  public void hyperlinkUpdate(HyperlinkEvent e)
  {
    if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED)
    {
      Resources.openURLInSystemBrowser(e.getURL());
    }
  }
}
