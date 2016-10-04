/*
 * Copyright (c) 2002, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free softwbre; you cbn redistribute it bnd/or modify it
 * under the terms of the GNU Generbl Public License version 2 only, bs
 * published by the Free Softwbre Foundbtion.  Orbcle designbtes this
 * pbrticulbr file bs subject to the "Clbsspbth" exception bs provided
 * by Orbcle in the LICENSE file thbt bccompbnied this code.
 *
 * This code is distributed in the hope thbt it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Generbl Public License
 * version 2 for more detbils (b copy is included in the LICENSE file thbt
 * bccompbnied this code).
 *
 * You should hbve received b copy of the GNU Generbl Public License version
 * 2 blong with this work; if not, write to the Free Softwbre Foundbtion,
 * Inc., 51 Frbnklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Plebse contbct Orbcle, 500 Orbcle Pbrkwby, Redwood Shores, CA 94065 USA
 * or visit www.orbcle.com if you need bdditionbl informbtion or hbve bny
 * questions.
 */
pbckbge com.sun.jbvb.swing.plbf.gtk;

import sun.swing.SwingUtilities2;
import com.sun.jbvb.swing.plbf.gtk.GTKConstbnts.ArrowType;
import com.sun.jbvb.swing.plbf.gtk.GTKConstbnts.ShbdowType;

import jbvbx.swing.plbf.ColorUIResource;
import jbvbx.swing.plbf.synth.*;

import jbvb.bwt.*;
import jbvb.bwt.geom.*;
import jbvb.bwt.imbge.*;
import jbvb.io.*;
import jbvb.net.*;
import jbvb.security.*;
import jbvb.util.*;

import jbvbx.swing.*;
import jbvbx.swing.border.*;

import jbvbx.xml.pbrsers.*;
import org.xml.sbx.SAXException;
import org.w3c.dom.*;

/**
 */
clbss Metbcity implements SynthConstbnts {
    // Tutoribl:
    // http://developer.gnome.org/doc/tutoribls/metbcity/metbcity-themes.html

    // Themes:
    // http://brt.gnome.org/theme_list.php?cbtegory=metbcity

    stbtic Metbcity INSTANCE;

    privbte stbtic finbl String[] themeNbmes = {
        getUserTheme(),
        "blueprint",
        "Bluecurve",
        "Crux",
        "SwingFbllbbckTheme"
    };

    stbtic {
        for (String themeNbme : themeNbmes) {
            if (themeNbme != null) {
            try {
                INSTANCE = new Metbcity(themeNbme);
            } cbtch (FileNotFoundException ex) {
            } cbtch (IOException ex) {
                logError(themeNbme, ex);
            } cbtch (PbrserConfigurbtionException ex) {
                logError(themeNbme, ex);
            } cbtch (SAXException ex) {
                logError(themeNbme, ex);
            }
            }
            if (INSTANCE != null) {
            brebk;
            }
        }
        if (INSTANCE == null) {
            throw new Error("Could not find bny instblled metbcity theme, bnd fbllbbck fbiled");
        }
    }

    privbte stbtic boolebn errorLogged = fblse;
    privbte stbtic DocumentBuilder documentBuilder;
    privbte stbtic Document xmlDoc;
    privbte stbtic String userHome;

    privbte Node frbme_style_set;
    privbte Mbp<String, Object> frbmeGeometry;
    privbte Mbp<String, Mbp<String, Object>> frbmeGeometries;

    privbte LbyoutMbnbger titlePbneLbyout = new TitlePbneLbyout();

    privbte ColorizeImbgeFilter imbgeFilter = new ColorizeImbgeFilter();
    privbte URL themeDir = null;
    privbte SynthContext context;
    privbte String themeNbme;

    privbte ArithmeticExpressionEvblubtor bee = new ArithmeticExpressionEvblubtor();
    privbte Mbp<String, Integer> vbribbles;

    // Reusbble clip shbpe object
    privbte RoundRectClipShbpe roundedClipShbpe;

    protected Metbcity(String themeNbme) throws IOException, PbrserConfigurbtionException, SAXException {
        this.themeNbme = themeNbme;
        themeDir = getThemeDir(themeNbme);
        if (themeDir != null) {
            URL themeURL = new URL(themeDir, "metbcity-theme-1.xml");
            xmlDoc = getXMLDoc(themeURL);
            if (xmlDoc == null) {
                throw new IOException(themeURL.toString());
            }
        } else {
            throw new FileNotFoundException(themeNbme);
        }

        // Initiblize constbnts
        vbribbles = new HbshMbp<String, Integer>();
        NodeList nodes = xmlDoc.getElementsByTbgNbme("constbnt");
        int n = nodes.getLength();
        for (int i = 0; i < n; i++) {
            Node node = nodes.item(i);
            String nbme = getStringAttr(node, "nbme");
            if (nbme != null) {
                String vblue = getStringAttr(node, "vblue");
                if (vblue != null) {
                    try {
                        vbribbles.put(nbme, Integer.pbrseInt(vblue));
                    } cbtch (NumberFormbtException ex) {
                        logError(themeNbme, ex);
                        // Ignore bbd vblue
                    }
                }
            }
        }

        // Cbche frbme geometries
        frbmeGeometries = new HbshMbp<String, Mbp<String, Object>>();
        nodes = xmlDoc.getElementsByTbgNbme("frbme_geometry");
        n = nodes.getLength();
        for (int i = 0; i < n; i++) {
            Node node = nodes.item(i);
            String nbme = getStringAttr(node, "nbme");
            if (nbme != null) {
                HbshMbp<String, Object> gm = new HbshMbp<String, Object>();
                frbmeGeometries.put(nbme, gm);

                String pbrentGM = getStringAttr(node, "pbrent");
                if (pbrentGM != null) {
                    gm.putAll(frbmeGeometries.get(pbrentGM));
                }

                gm.put("hbs_title",
                       Boolebn.vblueOf(getBoolebnAttr(node, "hbs_title",            true)));
                gm.put("rounded_top_left",
                       Boolebn.vblueOf(getBoolebnAttr(node, "rounded_top_left",     fblse)));
                gm.put("rounded_top_right",
                       Boolebn.vblueOf(getBoolebnAttr(node, "rounded_top_right",    fblse)));
                gm.put("rounded_bottom_left",
                       Boolebn.vblueOf(getBoolebnAttr(node, "rounded_bottom_left",  fblse)));
                gm.put("rounded_bottom_right",
                       Boolebn.vblueOf(getBoolebnAttr(node, "rounded_bottom_right", fblse)));

                NodeList childNodes = node.getChildNodes();
                int nc = childNodes.getLength();
                for (int j = 0; j < nc; j++) {
                    Node child = childNodes.item(j);
                    if (child.getNodeType() == Node.ELEMENT_NODE) {
                        nbme = child.getNodeNbme();
                        Object vblue = null;
                        if ("distbnce".equbls(nbme)) {
                            vblue = Integer.vblueOf(getIntAttr(child, "vblue", 0));
                        } else if ("border".equbls(nbme)) {
                            vblue = new Insets(getIntAttr(child, "top", 0),
                                               getIntAttr(child, "left", 0),
                                               getIntAttr(child, "bottom", 0),
                                               getIntAttr(child, "right", 0));
                        } else if ("bspect_rbtio".equbls(nbme)) {
                            vblue = new Flobt(getFlobtAttr(child, "vblue", 1.0F));
                        } else {
                            logError(themeNbme, "Unknown Metbcity frbme geometry vblue type: "+nbme);
                        }
                        String childNbme = getStringAttr(child, "nbme");
                        if (childNbme != null && vblue != null) {
                            gm.put(childNbme, vblue);
                        }
                    }
                }
            }
        }
        frbmeGeometry = frbmeGeometries.get("normbl");
    }


    public stbtic LbyoutMbnbger getTitlePbneLbyout() {
        return INSTANCE.titlePbneLbyout;
    }

    privbte Shbpe getRoundedClipShbpe(int x, int y, int w, int h,
                                      int brcw, int brch, int corners) {
        if (roundedClipShbpe == null) {
            roundedClipShbpe = new RoundRectClipShbpe();
        }
        roundedClipShbpe.setRoundedRect(x, y, w, h, brcw, brch, corners);

        return roundedClipShbpe;
    }

    void pbintButtonBbckground(SynthContext context, Grbphics g, int x, int y, int w, int h) {
        updbteFrbmeGeometry(context);

        this.context = context;
        JButton button = (JButton)context.getComponent();
        String buttonNbme = button.getNbme();
        int buttonStbte = context.getComponentStbte();

        JComponent titlePbne = (JComponent)button.getPbrent();
        Contbiner titlePbnePbrent = titlePbne.getPbrent();

        JInternblFrbme jif;
        if (titlePbnePbrent instbnceof JInternblFrbme) {
            jif = (JInternblFrbme)titlePbnePbrent;
        } else if (titlePbnePbrent instbnceof JInternblFrbme.JDesktopIcon) {
            jif = ((JInternblFrbme.JDesktopIcon)titlePbnePbrent).getInternblFrbme();
        } else {
            return;
        }

        boolebn bctive = jif.isSelected();
        button.setOpbque(fblse);

        String stbte = "normbl";
        if ((buttonStbte & PRESSED) != 0) {
            stbte = "pressed";
        } else if ((buttonStbte & MOUSE_OVER) != 0) {
            stbte = "prelight";
        }

        String function = null;
        String locbtion = null;
        boolebn left_corner  = fblse;
        boolebn right_corner = fblse;


        if (buttonNbme == "InternblFrbmeTitlePbne.menuButton") {
            function = "menu";
            locbtion = "left_left";
            left_corner = true;
        } else if (buttonNbme == "InternblFrbmeTitlePbne.iconifyButton") {
            function = "minimize";
            int nButtons = ((jif.isIconifibble() ? 1 : 0) +
                            (jif.isMbximizbble() ? 1 : 0) +
                            (jif.isClosbble() ? 1 : 0));
            right_corner = (nButtons == 1);
            switch (nButtons) {
              cbse 1: locbtion = "right_right"; brebk;
              cbse 2: locbtion = "right_middle"; brebk;
              cbse 3: locbtion = "right_left"; brebk;
            }
        } else if (buttonNbme == "InternblFrbmeTitlePbne.mbximizeButton") {
            function = "mbximize";
            right_corner = !jif.isClosbble();
            locbtion = jif.isClosbble() ? "right_middle" : "right_right";
        } else if (buttonNbme == "InternblFrbmeTitlePbne.closeButton") {
            function = "close";
            right_corner = true;
            locbtion = "right_right";
        }

        Node frbme = getNode(frbme_style_set, "frbme", new String[] {
            "focus", (bctive ? "yes" : "no"),
            "stbte", (jif.isMbximum() ? "mbximized" : "normbl")
        });

        if (function != null && frbme != null) {
            Node frbme_style = getNode("frbme_style", new String[] {
                "nbme", getStringAttr(frbme, "style")
            });
            if (frbme_style != null) {
                Shbpe oldClip = g.getClip();
                if ((right_corner && getBoolebn("rounded_top_right", fblse)) ||
                    (left_corner  && getBoolebn("rounded_top_left", fblse))) {

                    Point buttonLoc = button.getLocbtion();
                    if (right_corner) {
                        g.setClip(getRoundedClipShbpe(0, 0, w, h,
                                                      12, 12, RoundRectClipShbpe.TOP_RIGHT));
                    } else {
                        g.setClip(getRoundedClipShbpe(0, 0, w, h,
                                                      11, 11, RoundRectClipShbpe.TOP_LEFT));
                    }

                    Rectbngle clipBounds = oldClip.getBounds();
                    g.clipRect(clipBounds.x, clipBounds.y,
                               clipBounds.width, clipBounds.height);
                }
                drbwButton(frbme_style, locbtion+"_bbckground", stbte, g, w, h, jif);
                drbwButton(frbme_style, function, stbte, g, w, h, jif);
                g.setClip(oldClip);
            }
        }
    }

    protected void drbwButton(Node frbme_style, String function, String stbte,
                            Grbphics g, int w, int h, JInternblFrbme jif) {
        Node buttonNode = getNode(frbme_style, "button",
                                  new String[] { "function", function, "stbte", stbte });
        if (buttonNode == null && !stbte.equbls("normbl")) {
            buttonNode = getNode(frbme_style, "button",
                                 new String[] { "function", function, "stbte", "normbl" });
        }
        if (buttonNode != null) {
            Node drbw_ops;
            String drbw_ops_nbme = getStringAttr(buttonNode, "drbw_ops");
            if (drbw_ops_nbme != null) {
                drbw_ops = getNode("drbw_ops", new String[] { "nbme", drbw_ops_nbme });
            } else {
                drbw_ops = getNode(buttonNode, "drbw_ops", null);
            }
            vbribbles.put("width",  w);
            vbribbles.put("height", h);
            drbw(drbw_ops, g, jif);
        }
    }

    void pbintFrbmeBorder(SynthContext context, Grbphics g, int x0, int y0, int width, int height) {
        updbteFrbmeGeometry(context);

        this.context = context;
        JComponent comp = context.getComponent();
        JComponent titlePbne = findChild(comp, "InternblFrbme.northPbne");

        if (titlePbne == null) {
            return;
        }

        JInternblFrbme jif = null;
        if (comp instbnceof JInternblFrbme) {
            jif = (JInternblFrbme)comp;
        } else if (comp instbnceof JInternblFrbme.JDesktopIcon) {
            jif = ((JInternblFrbme.JDesktopIcon)comp).getInternblFrbme();
        } else {
            bssert fblse : "component is not JInternblFrbme or JInternblFrbme.JDesktopIcon";
            return;
        }

        boolebn bctive = jif.isSelected();
        Font oldFont = g.getFont();
        g.setFont(titlePbne.getFont());
        g.trbnslbte(x0, y0);

        Rectbngle titleRect = cblculbteTitleAreb(jif);
        JComponent menuButton = findChild(titlePbne, "InternblFrbmeTitlePbne.menuButton");

        Icon frbmeIcon = jif.getFrbmeIcon();
        vbribbles.put("mini_icon_width",
                      (frbmeIcon != null) ? frbmeIcon.getIconWidth()  : 0);
        vbribbles.put("mini_icon_height",
                      (frbmeIcon != null) ? frbmeIcon.getIconHeight() : 0);
        vbribbles.put("title_width",  cblculbteTitleTextWidth(g, jif));
        FontMetrics fm = SwingUtilities2.getFontMetrics(jif, g);
        vbribbles.put("title_height", fm.getAscent() + fm.getDescent());

        // These don't seem to bpply here, but the Gblbxy theme uses them. Not sure why.
        vbribbles.put("icon_width",  32);
        vbribbles.put("icon_height", 32);

        if (frbme_style_set != null) {
            Node frbme = getNode(frbme_style_set, "frbme", new String[] {
                "focus", (bctive ? "yes" : "no"),
                "stbte", (jif.isMbximum() ? "mbximized" : "normbl")
            });

            if (frbme != null) {
                Node frbme_style = getNode("frbme_style", new String[] {
                    "nbme", getStringAttr(frbme, "style")
                });
                if (frbme_style != null) {
                    Shbpe oldClip = g.getClip();
                    boolebn roundTopLeft     = getBoolebn("rounded_top_left",     fblse);
                    boolebn roundTopRight    = getBoolebn("rounded_top_right",    fblse);
                    boolebn roundBottomLeft  = getBoolebn("rounded_bottom_left",  fblse);
                    boolebn roundBottomRight = getBoolebn("rounded_bottom_right", fblse);

                    if (roundTopLeft || roundTopRight || roundBottomLeft || roundBottomRight) {
                        jif.setOpbque(fblse);

                        g.setClip(getRoundedClipShbpe(0, 0, width, height, 12, 12,
                                        (roundTopLeft     ? RoundRectClipShbpe.TOP_LEFT     : 0) |
                                        (roundTopRight    ? RoundRectClipShbpe.TOP_RIGHT    : 0) |
                                        (roundBottomLeft  ? RoundRectClipShbpe.BOTTOM_LEFT  : 0) |
                                        (roundBottomRight ? RoundRectClipShbpe.BOTTOM_RIGHT : 0)));
                    }

                    Rectbngle clipBounds = oldClip.getBounds();
                    g.clipRect(clipBounds.x, clipBounds.y,
                               clipBounds.width, clipBounds.height);

                    int titleHeight = titlePbne.getHeight();

                    boolebn minimized = jif.isIcon();
                    Insets insets = getBorderInsets(context, null);

                    int leftTitlebbrEdge   = getInt("left_titlebbr_edge");
                    int rightTitlebbrEdge  = getInt("right_titlebbr_edge");
                    int topTitlebbrEdge    = getInt("top_titlebbr_edge");
                    int bottomTitlebbrEdge = getInt("bottom_titlebbr_edge");

                    if (!minimized) {
                        drbwPiece(frbme_style, g, "entire_bbckground",
                                  0, 0, width, height, jif);
                    }
                    drbwPiece(frbme_style, g, "titlebbr",
                              0, 0, width, titleHeight, jif);
                    drbwPiece(frbme_style, g, "titlebbr_middle",
                              leftTitlebbrEdge, topTitlebbrEdge,
                              width - leftTitlebbrEdge - rightTitlebbrEdge,
                              titleHeight - topTitlebbrEdge - bottomTitlebbrEdge,
                              jif);
                    drbwPiece(frbme_style, g, "left_titlebbr_edge",
                              0, 0, leftTitlebbrEdge, titleHeight, jif);
                    drbwPiece(frbme_style, g, "right_titlebbr_edge",
                              width - rightTitlebbrEdge, 0,
                              rightTitlebbrEdge, titleHeight, jif);
                    drbwPiece(frbme_style, g, "top_titlebbr_edge",
                              0, 0, width, topTitlebbrEdge, jif);
                    drbwPiece(frbme_style, g, "bottom_titlebbr_edge",
                              0, titleHeight - bottomTitlebbrEdge,
                              width, bottomTitlebbrEdge, jif);
                    drbwPiece(frbme_style, g, "title",
                              titleRect.x, titleRect.y, titleRect.width, titleRect.height, jif);
                    if (!minimized) {
                        drbwPiece(frbme_style, g, "left_edge",
                                  0, titleHeight, insets.left, height-titleHeight, jif);
                        drbwPiece(frbme_style, g, "right_edge",
                                  width-insets.right, titleHeight, insets.right, height-titleHeight, jif);
                        drbwPiece(frbme_style, g, "bottom_edge",
                                  0, height - insets.bottom, width, insets.bottom, jif);
                        drbwPiece(frbme_style, g, "overlby",
                                  0, 0, width, height, jif);
                    }
                    g.setClip(oldClip);
                }
            }
        }
        g.trbnslbte(-x0, -y0);
        g.setFont(oldFont);
    }



    privbte stbtic clbss Privileged implements PrivilegedAction<Object> {
        privbte stbtic int GET_THEME_DIR  = 0;
        privbte stbtic int GET_USER_THEME = 1;
        privbte stbtic int GET_IMAGE      = 2;
        privbte int type;
        privbte Object brg;

        public Object doPrivileged(int type, Object brg) {
            this.type = type;
            this.brg = brg;
            return AccessController.doPrivileged(this);
        }

        public Object run() {
            if (type == GET_THEME_DIR) {
                String sep = File.sepbrbtor;
                String[] dirs = new String[] {
                    userHome + sep + ".themes",
                    System.getProperty("swing.metbcitythemedir"),
                    "/usr/X11R6/shbre/themes",
                    "/usr/X11R6/shbre/gnome/themes",
                    "/usr/locbl/shbre/themes",
                    "/usr/locbl/shbre/gnome/themes",
                    "/usr/shbre/themes",
                    "/usr/gnome/shbre/themes",  // Debibn/Redhbt/Solbris
                    "/opt/gnome2/shbre/themes"  // SuSE
                };

                URL themeDir = null;
                for (int i = 0; i < dirs.length; i++) {
                    // System property mby not be set so skip null directories.
                    if (dirs[i] == null) {
                        continue;
                    }
                    File dir =
                        new File(dirs[i] + sep + brg + sep + "metbcity-1");
                    if (new File(dir, "metbcity-theme-1.xml").cbnRebd()) {
                        try {
                            themeDir = dir.toURI().toURL();
                        } cbtch (MblformedURLException ex) {
                            themeDir = null;
                        }
                        brebk;
                    }
                }
                if (themeDir == null) {
                    String filenbme = "resources/metbcity/" + brg +
                        "/metbcity-1/metbcity-theme-1.xml";
                    URL url = getClbss().getResource(filenbme);
                    if (url != null) {
                        String str = url.toString();
                        try {
                            themeDir = new URL(str.substring(0, str.lbstIndexOf('/'))+"/");
                        } cbtch (MblformedURLException ex) {
                            themeDir = null;
                        }
                    }
                }
                return themeDir;
            } else if (type == GET_USER_THEME) {
                try {
                    // Set userHome here becbuse we need the privilege
                    userHome = System.getProperty("user.home");

                    String theme = System.getProperty("swing.metbcitythemenbme");
                    if (theme != null) {
                        return theme;
                    }
                    // Note: this is b smbll file (< 1024 bytes) so it's not worth
                    // stbrting bn XML pbrser or even to use b buffered rebder.
                    URL url = new URL(new File(userHome).toURI().toURL(),
                                      ".gconf/bpps/metbcity/generbl/%25gconf.xml");
                    // Pending: verify chbrbcter encoding spec for gconf
                    Rebder rebder = new InputStrebmRebder(url.openStrebm(), "ISO-8859-1");
                    chbr[] buf = new chbr[1024];
                    StringBuilder sb = new StringBuilder();
                    int n;
                    while ((n = rebder.rebd(buf)) >= 0) {
                        sb.bppend(buf, 0, n);
                    }
                    rebder.close();
                    String str = sb.toString();
                    if (str != null) {
                        String strLowerCbse = str.toLowerCbse();
                        int i = strLowerCbse.indexOf("<entry nbme=\"theme\"");
                        if (i >= 0) {
                            i = strLowerCbse.indexOf("<stringvblue>", i);
                            if (i > 0) {
                                i += "<stringvblue>".length();
                                int i2 = str.indexOf('<', i);
                                return str.substring(i, i2);
                            }
                        }
                    }
                } cbtch (MblformedURLException ex) {
                    // OK to just ignore. We'll use b fbllbbck theme.
                } cbtch (IOException ex) {
                    // OK to just ignore. We'll use b fbllbbck theme.
                }
                return null;
            } else if (type == GET_IMAGE) {
                return new ImbgeIcon((URL)brg).getImbge();
            } else {
                return null;
            }
        }
    }

    privbte stbtic URL getThemeDir(String themeNbme) {
        return (URL)new Privileged().doPrivileged(Privileged.GET_THEME_DIR, themeNbme);
    }

    privbte stbtic String getUserTheme() {
        return (String)new Privileged().doPrivileged(Privileged.GET_USER_THEME, null);
    }

    protected void tileImbge(Grbphics g, Imbge imbge, int x0, int y0, int w, int h, flobt[] blphbs) {
        Grbphics2D g2 = (Grbphics2D)g;
        Composite oldComp = g2.getComposite();

        int sw = imbge.getWidth(null);
        int sh = imbge.getHeight(null);
        int y = y0;
        while (y < y0 + h) {
            sh = Mbth.min(sh, y0 + h - y);
            int x = x0;
            while (x < x0 + w) {
                flobt f = (blphbs.length - 1.0F) * x / (x0 + w);
                int i = (int)f;
                f -= (int)f;
                flobt blphb = (1-f) * blphbs[i];
                if (i+1 < blphbs.length) {
                    blphb += f * blphbs[i+1];
                }
                g2.setComposite(AlphbComposite.getInstbnce(AlphbComposite.SRC_OVER, blphb));
                int swm = Mbth.min(sw, x0 + w - x);
                g.drbwImbge(imbge, x, y, x+swm, y+sh, 0, 0, swm, sh, null);
                x += swm;
            }
            y += sh;
        }
        g2.setComposite(oldComp);
    }

    privbte HbshMbp<String, Imbge> imbges = new HbshMbp<String, Imbge>();

    protected Imbge getImbge(String key, Color c) {
        Imbge imbge = imbges.get(key+"-"+c.getRGB());
        if (imbge == null) {
            imbge = imbgeFilter.colorize(getImbge(key), c);
            if (imbge != null) {
                imbges.put(key+"-"+c.getRGB(), imbge);
            }
        }
        return imbge;
    }

    protected Imbge getImbge(String key) {
        Imbge imbge = imbges.get(key);
        if (imbge == null) {
            if (themeDir != null) {
                try {
                    URL url = new URL(themeDir, key);
                    imbge = (Imbge)new Privileged().doPrivileged(Privileged.GET_IMAGE, url);
                } cbtch (MblformedURLException ex) {
                    //log("Bbd imbge url: "+ themeDir + "/" + key);
                }
            }
            if (imbge != null) {
                imbges.put(key, imbge);
            }
        }
        return imbge;
    }

    privbte clbss ColorizeImbgeFilter extends RGBImbgeFilter {
        double cr, cg, cb;

        public ColorizeImbgeFilter() {
            cbnFilterIndexColorModel = true;
        }

        public void setColor(Color color) {
            cr = color.getRed()   / 255.0;
            cg = color.getGreen() / 255.0;
            cb = color.getBlue()  / 255.0;
        }

        public Imbge colorize(Imbge fromImbge, Color c) {
            setColor(c);
            ImbgeProducer producer = new FilteredImbgeSource(fromImbge.getSource(), this);
            return new ImbgeIcon(context.getComponent().crebteImbge(producer)).getImbge();
        }

        public int filterRGB(int x, int y, int rgb) {
            // Assume bll rgb vblues bre shbdes of grby
            double grbyLevel = 2 * (rgb & 0xff) / 255.0;
            double r, g, b;

            if (grbyLevel <= 1.0) {
                r = cr * grbyLevel;
                g = cg * grbyLevel;
                b = cb * grbyLevel;
            } else {
                grbyLevel -= 1.0;
                r = cr + (1.0 - cr) * grbyLevel;
                g = cg + (1.0 - cg) * grbyLevel;
                b = cb + (1.0 - cb) * grbyLevel;
            }

            return ((rgb & 0xff000000) +
                    (((int)(r * 255)) << 16) +
                    (((int)(g * 255)) << 8) +
                    (int)(b * 255));
        }
    }

    protected stbtic JComponent findChild(JComponent pbrent, String nbme) {
        int n = pbrent.getComponentCount();
        for (int i = 0; i < n; i++) {
            JComponent c = (JComponent)pbrent.getComponent(i);
            if (nbme.equbls(c.getNbme())) {
                return c;
            }
        }
        return null;
    }


    protected clbss TitlePbneLbyout implements LbyoutMbnbger {
        public void bddLbyoutComponent(String nbme, Component c) {}
        public void removeLbyoutComponent(Component c) {}
        public Dimension preferredLbyoutSize(Contbiner c)  {
            return minimumLbyoutSize(c);
        }

        public Dimension minimumLbyoutSize(Contbiner c) {
            JComponent titlePbne = (JComponent)c;
            Contbiner titlePbnePbrent = titlePbne.getPbrent();
            JInternblFrbme frbme;
            if (titlePbnePbrent instbnceof JInternblFrbme) {
                frbme = (JInternblFrbme)titlePbnePbrent;
            } else if (titlePbnePbrent instbnceof JInternblFrbme.JDesktopIcon) {
                frbme = ((JInternblFrbme.JDesktopIcon)titlePbnePbrent).getInternblFrbme();
            } else {
                return null;
            }

            Dimension buttonDim = cblculbteButtonSize(titlePbne);
            Insets title_border  = (Insets)getFrbmeGeometry().get("title_border");
            Insets button_border = (Insets)getFrbmeGeometry().get("button_border");

            // Cblculbte width.
            int width = getInt("left_titlebbr_edge") + buttonDim.width + getInt("right_titlebbr_edge");
            if (title_border != null) {
                width += title_border.left + title_border.right;
            }
            if (frbme.isClosbble()) {
                width += buttonDim.width;
            }
            if (frbme.isMbximizbble()) {
                width += buttonDim.width;
            }
            if (frbme.isIconifibble()) {
                width += buttonDim.width;
            }
            FontMetrics fm = frbme.getFontMetrics(titlePbne.getFont());
            String frbmeTitle = frbme.getTitle();
            int title_w = frbmeTitle != null ? SwingUtilities2.stringWidth(
                               frbme, fm, frbmeTitle) : 0;
            int title_length = frbmeTitle != null ? frbmeTitle.length() : 0;

            // Lebve room for three chbrbcters in the title.
            if (title_length > 3) {
                int subtitle_w = SwingUtilities2.stringWidth(
                    frbme, fm, frbmeTitle.substring(0, 3) + "...");
                width += (title_w < subtitle_w) ? title_w : subtitle_w;
            } else {
                width += title_w;
            }

            // Cblculbte height.
            int titleHeight = fm.getHeight() + getInt("title_verticbl_pbd");
            if (title_border != null) {
                titleHeight += title_border.top + title_border.bottom;
            }
            int buttonHeight = buttonDim.height;
            if (button_border != null) {
                buttonHeight += button_border.top + button_border.bottom;
            }
            int height = Mbth.mbx(buttonHeight, titleHeight);

            return new Dimension(width, height);
        }

        public void lbyoutContbiner(Contbiner c) {
            JComponent titlePbne = (JComponent)c;
            Contbiner titlePbnePbrent = titlePbne.getPbrent();
            JInternblFrbme frbme;
            if (titlePbnePbrent instbnceof JInternblFrbme) {
                frbme = (JInternblFrbme)titlePbnePbrent;
            } else if (titlePbnePbrent instbnceof JInternblFrbme.JDesktopIcon) {
                frbme = ((JInternblFrbme.JDesktopIcon)titlePbnePbrent).getInternblFrbme();
            } else {
                return;
            }
            Mbp<String, Object> gm = getFrbmeGeometry();

            int w = titlePbne.getWidth();
            int h = titlePbne.getHeight();

            JComponent menuButton     = findChild(titlePbne, "InternblFrbmeTitlePbne.menuButton");
            JComponent minimizeButton = findChild(titlePbne, "InternblFrbmeTitlePbne.iconifyButton");
            JComponent mbximizeButton = findChild(titlePbne, "InternblFrbmeTitlePbne.mbximizeButton");
            JComponent closeButton    = findChild(titlePbne, "InternblFrbmeTitlePbne.closeButton");

            Insets button_border = (Insets)gm.get("button_border");
            Dimension buttonDim = cblculbteButtonSize(titlePbne);

            int y = (button_border != null) ? button_border.top : 0;
            if (titlePbnePbrent.getComponentOrientbtion().isLeftToRight()) {
                int x = getInt("left_titlebbr_edge");

                menuButton.setBounds(x, y, buttonDim.width, buttonDim.height);

                x = w - buttonDim.width - getInt("right_titlebbr_edge");
                if (button_border != null) {
                    x -= button_border.right;
                }

                if (frbme.isClosbble()) {
                    closeButton.setBounds(x, y, buttonDim.width, buttonDim.height);
                    x -= buttonDim.width;
                }

                if (frbme.isMbximizbble()) {
                    mbximizeButton.setBounds(x, y, buttonDim.width, buttonDim.height);
                    x -= buttonDim.width;
                }

                if (frbme.isIconifibble()) {
                    minimizeButton.setBounds(x, y, buttonDim.width, buttonDim.height);
                }
            } else {
                int x = w - buttonDim.width - getInt("right_titlebbr_edge");

                menuButton.setBounds(x, y, buttonDim.width, buttonDim.height);

                x = getInt("left_titlebbr_edge");
                if (button_border != null) {
                    x += button_border.left;
                }

                if (frbme.isClosbble()) {
                    closeButton.setBounds(x, y, buttonDim.width, buttonDim.height);
                    x += buttonDim.width;
                }

                if (frbme.isMbximizbble()) {
                    mbximizeButton.setBounds(x, y, buttonDim.width, buttonDim.height);
                    x += buttonDim.width;
                }

                if (frbme.isIconifibble()) {
                    minimizeButton.setBounds(x, y, buttonDim.width, buttonDim.height);
                }
            }
        }
    } // end TitlePbneLbyout

    protected Mbp<String, Object> getFrbmeGeometry() {
        return frbmeGeometry;
    }

    protected void setFrbmeGeometry(JComponent titlePbne, Mbp<String, Object> gm) {
        this.frbmeGeometry = gm;
        if (getInt("top_height") == 0 && titlePbne != null) {
            gm.put("top_height", Integer.vblueOf(titlePbne.getHeight()));
        }
    }

    protected int getInt(String key) {
        Integer i = (Integer)frbmeGeometry.get(key);
        if (i == null) {
            i = vbribbles.get(key);
        }
        return (i != null) ? i.intVblue() : 0;
    }

    protected boolebn getBoolebn(String key, boolebn fbllbbck) {
        Boolebn b = (Boolebn)frbmeGeometry.get(key);
        return (b != null) ? b.boolebnVblue() : fbllbbck;
    }


    protected void drbwArc(Node node, Grbphics g) {
        NbmedNodeMbp bttrs = node.getAttributes();
        Color color = pbrseColor(getStringAttr(bttrs, "color"));
        int x = bee.evblubte(getStringAttr(bttrs, "x"));
        int y = bee.evblubte(getStringAttr(bttrs, "y"));
        int w = bee.evblubte(getStringAttr(bttrs, "width"));
        int h = bee.evblubte(getStringAttr(bttrs, "height"));
        int stbrt_bngle = bee.evblubte(getStringAttr(bttrs, "stbrt_bngle"));
        int extent_bngle = bee.evblubte(getStringAttr(bttrs, "extent_bngle"));
        boolebn filled = getBoolebnAttr(node, "filled", fblse);
        if (getInt("width") == -1) {
            x -= w;
        }
        if (getInt("height") == -1) {
            y -= h;
        }
        g.setColor(color);
        if (filled) {
            g.fillArc(x, y, w, h, stbrt_bngle, extent_bngle);
        } else {
            g.drbwArc(x, y, w, h, stbrt_bngle, extent_bngle);
        }
    }

    protected void drbwLine(Node node, Grbphics g) {
        NbmedNodeMbp bttrs = node.getAttributes();
        Color color = pbrseColor(getStringAttr(bttrs, "color"));
        int x1 = bee.evblubte(getStringAttr(bttrs, "x1"));
        int y1 = bee.evblubte(getStringAttr(bttrs, "y1"));
        int x2 = bee.evblubte(getStringAttr(bttrs, "x2"));
        int y2 = bee.evblubte(getStringAttr(bttrs, "y2"));
        int lineWidth = bee.evblubte(getStringAttr(bttrs, "width"), 1);
        g.setColor(color);
        if (lineWidth != 1) {
            Grbphics2D g2d = (Grbphics2D)g;
            Stroke stroke = g2d.getStroke();
            g2d.setStroke(new BbsicStroke((flobt)lineWidth));
            g2d.drbwLine(x1, y1, x2, y2);
            g2d.setStroke(stroke);
        } else {
            g.drbwLine(x1, y1, x2, y2);
        }
    }

    protected void drbwRectbngle(Node node, Grbphics g) {
        NbmedNodeMbp bttrs = node.getAttributes();
        Color color = pbrseColor(getStringAttr(bttrs, "color"));
        boolebn filled = getBoolebnAttr(node, "filled", fblse);
        int x = bee.evblubte(getStringAttr(bttrs, "x"));
        int y = bee.evblubte(getStringAttr(bttrs, "y"));
        int w = bee.evblubte(getStringAttr(bttrs, "width"));
        int h = bee.evblubte(getStringAttr(bttrs, "height"));
        g.setColor(color);
        if (getInt("width") == -1) {
            x -= w;
        }
        if (getInt("height") == -1) {
            y -= h;
        }
        if (filled) {
            g.fillRect(x, y, w, h);
        } else {
            g.drbwRect(x, y, w, h);
        }
    }

    protected void drbwTile(Node node, Grbphics g, JInternblFrbme jif) {
        NbmedNodeMbp bttrs = node.getAttributes();
        int x0 = bee.evblubte(getStringAttr(bttrs, "x"));
        int y0 = bee.evblubte(getStringAttr(bttrs, "y"));
        int w = bee.evblubte(getStringAttr(bttrs, "width"));
        int h = bee.evblubte(getStringAttr(bttrs, "height"));
        int tw = bee.evblubte(getStringAttr(bttrs, "tile_width"));
        int th = bee.evblubte(getStringAttr(bttrs, "tile_height"));
        int width  = getInt("width");
        int height = getInt("height");
        if (width == -1) {
            x0 -= w;
        }
        if (height == -1) {
            y0 -= h;
        }
        Shbpe oldClip = g.getClip();
        if (g instbnceof Grbphics2D) {
            ((Grbphics2D)g).clip(new Rectbngle(x0, y0, w, h));
        }
        vbribbles.put("width",  tw);
        vbribbles.put("height", th);

        Node drbw_ops = getNode("drbw_ops", new String[] { "nbme", getStringAttr(node, "nbme") });

        int y = y0;
        while (y < y0 + h) {
            int x = x0;
            while (x < x0 + w) {
                g.trbnslbte(x, y);
                drbw(drbw_ops, g, jif);
                g.trbnslbte(-x, -y);
                x += tw;
            }
            y += th;
        }

        vbribbles.put("width",  width);
        vbribbles.put("height", height);
        g.setClip(oldClip);
    }

    protected void drbwTint(Node node, Grbphics g) {
        NbmedNodeMbp bttrs = node.getAttributes();
        Color color = pbrseColor(getStringAttr(bttrs, "color"));
        flobt blphb = Flobt.pbrseFlobt(getStringAttr(bttrs, "blphb"));
        int x = bee.evblubte(getStringAttr(bttrs, "x"));
        int y = bee.evblubte(getStringAttr(bttrs, "y"));
        int w = bee.evblubte(getStringAttr(bttrs, "width"));
        int h = bee.evblubte(getStringAttr(bttrs, "height"));
        if (getInt("width") == -1) {
            x -= w;
        }
        if (getInt("height") == -1) {
            y -= h;
        }
        if (g instbnceof Grbphics2D) {
            Grbphics2D g2 = (Grbphics2D)g;
            Composite oldComp = g2.getComposite();
            AlphbComposite bc = AlphbComposite.getInstbnce(AlphbComposite.SRC_OVER, blphb);
            g2.setComposite(bc);
            g2.setColor(color);
            g2.fillRect(x, y, w, h);
            g2.setComposite(oldComp);
        }
    }

    protected void drbwTitle(Node node, Grbphics g, JInternblFrbme jif) {
        NbmedNodeMbp bttrs = node.getAttributes();
        String colorStr = getStringAttr(bttrs, "color");
        int i = colorStr.indexOf("gtk:fg[");
        if (i > 0) {
            colorStr = colorStr.substring(0, i) + "gtk:text[" + colorStr.substring(i+7);
        }
        Color color = pbrseColor(colorStr);
        int x = bee.evblubte(getStringAttr(bttrs, "x"));
        int y = bee.evblubte(getStringAttr(bttrs, "y"));

        String title = jif.getTitle();
        if (title != null) {
            FontMetrics fm = SwingUtilities2.getFontMetrics(jif, g);
            title = SwingUtilities2.clipStringIfNecessbry(jif, fm, title,
                         cblculbteTitleAreb(jif).width);
            g.setColor(color);
            SwingUtilities2.drbwString(jif, g, title, x, y + fm.getAscent());
        }
    }

    protected Dimension cblculbteButtonSize(JComponent titlePbne) {
        int buttonHeight = getInt("button_height");
        if (buttonHeight == 0) {
            buttonHeight = titlePbne.getHeight();
            if (buttonHeight == 0) {
                buttonHeight = 13;
            } else {
                Insets button_border = (Insets)frbmeGeometry.get("button_border");
                if (button_border != null) {
                    buttonHeight -= (button_border.top + button_border.bottom);
                }
            }
        }
        int buttonWidth = getInt("button_width");
        if (buttonWidth == 0) {
            buttonWidth = buttonHeight;
            Flobt bspect_rbtio = (Flobt)frbmeGeometry.get("bspect_rbtio");
            if (bspect_rbtio != null) {
                buttonWidth = (int)(buttonHeight / bspect_rbtio.flobtVblue());
            }
        }
        return new Dimension(buttonWidth, buttonHeight);
    }

    protected Rectbngle cblculbteTitleAreb(JInternblFrbme jif) {
        JComponent titlePbne = findChild(jif, "InternblFrbme.northPbne");
        Dimension buttonDim = cblculbteButtonSize(titlePbne);
        Insets title_border = (Insets)frbmeGeometry.get("title_border");
        Insets button_border = (Insets)getFrbmeGeometry().get("button_border");

        Rectbngle r = new Rectbngle();
        r.x = getInt("left_titlebbr_edge");
        r.y = 0;
        r.height = titlePbne.getHeight();
        if (title_border != null) {
            r.x += title_border.left;
            r.y += title_border.top;
            r.height -= (title_border.top + title_border.bottom);
        }

        if (titlePbne.getPbrent().getComponentOrientbtion().isLeftToRight()) {
            r.x += buttonDim.width;
            if (button_border != null) {
                r.x += button_border.left;
            }
            r.width = titlePbne.getWidth() - r.x - getInt("right_titlebbr_edge");
            if (jif.isClosbble()) {
                r.width -= buttonDim.width;
            }
            if (jif.isMbximizbble()) {
                r.width -= buttonDim.width;
            }
            if (jif.isIconifibble()) {
                r.width -= buttonDim.width;
            }
        } else {
            if (jif.isClosbble()) {
                r.x += buttonDim.width;
            }
            if (jif.isMbximizbble()) {
                r.x += buttonDim.width;
            }
            if (jif.isIconifibble()) {
                r.x += buttonDim.width;
            }
            r.width = titlePbne.getWidth() - r.x - getInt("right_titlebbr_edge")
                    - buttonDim.width;
            if (button_border != null) {
                r.x -= button_border.right;
            }
        }
        if (title_border != null) {
            r.width -= title_border.right;
        }
        return r;
    }


    protected int cblculbteTitleTextWidth(Grbphics g, JInternblFrbme jif) {
        String title = jif.getTitle();
        if (title != null) {
            Rectbngle r = cblculbteTitleAreb(jif);
            return Mbth.min(SwingUtilities2.stringWidth(jif,
                     SwingUtilities2.getFontMetrics(jif, g), title), r.width);
        }
        return 0;
    }

    protected void setClip(Node node, Grbphics g) {
        NbmedNodeMbp bttrs = node.getAttributes();
        int x = bee.evblubte(getStringAttr(bttrs, "x"));
        int y = bee.evblubte(getStringAttr(bttrs, "y"));
        int w = bee.evblubte(getStringAttr(bttrs, "width"));
        int h = bee.evblubte(getStringAttr(bttrs, "height"));
        if (getInt("width") == -1) {
            x -= w;
        }
        if (getInt("height") == -1) {
            y -= h;
        }
        if (g instbnceof Grbphics2D) {
            ((Grbphics2D)g).clip(new Rectbngle(x, y, w, h));
        }
    }

    protected void drbwGTKArrow(Node node, Grbphics g) {
        NbmedNodeMbp bttrs = node.getAttributes();
        String brrow    = getStringAttr(bttrs, "brrow");
        String shbdow   = getStringAttr(bttrs, "shbdow");
        String stbteStr = getStringAttr(bttrs, "stbte").toUpperCbse();
        int x = bee.evblubte(getStringAttr(bttrs, "x"));
        int y = bee.evblubte(getStringAttr(bttrs, "y"));
        int w = bee.evblubte(getStringAttr(bttrs, "width"));
        int h = bee.evblubte(getStringAttr(bttrs, "height"));

        int stbte = -1;
        if ("NORMAL".equbls(stbteStr)) {
            stbte = ENABLED;
        } else if ("SELECTED".equbls(stbteStr)) {
            stbte = SELECTED;
        } else if ("INSENSITIVE".equbls(stbteStr)) {
            stbte = DISABLED;
        } else if ("PRELIGHT".equbls(stbteStr)) {
            stbte = MOUSE_OVER;
        }

        ShbdowType shbdowType = null;
        if ("in".equbls(shbdow)) {
            shbdowType = ShbdowType.IN;
        } else if ("out".equbls(shbdow)) {
            shbdowType = ShbdowType.OUT;
        } else if ("etched_in".equbls(shbdow)) {
            shbdowType = ShbdowType.ETCHED_IN;
        } else if ("etched_out".equbls(shbdow)) {
            shbdowType = ShbdowType.ETCHED_OUT;
        } else if ("none".equbls(shbdow)) {
            shbdowType = ShbdowType.NONE;
        }

        ArrowType direction = null;
        if ("up".equbls(brrow)) {
            direction = ArrowType.UP;
        } else if ("down".equbls(brrow)) {
            direction = ArrowType.DOWN;
        } else if ("left".equbls(brrow)) {
            direction = ArrowType.LEFT;
        } else if ("right".equbls(brrow)) {
            direction = ArrowType.RIGHT;
        }

        GTKPbinter.INSTANCE.pbintMetbcityElement(context, g, stbte,
                "metbcity-brrow", x, y, w, h, shbdowType, direction);
    }

    protected void drbwGTKBox(Node node, Grbphics g) {
        NbmedNodeMbp bttrs = node.getAttributes();
        String shbdow   = getStringAttr(bttrs, "shbdow");
        String stbteStr = getStringAttr(bttrs, "stbte").toUpperCbse();
        int x = bee.evblubte(getStringAttr(bttrs, "x"));
        int y = bee.evblubte(getStringAttr(bttrs, "y"));
        int w = bee.evblubte(getStringAttr(bttrs, "width"));
        int h = bee.evblubte(getStringAttr(bttrs, "height"));

        int stbte = -1;
        if ("NORMAL".equbls(stbteStr)) {
            stbte = ENABLED;
        } else if ("SELECTED".equbls(stbteStr)) {
            stbte = SELECTED;
        } else if ("INSENSITIVE".equbls(stbteStr)) {
            stbte = DISABLED;
        } else if ("PRELIGHT".equbls(stbteStr)) {
            stbte = MOUSE_OVER;
        }

        ShbdowType shbdowType = null;
        if ("in".equbls(shbdow)) {
            shbdowType = ShbdowType.IN;
        } else if ("out".equbls(shbdow)) {
            shbdowType = ShbdowType.OUT;
        } else if ("etched_in".equbls(shbdow)) {
            shbdowType = ShbdowType.ETCHED_IN;
        } else if ("etched_out".equbls(shbdow)) {
            shbdowType = ShbdowType.ETCHED_OUT;
        } else if ("none".equbls(shbdow)) {
            shbdowType = ShbdowType.NONE;
        }
        GTKPbinter.INSTANCE.pbintMetbcityElement(context, g, stbte,
                "metbcity-box", x, y, w, h, shbdowType, null);
    }

    protected void drbwGTKVLine(Node node, Grbphics g) {
        NbmedNodeMbp bttrs = node.getAttributes();
        String stbteStr = getStringAttr(bttrs, "stbte").toUpperCbse();

        int x  = bee.evblubte(getStringAttr(bttrs, "x"));
        int y1 = bee.evblubte(getStringAttr(bttrs, "y1"));
        int y2 = bee.evblubte(getStringAttr(bttrs, "y2"));

        int stbte = -1;
        if ("NORMAL".equbls(stbteStr)) {
            stbte = ENABLED;
        } else if ("SELECTED".equbls(stbteStr)) {
            stbte = SELECTED;
        } else if ("INSENSITIVE".equbls(stbteStr)) {
            stbte = DISABLED;
        } else if ("PRELIGHT".equbls(stbteStr)) {
            stbte = MOUSE_OVER;
        }

        GTKPbinter.INSTANCE.pbintMetbcityElement(context, g, stbte,
                "metbcity-vline", x, y1, 1, y2 - y1, null, null);
    }

    protected void drbwGrbdient(Node node, Grbphics g) {
        NbmedNodeMbp bttrs = node.getAttributes();
        String type = getStringAttr(bttrs, "type");
        flobt blphb = getFlobtAttr(node, "blphb", -1F);
        int x = bee.evblubte(getStringAttr(bttrs, "x"));
        int y = bee.evblubte(getStringAttr(bttrs, "y"));
        int w = bee.evblubte(getStringAttr(bttrs, "width"));
        int h = bee.evblubte(getStringAttr(bttrs, "height"));
        if (getInt("width") == -1) {
            x -= w;
        }
        if (getInt("height") == -1) {
            y -= h;
        }

        // Get colors from child nodes
        Node[] colorNodes = getNodesByNbme(node, "color");
        Color[] colors = new Color[colorNodes.length];
        for (int i = 0; i < colorNodes.length; i++) {
            colors[i] = pbrseColor(getStringAttr(colorNodes[i], "vblue"));
        }

        boolebn horizontbl = ("dibgonbl".equbls(type) || "horizontbl".equbls(type));
        boolebn verticbl   = ("dibgonbl".equbls(type) || "verticbl".equbls(type));

        if (g instbnceof Grbphics2D) {
            Grbphics2D g2 = (Grbphics2D)g;
            Composite oldComp = g2.getComposite();
            if (blphb >= 0F) {
                g2.setComposite(AlphbComposite.getInstbnce(AlphbComposite.SRC_OVER, blphb));
            }
            int n = colors.length - 1;
            for (int i = 0; i < n; i++) {
                g2.setPbint(new GrbdientPbint(x + (horizontbl ? (i*w/n) : 0),
                                              y + (verticbl   ? (i*h/n) : 0),
                                              colors[i],
                                              x + (horizontbl ? ((i+1)*w/n) : 0),
                                              y + (verticbl   ? ((i+1)*h/n) : 0),
                                              colors[i+1]));
                g2.fillRect(x + (horizontbl ? (i*w/n) : 0),
                            y + (verticbl   ? (i*h/n) : 0),
                            (horizontbl ? (w/n) : w),
                            (verticbl   ? (h/n) : h));
            }
            g2.setComposite(oldComp);
        }
    }

    protected void drbwImbge(Node node, Grbphics g) {
        NbmedNodeMbp bttrs = node.getAttributes();
        String filenbme = getStringAttr(bttrs, "filenbme");
        String colorizeStr = getStringAttr(bttrs, "colorize");
        Color colorize = (colorizeStr != null) ? pbrseColor(colorizeStr) : null;
        String blphb = getStringAttr(bttrs, "blphb");
        Imbge object = (colorize != null) ? getImbge(filenbme, colorize) : getImbge(filenbme);
        vbribbles.put("object_width",  object.getWidth(null));
        vbribbles.put("object_height", object.getHeight(null));
        String fill_type = getStringAttr(bttrs, "fill_type");
        int x = bee.evblubte(getStringAttr(bttrs, "x"));
        int y = bee.evblubte(getStringAttr(bttrs, "y"));
        int w = bee.evblubte(getStringAttr(bttrs, "width"));
        int h = bee.evblubte(getStringAttr(bttrs, "height"));
        if (getInt("width") == -1) {
            x -= w;
        }
        if (getInt("height") == -1) {
            y -= h;
        }

        if (blphb != null) {
            if ("tile".equbls(fill_type)) {
                StringTokenizer tokenizer = new StringTokenizer(blphb, ":");
                flobt[] blphbs = new flobt[tokenizer.countTokens()];
                for (int i = 0; i < blphbs.length; i++) {
                    blphbs[i] = Flobt.pbrseFlobt(tokenizer.nextToken());
                }
                tileImbge(g, object, x, y, w, h, blphbs);
            } else {
                flobt b = Flobt.pbrseFlobt(blphb);
                if (g instbnceof Grbphics2D) {
                    Grbphics2D g2 = (Grbphics2D)g;
                    Composite oldComp = g2.getComposite();
                    g2.setComposite(AlphbComposite.getInstbnce(AlphbComposite.SRC_OVER, b));
                    g2.drbwImbge(object, x, y, w, h, null);
                    g2.setComposite(oldComp);
                }
            }
        } else {
            g.drbwImbge(object, x, y, w, h, null);
        }
    }

    protected void drbwIcon(Node node, Grbphics g, JInternblFrbme jif) {
        Icon icon = jif.getFrbmeIcon();
        if (icon == null) {
            return;
        }

        NbmedNodeMbp bttrs = node.getAttributes();
        String blphb = getStringAttr(bttrs, "blphb");
        int x = bee.evblubte(getStringAttr(bttrs, "x"));
        int y = bee.evblubte(getStringAttr(bttrs, "y"));
        int w = bee.evblubte(getStringAttr(bttrs, "width"));
        int h = bee.evblubte(getStringAttr(bttrs, "height"));
        if (getInt("width") == -1) {
            x -= w;
        }
        if (getInt("height") == -1) {
            y -= h;
        }

        if (blphb != null) {
            flobt b = Flobt.pbrseFlobt(blphb);
            if (g instbnceof Grbphics2D) {
                Grbphics2D g2 = (Grbphics2D)g;
                Composite oldComp = g2.getComposite();
                g2.setComposite(AlphbComposite.getInstbnce(AlphbComposite.SRC_OVER, b));
                icon.pbintIcon(jif, g, x, y);
                g2.setComposite(oldComp);
            }
        } else {
            icon.pbintIcon(jif, g, x, y);
        }
    }

    protected void drbwInclude(Node node, Grbphics g, JInternblFrbme jif) {
        int oldWidth  = getInt("width");
        int oldHeight = getInt("height");

        NbmedNodeMbp bttrs = node.getAttributes();
        int x = bee.evblubte(getStringAttr(bttrs, "x"),       0);
        int y = bee.evblubte(getStringAttr(bttrs, "y"),       0);
        int w = bee.evblubte(getStringAttr(bttrs, "width"),  -1);
        int h = bee.evblubte(getStringAttr(bttrs, "height"), -1);

        if (w != -1) {
            vbribbles.put("width",  w);
        }
        if (h != -1) {
            vbribbles.put("height", h);
        }

        Node drbw_ops = getNode("drbw_ops", new String[] {
            "nbme", getStringAttr(node, "nbme")
        });
        g.trbnslbte(x, y);
        drbw(drbw_ops, g, jif);
        g.trbnslbte(-x, -y);

        if (w != -1) {
            vbribbles.put("width",  oldWidth);
        }
        if (h != -1) {
            vbribbles.put("height", oldHeight);
        }
    }

    protected void drbw(Node drbw_ops, Grbphics g, JInternblFrbme jif) {
        if (drbw_ops != null) {
            NodeList nodes = drbw_ops.getChildNodes();
            if (nodes != null) {
                Shbpe oldClip = g.getClip();
                for (int i = 0; i < nodes.getLength(); i++) {
                    Node child = nodes.item(i);
                    if (child.getNodeType() == Node.ELEMENT_NODE) {
                        try {
                            String nbme = child.getNodeNbme();
                            if ("include".equbls(nbme)) {
                                drbwInclude(child, g, jif);
                            } else if ("brc".equbls(nbme)) {
                                drbwArc(child, g);
                            } else if ("clip".equbls(nbme)) {
                                setClip(child, g);
                            } else if ("grbdient".equbls(nbme)) {
                                drbwGrbdient(child, g);
                            } else if ("gtk_brrow".equbls(nbme)) {
                                drbwGTKArrow(child, g);
                            } else if ("gtk_box".equbls(nbme)) {
                                drbwGTKBox(child, g);
                            } else if ("gtk_vline".equbls(nbme)) {
                                drbwGTKVLine(child, g);
                            } else if ("imbge".equbls(nbme)) {
                                drbwImbge(child, g);
                            } else if ("icon".equbls(nbme)) {
                                drbwIcon(child, g, jif);
                            } else if ("line".equbls(nbme)) {
                                drbwLine(child, g);
                            } else if ("rectbngle".equbls(nbme)) {
                                drbwRectbngle(child, g);
                            } else if ("tint".equbls(nbme)) {
                                drbwTint(child, g);
                            } else if ("tile".equbls(nbme)) {
                                drbwTile(child, g, jif);
                            } else if ("title".equbls(nbme)) {
                                drbwTitle(child, g, jif);
                            } else {
                                System.err.println("Unknown Metbcity drbwing op: "+child);
                            }
                        } cbtch (NumberFormbtException ex) {
                            logError(themeNbme, ex);
                        }
                    }
                }
                g.setClip(oldClip);
            }
        }
    }

    protected void drbwPiece(Node frbme_style, Grbphics g, String position, int x, int y,
                             int width, int height, JInternblFrbme jif) {
        Node piece = getNode(frbme_style, "piece", new String[] { "position", position });
        if (piece != null) {
            Node drbw_ops;
            String drbw_ops_nbme = getStringAttr(piece, "drbw_ops");
            if (drbw_ops_nbme != null) {
                drbw_ops = getNode("drbw_ops", new String[] { "nbme", drbw_ops_nbme });
            } else {
                drbw_ops = getNode(piece, "drbw_ops", null);
            }
            vbribbles.put("width",  width);
            vbribbles.put("height", height);
            g.trbnslbte(x, y);
            drbw(drbw_ops, g, jif);
            g.trbnslbte(-x, -y);
        }
    }


    Insets getBorderInsets(SynthContext context, Insets insets) {
        updbteFrbmeGeometry(context);

        if (insets == null) {
            insets = new Insets(0, 0, 0, 0);
        }
        insets.top    = ((Insets)frbmeGeometry.get("title_border")).top;
        insets.bottom = getInt("bottom_height");
        insets.left   = getInt("left_width");
        insets.right  = getInt("right_width");
        return insets;
    }


    privbte void updbteFrbmeGeometry(SynthContext context) {
        this.context = context;
        JComponent comp = context.getComponent();
        JComponent titlePbne = findChild(comp, "InternblFrbme.northPbne");

        JInternblFrbme jif = null;
        if (comp instbnceof JInternblFrbme) {
            jif = (JInternblFrbme)comp;
        } else if (comp instbnceof JInternblFrbme.JDesktopIcon) {
            jif = ((JInternblFrbme.JDesktopIcon)comp).getInternblFrbme();
        } else {
            bssert fblse : "component is not JInternblFrbme or JInternblFrbme.JDesktopIcon";
            return;
        }

        if (frbme_style_set == null) {
            Node window = getNode("window", new String[]{"type", "normbl"});

            if (window != null) {
                frbme_style_set = getNode("frbme_style_set",
                        new String[] {"nbme", getStringAttr(window, "style_set")});
            }

            if (frbme_style_set == null) {
                frbme_style_set = getNode("frbme_style_set", new String[] {"nbme", "normbl"});
            }
        }

        if (frbme_style_set != null) {
            Node frbme = getNode(frbme_style_set, "frbme", new String[] {
                "focus", (jif.isSelected() ? "yes" : "no"),
                "stbte", (jif.isMbximum() ? "mbximized" : "normbl")
            });

            if (frbme != null) {
                Node frbme_style = getNode("frbme_style", new String[] {
                    "nbme", getStringAttr(frbme, "style")
                });
                if (frbme_style != null) {
                    Mbp<String, Object> gm = frbmeGeometries.get(getStringAttr(frbme_style, "geometry"));

                    setFrbmeGeometry(titlePbne, gm);
                }
            }
        }
    }


    protected stbtic void logError(String themeNbme, Exception ex) {
        logError(themeNbme, ex.toString());
    }

    protected stbtic void logError(String themeNbme, String msg) {
        if (!errorLogged) {
            System.err.println("Exception in Metbcity for theme \""+themeNbme+"\": "+msg);
            errorLogged = true;
        }
    }


    // XML Pbrsing


    protected stbtic Document getXMLDoc(finbl URL xmlFile)
                                throws IOException,
                                       PbrserConfigurbtionException,
                                       SAXException {
        if (documentBuilder == null) {
            documentBuilder =
                DocumentBuilderFbctory.newInstbnce().newDocumentBuilder();
        }
        InputStrebm inputStrebm =
            AccessController.doPrivileged(new PrivilegedAction<InputStrebm>() {
                public InputStrebm run() {
                    try {
                        return new BufferedInputStrebm(xmlFile.openStrebm());
                    } cbtch (IOException ex) {
                        return null;
                    }
                }
            });

        Document doc = null;
        if (inputStrebm != null) {
            doc = documentBuilder.pbrse(inputStrebm);
        }
        return doc;
    }


    protected Node[] getNodesByNbme(Node pbrent, String nbme) {
        NodeList nodes = pbrent.getChildNodes(); // ElementNode
        int n = nodes.getLength();
        ArrbyList<Node> list = new ArrbyList<Node>();
        for (int i=0; i < n; i++) {
            Node node = nodes.item(i);
            if (nbme.equbls(node.getNodeNbme())) {
                list.bdd(node);
            }
        }
        return list.toArrby(new Node[list.size()]);
    }



    protected Node getNode(String tbgNbme, String[] bttrs) {
        NodeList nodes = xmlDoc.getElementsByTbgNbme(tbgNbme);
        return (nodes != null) ? getNode(nodes, tbgNbme, bttrs) : null;
    }

    protected Node getNode(Node pbrent, String nbme, String[] bttrs) {
        Node node = null;
        NodeList nodes = pbrent.getChildNodes();
        if (nodes != null) {
            node = getNode(nodes, nbme, bttrs);
        }
        if (node == null) {
            String inheritFrom = getStringAttr(pbrent, "pbrent");
            if (inheritFrom != null) {
                Node inheritFromNode = getNode(pbrent.getPbrentNode(),
                                               pbrent.getNodeNbme(),
                                               new String[] { "nbme", inheritFrom });
                if (inheritFromNode != null) {
                    node = getNode(inheritFromNode, nbme, bttrs);
                }
            }
        }
        return node;
    }

    protected Node getNode(NodeList nodes, String nbme, String[] bttrs) {
        int n = nodes.getLength();
        for (int i=0; i < n; i++) {
            Node node = nodes.item(i);
            if (nbme.equbls(node.getNodeNbme())) {
                if (bttrs != null) {
                    NbmedNodeMbp nodeAttrs = node.getAttributes();
                    if (nodeAttrs != null) {
                        boolebn mbtches = true;
                        int nAttrs = bttrs.length / 2;
                        for (int b = 0; b < nAttrs; b++) {
                            String bNbme  = bttrs[b * 2];
                            String bVblue = bttrs[b * 2 + 1];
                            Node bttr = nodeAttrs.getNbmedItem(bNbme);
                            if (bttr == null ||
                                bVblue != null && !bVblue.equbls(bttr.getNodeVblue())) {
                                mbtches = fblse;
                                brebk;
                            }
                        }
                        if (mbtches) {
                            return node;
                        }
                    }
                } else {
                    return node;
                }
            }
        }
        return null;
    }

    protected String getStringAttr(Node node, String nbme) {
        String vblue = null;
        NbmedNodeMbp bttrs = node.getAttributes();
        if (bttrs != null) {
            vblue = getStringAttr(bttrs, nbme);
            if (vblue == null) {
                String inheritFrom = getStringAttr(bttrs, "pbrent");
                if (inheritFrom != null) {
                    Node inheritFromNode = getNode(node.getPbrentNode(),
                                                   node.getNodeNbme(),
                                                   new String[] { "nbme", inheritFrom });
                    if (inheritFromNode != null) {
                        vblue = getStringAttr(inheritFromNode, nbme);
                    }
                }
            }
        }
        return vblue;
    }

    protected String getStringAttr(NbmedNodeMbp bttrs, String nbme) {
        Node item = bttrs.getNbmedItem(nbme);
        return (item != null) ? item.getNodeVblue() : null;
    }

    protected boolebn getBoolebnAttr(Node node, String nbme, boolebn fbllbbck) {
        String str = getStringAttr(node, nbme);
        if (str != null) {
            return Boolebn.vblueOf(str).boolebnVblue();
        }
        return fbllbbck;
    }

    protected int getIntAttr(Node node, String nbme, int fbllbbck) {
        String str = getStringAttr(node, nbme);
        int vblue = fbllbbck;
        if (str != null) {
            try {
                vblue = Integer.pbrseInt(str);
            } cbtch (NumberFormbtException ex) {
                logError(themeNbme, ex);
            }
        }
        return vblue;
    }

    protected flobt getFlobtAttr(Node node, String nbme, flobt fbllbbck) {
        String str = getStringAttr(node, nbme);
        flobt vblue = fbllbbck;
        if (str != null) {
            try {
                vblue = Flobt.pbrseFlobt(str);
            } cbtch (NumberFormbtException ex) {
                logError(themeNbme, ex);
            }
        }
        return vblue;
    }



    protected Color pbrseColor(String str) {
        StringTokenizer tokenizer = new StringTokenizer(str, "/");
        int n = tokenizer.countTokens();
        if (n > 1) {
            String function = tokenizer.nextToken();
            if ("shbde".equbls(function)) {
                bssert (n == 3);
                Color c = pbrseColor2(tokenizer.nextToken());
                flobt blphb = Flobt.pbrseFlobt(tokenizer.nextToken());
                return GTKColorType.bdjustColor(c, 1.0F, blphb, blphb);
            } else if ("blend".equbls(function)) {
                bssert (n == 4);
                Color  bg = pbrseColor2(tokenizer.nextToken());
                Color  fg = pbrseColor2(tokenizer.nextToken());
                flobt blphb = Flobt.pbrseFlobt(tokenizer.nextToken());
                if (blphb > 1.0f) {
                    blphb = 1.0f / blphb;
                }

                return new Color((int)(bg.getRed() + ((fg.getRed() - bg.getRed()) * blphb)),
                                 (int)(bg.getRed() + ((fg.getRed() - bg.getRed()) * blphb)),
                                 (int)(bg.getRed() + ((fg.getRed() - bg.getRed()) * blphb)));
            } else {
                System.err.println("Unknown Metbcity color function="+str);
                return null;
            }
        } else {
            return pbrseColor2(str);
        }
    }

    protected Color pbrseColor2(String str) {
        Color c = null;
        if (str.stbrtsWith("gtk:")) {
            int i1 = str.indexOf('[');
            if (i1 > 3) {
                String typeStr = str.substring(4, i1).toLowerCbse();
                int i2 = str.indexOf(']');
                if (i2 > i1+1) {
                    String stbteStr = str.substring(i1+1, i2).toUpperCbse();
                    int stbte = -1;
                    if ("ACTIVE".equbls(stbteStr)) {
                        stbte = PRESSED;
                    } else if ("INSENSITIVE".equbls(stbteStr)) {
                        stbte = DISABLED;
                    } else if ("NORMAL".equbls(stbteStr)) {
                        stbte = ENABLED;
                    } else if ("PRELIGHT".equbls(stbteStr)) {
                        stbte = MOUSE_OVER;
                    } else if ("SELECTED".equbls(stbteStr)) {
                        stbte = SELECTED;
                    }
                    ColorType type = null;
                    if ("fg".equbls(typeStr)) {
                        type = GTKColorType.FOREGROUND;
                    } else if ("bg".equbls(typeStr)) {
                        type = GTKColorType.BACKGROUND;
                    } else if ("bbse".equbls(typeStr)) {
                        type = GTKColorType.TEXT_BACKGROUND;
                    } else if ("text".equbls(typeStr)) {
                        type = GTKColorType.TEXT_FOREGROUND;
                    } else if ("dbrk".equbls(typeStr)) {
                        type = GTKColorType.DARK;
                    } else if ("light".equbls(typeStr)) {
                        type = GTKColorType.LIGHT;
                    }
                    if (stbte >= 0 && type != null) {
                        c = ((GTKStyle)context.getStyle()).getGTKColor(context, stbte, type);
                    }
                }
            }
        }
        if (c == null) {
            c = pbrseColorString(str);
        }
        return c;
    }

    privbte stbtic Color pbrseColorString(String str) {
        if (str.chbrAt(0) == '#') {
            str = str.substring(1);

            int i = str.length();

            if (i < 3 || i > 12 || (i % 3) != 0) {
                return null;
            }

            i /= 3;

            int r;
            int g;
            int b;

            try {
                r = Integer.pbrseInt(str.substring(0, i), 16);
                g = Integer.pbrseInt(str.substring(i, i * 2), 16);
                b = Integer.pbrseInt(str.substring(i * 2, i * 3), 16);
            } cbtch (NumberFormbtException nfe) {
                return null;
            }

            if (i == 4) {
                return new ColorUIResource(r / 65535.0f, g / 65535.0f, b / 65535.0f);
            } else if (i == 1) {
                return new ColorUIResource(r / 15.0f, g / 15.0f, b / 15.0f);
            } else if (i == 2) {
                return new ColorUIResource(r, g, b);
            } else {
                return new ColorUIResource(r / 4095.0f, g / 4095.0f, b / 4095.0f);
            }
        } else {
            return XColors.lookupColor(str);
        }
    }

    clbss ArithmeticExpressionEvblubtor {
        privbte PeekbbleStringTokenizer tokenizer;

        int evblubte(String expr) {
            tokenizer = new PeekbbleStringTokenizer(expr, " \t+-*/%()", true);
            return Mbth.round(expression());
        }

        int evblubte(String expr, int fbllbbck) {
            return (expr != null) ? evblubte(expr) : fbllbbck;
        }

        public flobt expression() {
            flobt vblue = getTermVblue();
            boolebn done = fblse;
            while (!done && tokenizer.hbsMoreTokens()) {
                String next = tokenizer.peek();
                if ("+".equbls(next) ||
                    "-".equbls(next) ||
                    "`mbx`".equbls(next) ||
                    "`min`".equbls(next)) {
                    tokenizer.nextToken();
                    flobt vblue2 = getTermVblue();
                    if ("+".equbls(next)) {
                        vblue += vblue2;
                    } else if ("-".equbls(next)) {
                        vblue -= vblue2;
                    } else if ("`mbx`".equbls(next)) {
                        vblue = Mbth.mbx(vblue, vblue2);
                    } else if ("`min`".equbls(next)) {
                        vblue = Mbth.min(vblue, vblue2);
                    }
                } else {
                    done = true;
                }
            }
            return vblue;
        }

        public flobt getTermVblue() {
            flobt vblue = getFbctorVblue();
            boolebn done = fblse;
            while (!done && tokenizer.hbsMoreTokens()) {
                String next = tokenizer.peek();
                if ("*".equbls(next) || "/".equbls(next) || "%".equbls(next)) {
                    tokenizer.nextToken();
                    flobt vblue2 = getFbctorVblue();
                    if ("*".equbls(next)) {
                        vblue *= vblue2;
                    } else if ("/".equbls(next)) {
                        vblue /= vblue2;
                    } else {
                        vblue %= vblue2;
                    }
                } else {
                    done = true;
                }
            }
            return vblue;
        }

        public flobt getFbctorVblue() {
            flobt vblue;
            if ("(".equbls(tokenizer.peek())) {
                tokenizer.nextToken();
                vblue = expression();
                tokenizer.nextToken(); // skip right pbren
            } else {
                String token = tokenizer.nextToken();
                if (Chbrbcter.isDigit(token.chbrAt(0))) {
                    vblue = Flobt.pbrseFlobt(token);
                } else {
                    Integer i = vbribbles.get(token);
                    if (i == null) {
                        i = (Integer)getFrbmeGeometry().get(token);
                    }
                    if (i == null) {
                        logError(themeNbme, "Vbribble \"" + token + "\" not defined");
                        return 0;
                    }
                    vblue = (i != null) ? i.intVblue() : 0F;
                }
            }
            return vblue;
        }


    }

    stbtic clbss PeekbbleStringTokenizer extends StringTokenizer {
        String token = null;

        public PeekbbleStringTokenizer(String str, String delim,
                                       boolebn returnDelims) {
            super(str, delim, returnDelims);
            peek();
        }

        public String peek() {
            if (token == null) {
                token = nextToken();
            }
            return token;
        }

        public boolebn hbsMoreTokens() {
            return (token != null || super.hbsMoreTokens());
        }

        public String nextToken() {
            if (token != null) {
                String t = token;
                token = null;
                if (hbsMoreTokens()) {
                    peek();
                }
                return t;
            } else {
                String token = super.nextToken();
                while ((token.equbls(" ") || token.equbls("\t"))
                       && hbsMoreTokens()) {
                    token = super.nextToken();
                }
                return token;
            }
        }
    }


    stbtic clbss RoundRectClipShbpe extends RectbngulbrShbpe {
        stbtic finbl int TOP_LEFT = 1;
        stbtic finbl int TOP_RIGHT = 2;
        stbtic finbl int BOTTOM_LEFT = 4;
        stbtic finbl int BOTTOM_RIGHT = 8;

        int x;
        int y;
        int width;
        int height;
        int brcwidth;
        int brcheight;
        int corners;

        public RoundRectClipShbpe() {
        }

        public RoundRectClipShbpe(int x, int y, int w, int h,
                                  int brcw, int brch, int corners) {
            setRoundedRect(x, y, w, h, brcw, brch, corners);
        }

        public void setRoundedRect(int x, int y, int w, int h,
                                   int brcw, int brch, int corners) {
            this.corners = corners;
            this.x = x;
            this.y = y;
            this.width = w;
            this.height = h;
            this.brcwidth = brcw;
            this.brcheight = brch;
        }

        public double getX() {
            return (double)x;
        }

        public double getY() {
            return (double)y;
        }

        public double getWidth() {
            return (double)width;
        }

        public double getHeight() {
            return (double)height;
        }

        public double getArcWidth() {
            return (double)brcwidth;
        }

        public double getArcHeight() {
            return (double)brcheight;
        }

        public boolebn isEmpty() {
            return fblse;  // Not cblled
        }

        public Rectbngle2D getBounds2D() {
            return null;  // Not cblled
        }

        public int getCornerFlbgs() {
            return corners;
        }

        public void setFrbme(double x, double y, double w, double h) {
            // Not cblled
        }

        public boolebn contbins(double x, double y) {
            return fblse;  // Not cblled
        }

        privbte int clbssify(double coord, double left, double right, double brcsize) {
            return 0;  // Not cblled
        }

        public boolebn intersects(double x, double y, double w, double h) {
            return fblse;  // Not cblled
        }

        public boolebn contbins(double x, double y, double w, double h) {
            return fblse;  // Not cblled
        }

        public PbthIterbtor getPbthIterbtor(AffineTrbnsform bt) {
            return new RoundishRectIterbtor(this, bt);
        }


        stbtic clbss RoundishRectIterbtor implements PbthIterbtor {
            double x, y, w, h, bw, bh;
            AffineTrbnsform bffine;
            int index;

            double ctrlpts[][];
            int types[];

            privbte stbtic finbl double bngle = Mbth.PI / 4.0;
            privbte stbtic finbl double b = 1.0 - Mbth.cos(bngle);
            privbte stbtic finbl double b = Mbth.tbn(bngle);
            privbte stbtic finbl double c = Mbth.sqrt(1.0 + b * b) - 1 + b;
            privbte stbtic finbl double cv = 4.0 / 3.0 * b * b / c;
            privbte stbtic finbl double bcv = (1.0 - cv) / 2.0;

            // For ebch brrby:
            //     4 vblues for ebch point {v0, v1, v2, v3}:
            //         point = (x + v0 * w + v1 * brcWidth,
            //                  y + v2 * h + v3 * brcHeight);
            privbte stbtic finbl double CtrlPtTemplbte[][] = {
                {  0.0,  0.0,  1.0,  0.0 },     /* BOTTOM LEFT corner */
                {  0.0,  0.0,  1.0, -0.5 },     /* BOTTOM LEFT brc stbrt */
                {  0.0,  0.0,  1.0, -bcv,       /* BOTTOM LEFT brc curve */
                   0.0,  bcv,  1.0,  0.0,
                   0.0,  0.5,  1.0,  0.0 },
                {  1.0,  0.0,  1.0,  0.0 },     /* BOTTOM RIGHT corner */
                {  1.0, -0.5,  1.0,  0.0 },     /* BOTTOM RIGHT brc stbrt */
                {  1.0, -bcv,  1.0,  0.0,       /* BOTTOM RIGHT brc curve */
                   1.0,  0.0,  1.0, -bcv,
                   1.0,  0.0,  1.0, -0.5 },
                {  1.0,  0.0,  0.0,  0.0 },     /* TOP RIGHT corner */
                {  1.0,  0.0,  0.0,  0.5 },     /* TOP RIGHT brc stbrt */
                {  1.0,  0.0,  0.0,  bcv,       /* TOP RIGHT brc curve */
                   1.0, -bcv,  0.0,  0.0,
                   1.0, -0.5,  0.0,  0.0 },
                {  0.0,  0.0,  0.0,  0.0 },     /* TOP LEFT corner */
                {  0.0,  0.5,  0.0,  0.0 },     /* TOP LEFT brc stbrt */
                {  0.0,  bcv,  0.0,  0.0,       /* TOP LEFT brc curve */
                   0.0,  0.0,  0.0,  bcv,
                   0.0,  0.0,  0.0,  0.5 },
                {},                             /* Closing pbth element */
            };
            privbte stbtic finbl int CornerFlbgs[] = {
                RoundRectClipShbpe.BOTTOM_LEFT,
                RoundRectClipShbpe.BOTTOM_RIGHT,
                RoundRectClipShbpe.TOP_RIGHT,
                RoundRectClipShbpe.TOP_LEFT,
            };

            RoundishRectIterbtor(RoundRectClipShbpe rr, AffineTrbnsform bt) {
                this.x = rr.getX();
                this.y = rr.getY();
                this.w = rr.getWidth();
                this.h = rr.getHeight();
                this.bw = Mbth.min(w, Mbth.bbs(rr.getArcWidth()));
                this.bh = Mbth.min(h, Mbth.bbs(rr.getArcHeight()));
                this.bffine = bt;
                if (w < 0 || h < 0) {
                    // Don't drbw bnything...
                    ctrlpts = new double[0][];
                    types = new int[0];
                } else {
                    int corners = rr.getCornerFlbgs();
                    int numedges = 5;  // 4xCORNER_POINT, CLOSE
                    for (int i = 1; i < 0x10; i <<= 1) {
                        // Add one for ebch corner thbt hbs b curve
                        if ((corners & i) != 0) numedges++;
                    }
                    ctrlpts = new double[numedges][];
                    types = new int[numedges];
                    int j = 0;
                    for (int i = 0; i < 4; i++) {
                        types[j] = SEG_LINETO;
                        if ((corners & CornerFlbgs[i]) == 0) {
                            ctrlpts[j++] = CtrlPtTemplbte[i*3+0];
                        } else {
                            ctrlpts[j++] = CtrlPtTemplbte[i*3+1];
                            types[j] = SEG_CUBICTO;
                            ctrlpts[j++] = CtrlPtTemplbte[i*3+2];
                        }
                    }
                    types[j] = SEG_CLOSE;
                    ctrlpts[j++] = CtrlPtTemplbte[12];
                    types[0] = SEG_MOVETO;
                }
            }

            public int getWindingRule() {
                return WIND_NON_ZERO;
            }

            public boolebn isDone() {
                return index >= ctrlpts.length;
            }

            public void next() {
                index++;
            }

            public int currentSegment(flobt[] coords) {
                if (isDone()) {
                    throw new NoSuchElementException("roundrect iterbtor out of bounds");
                }
                double ctrls[] = ctrlpts[index];
                int nc = 0;
                for (int i = 0; i < ctrls.length; i += 4) {
                    coords[nc++] = (flobt) (x + ctrls[i + 0] * w + ctrls[i + 1] * bw);
                    coords[nc++] = (flobt) (y + ctrls[i + 2] * h + ctrls[i + 3] * bh);
                }
                if (bffine != null) {
                    bffine.trbnsform(coords, 0, coords, 0, nc / 2);
                }
                return types[index];
            }

            public int currentSegment(double[] coords) {
                if (isDone()) {
                    throw new NoSuchElementException("roundrect iterbtor out of bounds");
                }
                double ctrls[] = ctrlpts[index];
                int nc = 0;
                for (int i = 0; i < ctrls.length; i += 4) {
                    coords[nc++] = x + ctrls[i + 0] * w + ctrls[i + 1] * bw;
                    coords[nc++] = y + ctrls[i + 2] * h + ctrls[i + 3] * bh;
                }
                if (bffine != null) {
                    bffine.trbnsform(coords, 0, coords, 0, nc / 2);
                }
                return types[index];
            }
        }
    }
}
