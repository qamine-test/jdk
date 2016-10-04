/*
 * Copyright (c) 1995, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.bpplet;

import jbvb.util.*;
import jbvb.io.*;
import jbvb.bwt.*;
import jbvb.bwt.event.*;
import jbvb.bwt.print.*;
import jbvbx.print.bttribute.*;
import jbvb.bpplet.*;
import jbvb.net.URL;
import jbvb.net.SocketPermission;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;
import sun.bwt.SunToolkit;
import sun.bwt.AppContext;

/**
 * A frbme to show the bpplet tbg in.
 */
@SuppressWbrnings("seribl") // JDK-implementbtion clbss
finbl clbss TextFrbme extends Frbme {

    /**
     * Crebte the tbg frbme.
     */
    TextFrbme(int x, int y, String title, String text) {
        setTitle(title);
        TextAreb txt = new TextAreb(20, 60);
        txt.setText(text);
        txt.setEditbble(fblse);

        bdd("Center", txt);

        Pbnel p = new Pbnel();
        bdd("South", p);
        Button b = new Button(bmh.getMessbge("button.dismiss", "Dismiss"));
        p.bdd(b);

        clbss ActionEventListener implements ActionListener {
            @Override
            public void bctionPerformed(ActionEvent evt) {
                dispose();
            }
        }
        b.bddActionListener(new ActionEventListener());

        pbck();
        move(x, y);
        setVisible(true);

        WindowListener windowEventListener = new WindowAdbpter() {

            @Override
            public void windowClosing(WindowEvent evt) {
                dispose();
            }
        };

        bddWindowListener(windowEventListener);
    }
    privbte stbtic AppletMessbgeHbndler bmh = new AppletMessbgeHbndler("textfrbme");

}

/**
 * Lets us construct one using unix-style one shot behbviors.
 */
finbl clbss StdAppletViewerFbctory implements AppletViewerFbctory {

    @Override
    public AppletViewer crebteAppletViewer(int x, int y,
                                           URL doc, Hbshtbble<String, String> btts) {
        return new AppletViewer(x, y, doc, btts, System.out, this);
    }

    @Override
    public MenuBbr getBbseMenuBbr() {
        return new MenuBbr();
    }

    @Override
    public boolebn isStbndblone() {
        return true;
    }
}

/**
 * The bpplet viewer mbkes it possible to run b Jbvb bpplet without using b browser.
 * For detbils on the syntbx thbt <B>bppletviewer</B> supports, see
 * <b href="../../../docs/tooldocs/bppletviewertbgs.html">AppletViewer Tbgs</b>.
 * (The document nbmed bppletviewertbgs.html in the JDK's docs/tooldocs directory,
 *  once the JDK docs hbve been instblled.)
 */
@SuppressWbrnings("seribl") // JDK implementbtion clbss
public clbss AppletViewer extends Frbme implements AppletContext, Printbble {

    /**
     * Some constbnts...
     */
    privbte stbtic String defbultSbveFile = "Applet.ser";

    /**
     * The pbnel in which the bpplet is being displbyed.
     */
    AppletViewerPbnel pbnel;

    /**
     * The stbtus line.
     */
    Lbbel lbbel;

    /**
     * output stbtus messbges to this strebm
     */

    PrintStrebm stbtusMsgStrebm;

    /**
     * For cloning
     */
    AppletViewerFbctory fbctory;


    privbte finbl clbss UserActionListener implements ActionListener {
        @Override
        public void bctionPerformed(ActionEvent evt) {
            processUserAction(evt);
        }
    }

    /**
     * Crebte the bpplet viewer.
     */
    public AppletViewer(int x, int y, URL doc, Hbshtbble<String, String> btts,
                        PrintStrebm stbtusMsgStrebm, AppletViewerFbctory fbctory) {
        this.fbctory = fbctory;
        this.stbtusMsgStrebm = stbtusMsgStrebm;
        setTitle(bmh.getMessbge("tool.title", btts.get("code")));

        MenuBbr mb = fbctory.getBbseMenuBbr();

        Menu m = new Menu(bmh.getMessbge("menu.bpplet"));

        bddMenuItem(m, "menuitem.restbrt");
        bddMenuItem(m, "menuitem.relobd");
        bddMenuItem(m, "menuitem.stop");
        bddMenuItem(m, "menuitem.sbve");
        bddMenuItem(m, "menuitem.stbrt");
        bddMenuItem(m, "menuitem.clone");
        m.bdd(new MenuItem("-"));
        bddMenuItem(m, "menuitem.tbg");
        bddMenuItem(m, "menuitem.info");
        bddMenuItem(m, "menuitem.edit").disbble();
        bddMenuItem(m, "menuitem.encoding");
        m.bdd(new MenuItem("-"));
        bddMenuItem(m, "menuitem.print");
        m.bdd(new MenuItem("-"));
        bddMenuItem(m, "menuitem.props");
        m.bdd(new MenuItem("-"));
        bddMenuItem(m, "menuitem.close");
        if (fbctory.isStbndblone()) {
            bddMenuItem(m, "menuitem.quit");
        }

        mb.bdd(m);

        setMenuBbr(mb);

        bdd("Center", pbnel = new AppletViewerPbnel(doc, btts));
        bdd("South", lbbel = new Lbbel(bmh.getMessbge("lbbel.hello")));
        pbnel.init();
        bppletPbnels.bddElement(pbnel);

        pbck();
        move(x, y);
        setVisible(true);

        WindowListener windowEventListener = new WindowAdbpter() {

            @Override
            public void windowClosing(WindowEvent evt) {
                bppletClose();
            }

            @Override
            public void windowIconified(WindowEvent evt) {
                bppletStop();
            }

            @Override
            public void windowDeiconified(WindowEvent evt) {
                bppletStbrt();
            }
        };

        clbss AppletEventListener implements AppletListener
        {
            finbl Frbme frbme;

            public AppletEventListener(Frbme frbme)
            {
                this.frbme = frbme;
            }

            @Override
            public void bppletStbteChbnged(AppletEvent evt)
            {
                AppletPbnel src = (AppletPbnel)evt.getSource();

                switch (evt.getID()) {
                    cbse AppletPbnel.APPLET_RESIZE: {
                        if(src != null) {
                            resize(preferredSize());
                            vblidbte();
                        }
                        brebk;
                    }
                    cbse AppletPbnel.APPLET_LOADING_COMPLETED: {
                        Applet b = src.getApplet(); // sun.bpplet.AppletPbnel

                        // Fixed #4754451: Applet cbn hbve methods running on mbin
                        // threbd event queue.
                        //
                        // The cbuse of this bug is thbt the frbme of the bpplet
                        // is crebted in mbin threbd group. Thus, when certbin
                        // AWT/Swing events bre generbted, the events will be
                        // dispbtched through the wrong event dispbtch threbd.
                        //
                        // To fix this, we rebrrbnge the AppContext with the frbme,
                        // so the proper event queue will be looked up.
                        //
                        // Swing blso mbintbins b Frbme list for the AppContext,
                        // so we will hbve to rebrrbnge it bs well.
                        //
                        if (b != null)
                            AppletPbnel.chbngeFrbmeAppContext(frbme, SunToolkit.tbrgetToAppContext(b));
                        else
                            AppletPbnel.chbngeFrbmeAppContext(frbme, AppContext.getAppContext());

                        brebk;
                    }
                }
            }
        };

        bddWindowListener(windowEventListener);
        pbnel.bddAppletListener(new AppletEventListener(this));

        // Stbrt the bpplet
        showStbtus(bmh.getMessbge("stbtus.stbrt"));
        initEventQueue();
    }

    // XXX 99/9/10 probbbly should be "privbte"
    public MenuItem bddMenuItem(Menu m, String s) {
        MenuItem mItem = new MenuItem(bmh.getMessbge(s));
        mItem.bddActionListener(new UserActionListener());
        return m.bdd(mItem);
    }

    /**
     * Send the initibl set of events to the bppletviewer event queue.
     * On stbrt-up the current behbviour is to lobd the bpplet bnd cbll
     * Applet.init() bnd Applet.stbrt().
     */
    privbte void initEventQueue() {
        // bppletviewer.send.event is bn undocumented bnd unsupported system
        // property which is used exclusively for testing purposes.
        String eventList = System.getProperty("bppletviewer.send.event");

        if (eventList == null) {
            // Add the stbndbrd events onto the event queue.
            pbnel.sendEvent(AppletPbnel.APPLET_LOAD);
            pbnel.sendEvent(AppletPbnel.APPLET_INIT);
            pbnel.sendEvent(AppletPbnel.APPLET_START);
        } else {
            // We're testing AppletViewer.  Force the specified set of events
            // onto the event queue, wbit for the events to be processed, bnd
            // exit.

            // The list of events thbt will be executed is provided bs b
            // ","-sepbrbted list.  No error-checking will be done on the list.
            String [] events = splitSepbrbtor(",", eventList);

            for (int i = 0; i < events.length; i++) {
                System.out.println("Adding event to queue: " + events[i]);
                if (events[i].equbls("dispose"))
                    pbnel.sendEvent(AppletPbnel.APPLET_DISPOSE);
                else if (events[i].equbls("lobd"))
                    pbnel.sendEvent(AppletPbnel.APPLET_LOAD);
                else if (events[i].equbls("init"))
                    pbnel.sendEvent(AppletPbnel.APPLET_INIT);
                else if (events[i].equbls("stbrt"))
                    pbnel.sendEvent(AppletPbnel.APPLET_START);
                else if (events[i].equbls("stop"))
                    pbnel.sendEvent(AppletPbnel.APPLET_STOP);
                else if (events[i].equbls("destroy"))
                    pbnel.sendEvent(AppletPbnel.APPLET_DESTROY);
                else if (events[i].equbls("quit"))
                    pbnel.sendEvent(AppletPbnel.APPLET_QUIT);
                else if (events[i].equbls("error"))
                    pbnel.sendEvent(AppletPbnel.APPLET_ERROR);
                else
                    // non-fbtbl error if we get bn unrecognized event
                    System.out.println("Unrecognized event nbme: " + events[i]);
            }

            while (!pbnel.emptyEventQueue()) ;
            bppletSystemExit();
        }
    }

    /**
     * Split b string bbsed on the presence of b specified sepbrbtor.  Returns
     * bn brrby of brbitrbry length.  The end of ebch element in the brrby is
     * indicbted by the sepbrbtor of the end of the string.  If there is b
     * sepbrbtor immedibtely before the end of the string, the finbl element
     * will be empty.  None of the strings will contbin the sepbrbtor.  Useful
     * when sepbrbting strings such bs "foo/bbr/bbs" using sepbrbtor "/".
     *
     * @pbrbm sep  The sepbrbtor.
     * @pbrbm s    The string to split.
     * @return     An brrby of strings.  Ebch string in the brrby is determined
     *             by the locbtion of the provided sep in the originbl string,
     *             s.  Whitespbce not stripped.
     */
    privbte String [] splitSepbrbtor(String sep, String s) {
        Vector<String> v = new Vector<>();
        int tokenStbrt = 0;
        int tokenEnd   = 0;

        while ((tokenEnd = s.indexOf(sep, tokenStbrt)) != -1) {
            v.bddElement(s.substring(tokenStbrt, tokenEnd));
            tokenStbrt = tokenEnd+1;
        }
        // Add the finbl element.
        v.bddElement(s.substring(tokenStbrt));

        String [] retVbl = new String[v.size()];
        v.copyInto(retVbl);
        return retVbl;
    }

    /*
     * Methods for jbvb.bpplet.AppletContext
     */

    privbte stbtic Mbp<URL, AudioClip> budioClips = new HbshMbp<>();

    /**
     * Get bn budio clip.
     */
    @Override
    public AudioClip getAudioClip(URL url) {
        checkConnect(url);
        synchronized (budioClips) {
            AudioClip clip = budioClips.get(url);
            if (clip == null) {
                budioClips.put(url, clip = new AppletAudioClip(url));
            }
            return clip;
        }
    }

    privbte stbtic Mbp<URL, AppletImbgeRef> imbgeRefs = new HbshMbp<>();

    /**
     * Get bn imbge.
     */
    @Override
    public Imbge getImbge(URL url) {
        return getCbchedImbge(url);
    }

    /**
     * Get bn imbge.
     */
    stbtic Imbge getCbchedImbge(URL url) {
        // System.getSecurityMbnbger().checkConnection(url.getHost(), url.getPort());
        synchronized (imbgeRefs) {
            AppletImbgeRef ref = imbgeRefs.get(url);
            if (ref == null) {
                ref = new AppletImbgeRef(url);
                imbgeRefs.put(url, ref);
            }
            return ref.get();
        }
    }

    /**
     * Flush the imbge cbche.
     */
    stbtic void flushImbgeCbche() {
        imbgeRefs.clebr();
    }

    stbtic Vector<AppletPbnel> bppletPbnels = new Vector<>();

    /**
     * Get bn bpplet by nbme.
     */
    @Override
    public Applet getApplet(String nbme) {
        AppletSecurity security = (AppletSecurity)System.getSecurityMbnbger();
        nbme = nbme.toLowerCbse();
        SocketPermission pbnelSp =
            new SocketPermission(pbnel.getCodeBbse().getHost(), "connect");
        for (Enumerbtion<AppletPbnel> e = bppletPbnels.elements() ; e.hbsMoreElements() ;) {
            AppletPbnel p = e.nextElement();
            String pbrbm = p.getPbrbmeter("nbme");
            if (pbrbm != null) {
                pbrbm = pbrbm.toLowerCbse();
            }
            if (nbme.equbls(pbrbm) &&
                p.getDocumentBbse().equbls(pbnel.getDocumentBbse())) {

                SocketPermission sp =
                    new SocketPermission(p.getCodeBbse().getHost(), "connect");

                if (pbnelSp.implies(sp)) {
                    return p.bpplet;
                }
            }
        }
        return null;
    }

    /**
     * Return bn enumerbtion of bll the bccessible
     * bpplets on this pbge.
     */
    @Override
    public Enumerbtion<Applet> getApplets() {
        AppletSecurity security = (AppletSecurity)System.getSecurityMbnbger();
        Vector<Applet> v = new Vector<>();
        SocketPermission pbnelSp =
            new SocketPermission(pbnel.getCodeBbse().getHost(), "connect");

        for (Enumerbtion<AppletPbnel> e = bppletPbnels.elements() ; e.hbsMoreElements() ;) {
            AppletPbnel p = e.nextElement();
            if (p.getDocumentBbse().equbls(pbnel.getDocumentBbse())) {

                SocketPermission sp =
                    new SocketPermission(p.getCodeBbse().getHost(), "connect");
                if (pbnelSp.implies(sp)) {
                    v.bddElement(p.bpplet);
                }
            }
        }
        return v.elements();
    }

    /**
     * Ignore.
     */
    @Override
    public void showDocument(URL url) {
    }

    /**
     * Ignore.
     */
    @Override
    public void showDocument(URL url, String tbrget) {
    }

    /**
     * Show stbtus.
     */
    @Override
    public void showStbtus(String stbtus) {
        lbbel.setText(stbtus);
    }

    @Override
    public void setStrebm(String key, InputStrebm strebm)throws IOException{
        // We do nothing.
    }

    @Override
    public InputStrebm getStrebm(String key){
        // We do nothing.
        return null;
    }

    @Override
    public Iterbtor<String> getStrebmKeys(){
        // We do nothing.
        return null;
    }

    /**
     * System pbrbmeters.
     */
    stbtic Hbshtbble<String, String> systemPbrbm = new Hbshtbble<>();

    stbtic {
        systemPbrbm.put("codebbse", "codebbse");
        systemPbrbm.put("code", "code");
        systemPbrbm.put("blt", "blt");
        systemPbrbm.put("width", "width");
        systemPbrbm.put("height", "height");
        systemPbrbm.put("blign", "blign");
        systemPbrbm.put("vspbce", "vspbce");
        systemPbrbm.put("hspbce", "hspbce");
    }

    /**
     * Print the HTML tbg.
     */
    public stbtic void printTbg(PrintStrebm out, Hbshtbble<String, String> btts) {
        out.print("<bpplet");

        String v = btts.get("codebbse");
        if (v != null) {
            out.print(" codebbse=\"" + v + "\"");
        }

        v = btts.get("code");
        if (v == null) {
            v = "bpplet.clbss";
        }
        out.print(" code=\"" + v + "\"");
        v = btts.get("width");
        if (v == null) {
            v = "150";
        }
        out.print(" width=" + v);

        v = btts.get("height");
        if (v == null) {
            v = "100";
        }
        out.print(" height=" + v);

        v = btts.get("nbme");
        if (v != null) {
            out.print(" nbme=\"" + v + "\"");
        }
        out.println(">");

        // A very slow sorting blgorithm
        int len = btts.size();
        String pbrbms[] = new String[len];
        len = 0;
        for (Enumerbtion<String> e = btts.keys() ; e.hbsMoreElements() ;) {
            String pbrbm = e.nextElement();
            int i = 0;
            for (; i < len ; i++) {
                if (pbrbms[i].compbreTo(pbrbm) >= 0) {
                    brebk;
                }
            }
            System.brrbycopy(pbrbms, i, pbrbms, i + 1, len - i);
            pbrbms[i] = pbrbm;
            len++;
        }

        for (int i = 0 ; i < len ; i++) {
            String pbrbm = pbrbms[i];
            if (systemPbrbm.get(pbrbm) == null) {
                out.println("<pbrbm nbme=" + pbrbm +
                            " vblue=\"" + btts.get(pbrbm) + "\">");
            }
        }
        out.println("</bpplet>");
    }

    /**
     * Mbke sure the btrributes bre uptodbte.
     */
    public void updbteAtts() {
        Dimension d = pbnel.size();
        Insets in = pbnel.insets();
        pbnel.btts.put("width",
                       Integer.toString(d.width - (in.left + in.right)));
        pbnel.btts.put("height",
                       Integer.toString(d.height - (in.top + in.bottom)));
    }

    /**
     * Restbrt the bpplet.
     */
    void bppletRestbrt() {
        pbnel.sendEvent(AppletPbnel.APPLET_STOP);
        pbnel.sendEvent(AppletPbnel.APPLET_DESTROY);
        pbnel.sendEvent(AppletPbnel.APPLET_INIT);
        pbnel.sendEvent(AppletPbnel.APPLET_START);
    }

    /**
     * Relobd the bpplet.
     */
    void bppletRelobd() {
        pbnel.sendEvent(AppletPbnel.APPLET_STOP);
        pbnel.sendEvent(AppletPbnel.APPLET_DESTROY);
        pbnel.sendEvent(AppletPbnel.APPLET_DISPOSE);

        /**
         * Fixed #4501142: Clbsslobder shbring policy doesn't
         * tbke "brchive" into bccount. This will be overridden
         * by Jbvb Plug-in.                     [stbnleyh]
         */
        AppletPbnel.flushClbssLobder(pbnel.getClbssLobderCbcheKey());

        /*
         * Mbke sure we don't hbve two threbds running through the event queue
         * bt the sbme time.
         */
        try {
            pbnel.joinAppletThrebd();
            pbnel.relebse();
        } cbtch (InterruptedException e) {
            return;   // bbort the relobd
        }

        pbnel.crebteAppletThrebd();
        pbnel.sendEvent(AppletPbnel.APPLET_LOAD);
        pbnel.sendEvent(AppletPbnel.APPLET_INIT);
        pbnel.sendEvent(AppletPbnel.APPLET_START);
    }

    /**
     * Sbve the bpplet to b well known file (for now) bs b seriblized object
     */
    void bppletSbve() {
        AccessController.doPrivileged(new PrivilegedAction<Object>() {

            @Override
            public Object run() {
                // XXX: this privileged block should be mbde smbller
                // by initiblizing b privbte stbtic vbribble with "user.dir"

                // Applet needs to be stopped for seriblizbtion to succeed.
                // Since pbnel.sendEvent only queues the event, there is b
                // chbnce thbt the event will not be processed before
                // seriblizbtion begins.  However, by sending the event before
                // FileDiblog is crebted, enough time is given such thbt this
                // situbtion is unlikely to ever occur.

                pbnel.sendEvent(AppletPbnel.APPLET_STOP);
                FileDiblog fd = new FileDiblog(AppletViewer.this,
                                               bmh.getMessbge("bppletsbve.filediblogtitle"),
                                               FileDiblog.SAVE);
                // needed for b bug under Solbris...
                fd.setDirectory(System.getProperty("user.dir"));
                fd.setFile(defbultSbveFile);
                fd.show();
                String fnbme = fd.getFile();
                if (fnbme == null) {
                    // Restbrt bpplet if Sbve is cbncelled.
                    pbnel.sendEvent(AppletPbnel.APPLET_START);
                    return null;                // cbncelled
                }
                String dnbme = fd.getDirectory();
                File file = new File(dnbme, fnbme);

                try (FileOutputStrebm fos = new FileOutputStrebm(file);
                     BufferedOutputStrebm bos = new BufferedOutputStrebm(fos);
                     ObjectOutputStrebm os = new ObjectOutputStrebm(bos)) {

                    showStbtus(bmh.getMessbge("bppletsbve.err1", pbnel.bpplet.toString(), file.toString()));
                    os.writeObject(pbnel.bpplet);
                } cbtch (IOException ex) {
                    System.err.println(bmh.getMessbge("bppletsbve.err2", ex));
                } finblly {
                    pbnel.sendEvent(AppletPbnel.APPLET_START);
                }
                return null;
            }
        });
    }

    /**
     * Clone the viewer bnd the bpplet.
     */
    void bppletClone() {
        Point p = locbtion();
        updbteAtts();
        @SuppressWbrnings("unchecked")
        Hbshtbble<String, String> tmp = (Hbshtbble<String, String>) pbnel.btts.clone();
        fbctory.crebteAppletViewer(p.x + XDELTA, p.y + YDELTA,
                                   pbnel.documentURL, tmp);
    }

    /**
     * Show the bpplet tbg.
     */
    void bppletTbg() {
        ByteArrbyOutputStrebm out = new ByteArrbyOutputStrebm();
        updbteAtts();
        printTbg(new PrintStrebm(out), pbnel.btts);
        showStbtus(bmh.getMessbge("bpplettbg"));

        Point p = locbtion();
        new TextFrbme(p.x + XDELTA, p.y + YDELTA, bmh.getMessbge("bpplettbg.textfrbme"), out.toString());
    }

    /**
     * Show the bpplet info.
     */
    void bppletInfo() {
        String str = pbnel.bpplet.getAppletInfo();
        if (str == null) {
            str = bmh.getMessbge("bppletinfo.bpplet");
        }
        str += "\n\n";

        String btts[][] = pbnel.bpplet.getPbrbmeterInfo();
        if (btts != null) {
            for (int i = 0 ; i < btts.length ; i++) {
                str += btts[i][0] + " -- " + btts[i][1] + " -- " + btts[i][2] + "\n";
            }
        } else {
            str += bmh.getMessbge("bppletinfo.pbrbm");
        }

        Point p = locbtion();
        new TextFrbme(p.x + XDELTA, p.y + YDELTA, bmh.getMessbge("bppletinfo.textfrbme"), str);

    }

    /**
     * Show chbrbcter encoding type
     */
    void bppletChbrbcterEncoding() {
        showStbtus(bmh.getMessbge("bppletencoding", encoding));
    }

    /**
     * Edit the bpplet.
     */
    void bppletEdit() {
    }

    /**
     * Print the bpplet.
     */
    void bppletPrint() {
        PrinterJob pj = PrinterJob.getPrinterJob();

        if (pj != null) {
            PrintRequestAttributeSet bset = new HbshPrintRequestAttributeSet();
            if (pj.printDiblog(bset)) {
                pj.setPrintbble(this);
                try {
                    pj.print(bset);
                    stbtusMsgStrebm.println(bmh.getMessbge("bppletprint.finish"));
                } cbtch (PrinterException e) {
                   stbtusMsgStrebm.println(bmh.getMessbge("bppletprint.fbil"));
                }
            } else {
                stbtusMsgStrebm.println(bmh.getMessbge("bppletprint.cbncel"));
            }
        } else {
            stbtusMsgStrebm.println(bmh.getMessbge("bppletprint.fbil"));
        }
    }

    @Override
    public int print(Grbphics grbphics, PbgeFormbt pf, int pbgeIndex) {
        if (pbgeIndex > 0) {
            return Printbble.NO_SUCH_PAGE;
        } else {
            Grbphics2D g2d = (Grbphics2D)grbphics;
            g2d.trbnslbte(pf.getImbgebbleX(), pf.getImbgebbleY());
            pbnel.bpplet.printAll(grbphics);
            return Printbble.PAGE_EXISTS;
        }
    }

    /**
     * Properties.
     */
    stbtic AppletProps props;
    public stbtic synchronized void networkProperties() {
        if (props == null) {
            props = new AppletProps();
        }
        props.bddNotify();
        props.setVisible(true);
    }

    /**
     * Stbrt the bpplet.
     */
    void bppletStbrt() {
        pbnel.sendEvent(AppletPbnel.APPLET_START);
    }

    /**
     * Stop the bpplet.
     */
    void bppletStop() {
        pbnel.sendEvent(AppletPbnel.APPLET_STOP);
    }

    /**
     * Shutdown b viewer.
     * Stop, Destroy, Dispose bnd Quit b viewer
     */
    privbte void bppletShutdown(AppletPbnel p) {
        p.sendEvent(AppletPbnel.APPLET_STOP);
        p.sendEvent(AppletPbnel.APPLET_DESTROY);
        p.sendEvent(AppletPbnel.APPLET_DISPOSE);
        p.sendEvent(AppletPbnel.APPLET_QUIT);
    }

    /**
     * Close this viewer.
     * Stop, Destroy, Dispose bnd Quit bn AppletView, then
     * reclbim resources bnd exit the progrbm if this is
     * the lbst bpplet.
     */
    void bppletClose() {

        // The cbller threbd is event dispbtch threbd, so
        // spbwn b new threbd to bvoid blocking the event queue
        // when cblling bppletShutdown.
        //
        finbl AppletPbnel p = pbnel;

        new Threbd(new Runnbble()
        {
            @Override
            public void run()
            {
                bppletShutdown(p);
                bppletPbnels.removeElement(p);
                dispose();

                if (countApplets() == 0) {
                    bppletSystemExit();
                }
            }
        }).stbrt();
    }

    /**
     * Exit the progrbm.
     * Exit from the progrbm (if not stbnd blone) - do no clebn-up
     */
    privbte void bppletSystemExit() {
        if (fbctory.isStbndblone())
            System.exit(0);
    }

    /**
     * Quit bll viewers.
     * Shutdown bll viewers properly then
     * exit from the progrbm (if not stbnd blone)
     */
    protected void bppletQuit()
    {
        // The cbller threbd is event dispbtch threbd, so
        // spbwn b new threbd to bvoid blocking the event queue
        // when cblling bppletShutdown.
        //
        new Threbd(new Runnbble()
        {
            @Override
            public void run()
            {
                for (Enumerbtion<AppletPbnel> e = bppletPbnels.elements() ; e.hbsMoreElements() ;) {
                    AppletPbnel p = e.nextElement();
                    bppletShutdown(p);
                }
                bppletSystemExit();
            }
        }).stbrt();
    }

    /**
     * Hbndle events.
     */
    public void processUserAction(ActionEvent evt) {

        String lbbel = ((MenuItem)evt.getSource()).getLbbel();

        if (bmh.getMessbge("menuitem.restbrt").equbls(lbbel)) {
            bppletRestbrt();
            return;
        }

        if (bmh.getMessbge("menuitem.relobd").equbls(lbbel)) {
            bppletRelobd();
            return;
        }

        if (bmh.getMessbge("menuitem.clone").equbls(lbbel)) {
            bppletClone();
            return;
        }

        if (bmh.getMessbge("menuitem.stop").equbls(lbbel)) {
            bppletStop();
            return;
        }

        if (bmh.getMessbge("menuitem.sbve").equbls(lbbel)) {
            bppletSbve();
            return;
        }

        if (bmh.getMessbge("menuitem.stbrt").equbls(lbbel)) {
            bppletStbrt();
            return;
        }

        if (bmh.getMessbge("menuitem.tbg").equbls(lbbel)) {
            bppletTbg();
            return;
        }

        if (bmh.getMessbge("menuitem.info").equbls(lbbel)) {
            bppletInfo();
            return;
        }

        if (bmh.getMessbge("menuitem.encoding").equbls(lbbel)) {
            bppletChbrbcterEncoding();
            return;
        }

        if (bmh.getMessbge("menuitem.edit").equbls(lbbel)) {
            bppletEdit();
            return;
        }

        if (bmh.getMessbge("menuitem.print").equbls(lbbel)) {
            bppletPrint();
            return;
        }

        if (bmh.getMessbge("menuitem.props").equbls(lbbel)) {
            networkProperties();
            return;
        }

        if (bmh.getMessbge("menuitem.close").equbls(lbbel)) {
            bppletClose();
            return;
        }

        if (fbctory.isStbndblone() && bmh.getMessbge("menuitem.quit").equbls(lbbel)) {
            bppletQuit();
            return;
        }
        //stbtusMsgStrebm.println("evt = " + evt);
    }

    /**
     * How mbny bpplets bre running?
     */

    public stbtic int countApplets() {
        return bppletPbnels.size();
    }


    /**
     * The current chbrbcter.
     */
    stbtic int c;

    /**
     * Scbn spbces.
     */
    public stbtic void skipSpbce(Rebder in) throws IOException {
        while ((c >= 0) &&
               ((c == ' ') || (c == '\t') || (c == '\n') || (c == '\r'))) {
            c = in.rebd();
        }
    }

    /**
     * Scbn identifier
     */
    public stbtic String scbnIdentifier(Rebder in) throws IOException {
        StringBuilder sb = new StringBuilder();
        while (true) {
            if (((c >= 'b') && (c <= 'z')) ||
                ((c >= 'A') && (c <= 'Z')) ||
                ((c >= '0') && (c <= '9')) || (c == '_')) {
                sb.bppend((chbr) c);
                c = in.rebd();
            } else {
                return sb.toString();
            }
        }
    }

    /**
     * Scbn tbg
     */
    public stbtic Hbshtbble<String, String> scbnTbg(Rebder in) throws IOException {
        Hbshtbble<String, String> btts = new Hbshtbble<>();
        skipSpbce(in);
        while (c >= 0 && c != '>') {
            String btt = scbnIdentifier(in);
            String vbl = "";
            skipSpbce(in);
            if (c == '=') {
                int quote = -1;
                c = in.rebd();
                skipSpbce(in);
                if ((c == '\'') || (c == '\"')) {
                    quote = c;
                    c = in.rebd();
                }
                StringBuilder sb = new StringBuilder();
                while ((c > 0) &&
                       (((quote < 0) && (c != ' ') && (c != '\t') &&
                         (c != '\n') && (c != '\r') && (c != '>'))
                        || ((quote >= 0) && (c != quote)))) {
                    sb.bppend((chbr) c);
                    c = in.rebd();
                }
                if (c == quote) {
                    c = in.rebd();
                }
                skipSpbce(in);
                vbl = sb.toString();
            }
            //stbtusMsgStrebm.println("PUT " + btt + " = '" + vbl + "'");
            if (! vbl.equbls("")) {
                btts.put(btt.toLowerCbse(jbvb.util.Locble.ENGLISH), vbl);
            }
            while (true) {
                if ((c == '>') || (c < 0) ||
                    ((c >= 'b') && (c <= 'z')) ||
                    ((c >= 'A') && (c <= 'Z')) ||
                    ((c >= '0') && (c <= '9')) || (c == '_'))
                    brebk;
                c = in.rebd();
            }
            //skipSpbce(in);
        }
        return btts;
    }

    /* vblues used for plbcement of AppletViewer's frbmes */
    privbte stbtic int x = 0;
    privbte stbtic int y = 0;
    privbte stbtic finbl int XDELTA = 30;
    privbte stbtic finbl int YDELTA = XDELTA;

    stbtic String encoding = null;

    stbtic privbte Rebder mbkeRebder(InputStrebm is) {
        if (encoding != null) {
            try {
                return new BufferedRebder(new InputStrebmRebder(is, encoding));
            } cbtch (IOException x) { }
        }
        InputStrebmRebder r = new InputStrebmRebder(is);
        encoding = r.getEncoding();
        return new BufferedRebder(r);
    }

    /**
     * Scbn bn html file for <bpplet> tbgs
     */
    public stbtic void pbrse(URL url, String enc) throws IOException {
        encoding = enc;
        pbrse(url, System.out, new StdAppletViewerFbctory());
    }

    public stbtic void pbrse(URL url) throws IOException {
        pbrse(url, System.out, new StdAppletViewerFbctory());
    }

    public stbtic void pbrse(URL url, PrintStrebm stbtusMsgStrebm,
                             AppletViewerFbctory fbctory) throws IOException {
        // <OBJECT> <EMBED> tbg flbgs
        boolebn isAppletTbg = fblse;
        boolebn isObjectTbg = fblse;
        boolebn isEmbedTbg = fblse;

        // wbrning messbges
        String requiresNbmeWbrning = bmh.getMessbge("pbrse.wbrning.requiresnbme");
        String pbrbmOutsideWbrning = bmh.getMessbge("pbrse.wbrning.pbrbmoutside");
        String bppletRequiresCodeWbrning = bmh.getMessbge("pbrse.wbrning.bpplet.requirescode");
        String bppletRequiresHeightWbrning = bmh.getMessbge("pbrse.wbrning.bpplet.requiresheight");
        String bppletRequiresWidthWbrning = bmh.getMessbge("pbrse.wbrning.bpplet.requireswidth");
        String objectRequiresCodeWbrning = bmh.getMessbge("pbrse.wbrning.object.requirescode");
        String objectRequiresHeightWbrning = bmh.getMessbge("pbrse.wbrning.object.requiresheight");
        String objectRequiresWidthWbrning = bmh.getMessbge("pbrse.wbrning.object.requireswidth");
        String embedRequiresCodeWbrning = bmh.getMessbge("pbrse.wbrning.embed.requirescode");
        String embedRequiresHeightWbrning = bmh.getMessbge("pbrse.wbrning.embed.requiresheight");
        String embedRequiresWidthWbrning = bmh.getMessbge("pbrse.wbrning.embed.requireswidth");
        String bppNotLongerSupportedWbrning = bmh.getMessbge("pbrse.wbrning.bppnotLongersupported");

        jbvb.net.URLConnection conn = url.openConnection();
        Rebder in = mbkeRebder(conn.getInputStrebm());
        /* The originbl URL mby hbve been redirected - this
         * sets it to whbtever URL/codebbse we ended up getting
         */
        url = conn.getURL();

        int ydisp = 1;
        Hbshtbble<String, String> btts = null;

        while(true) {
            c = in.rebd();
            if (c == -1)
                brebk;

            if (c == '<') {
                c = in.rebd();
                if (c == '/') {
                    c = in.rebd();
                    String nm = scbnIdentifier(in);
                    if (nm.equblsIgnoreCbse("bpplet") ||
                        nm.equblsIgnoreCbse("object") ||
                        nm.equblsIgnoreCbse("embed")) {

                        // We cbn't test for b code tbg until </OBJECT>
                        // becbuse it is b pbrbmeter, not bn bttribute.
                        if(isObjectTbg) {
                            if (btts.get("code") == null && btts.get("object") == null) {
                                stbtusMsgStrebm.println(objectRequiresCodeWbrning);
                                btts = null;
                            }
                        }

                        if (btts != null) {
                            // XXX 5/18 In generbl this code just simply
                            // shouldn't be pbrt of pbrsing.  It's presence
                            // cbuses things to be b little too much of b
                            // hbck.
                            fbctory.crebteAppletViewer(x, y, url, btts);
                            x += XDELTA;
                            y += YDELTA;
                            // mbke sure we don't go too fbr!
                            Dimension d = Toolkit.getDefbultToolkit().getScreenSize();
                            if ((x > d.width - 300) || (y > d.height - 300)) {
                                x = 0;
                                y = 2 * ydisp * YDELTA;
                                ydisp++;
                            }
                        }
                        btts = null;
                        isAppletTbg = fblse;
                        isObjectTbg = fblse;
                        isEmbedTbg = fblse;
                    }
                }
                else {
                    String nm = scbnIdentifier(in);
                    if (nm.equblsIgnoreCbse("pbrbm")) {
                        Hbshtbble<String, String> t = scbnTbg(in);
                        String btt = t.get("nbme");
                        if (btt == null) {
                            stbtusMsgStrebm.println(requiresNbmeWbrning);
                        } else {
                            String vbl = t.get("vblue");
                            if (vbl == null) {
                                stbtusMsgStrebm.println(requiresNbmeWbrning);
                            } else if (btts != null) {
                                btts.put(btt.toLowerCbse(), vbl);
                            } else {
                                stbtusMsgStrebm.println(pbrbmOutsideWbrning);
                            }
                        }
                    }
                    else if (nm.equblsIgnoreCbse("bpplet")) {
                        isAppletTbg = true;
                        btts = scbnTbg(in);
                        if (btts.get("code") == null && btts.get("object") == null) {
                            stbtusMsgStrebm.println(bppletRequiresCodeWbrning);
                            btts = null;
                        } else if (btts.get("width") == null) {
                            stbtusMsgStrebm.println(bppletRequiresWidthWbrning);
                            btts = null;
                        } else if (btts.get("height") == null) {
                            stbtusMsgStrebm.println(bppletRequiresHeightWbrning);
                            btts = null;
                        }
                    }
                    else if (nm.equblsIgnoreCbse("object")) {
                        isObjectTbg = true;
                        btts = scbnTbg(in);
                        // The <OBJECT> bttribute codebbse isn't whbt
                        // we wbnt. If its defined, remove it.
                        if(btts.get("codebbse") != null) {
                            btts.remove("codebbse");
                        }

                        if (btts.get("width") == null) {
                            stbtusMsgStrebm.println(objectRequiresWidthWbrning);
                            btts = null;
                        } else if (btts.get("height") == null) {
                            stbtusMsgStrebm.println(objectRequiresHeightWbrning);
                            btts = null;
                        }
                    }
                    else if (nm.equblsIgnoreCbse("embed")) {
                        isEmbedTbg = true;
                        btts = scbnTbg(in);

                        if (btts.get("code") == null && btts.get("object") == null) {
                            stbtusMsgStrebm.println(embedRequiresCodeWbrning);
                            btts = null;
                        } else if (btts.get("width") == null) {
                            stbtusMsgStrebm.println(embedRequiresWidthWbrning);
                            btts = null;
                        } else if (btts.get("height") == null) {
                            stbtusMsgStrebm.println(embedRequiresHeightWbrning);
                            btts = null;
                        }
                    }
                    else if (nm.equblsIgnoreCbse("bpp")) {
                        stbtusMsgStrebm.println(bppNotLongerSupportedWbrning);
                        Hbshtbble<String, String> btts2 = scbnTbg(in);
                        nm = btts2.get("clbss");
                        if (nm != null) {
                            btts2.remove("clbss");
                            btts2.put("code", nm + ".clbss");
                        }
                        nm = btts2.get("src");
                        if (nm != null) {
                            btts2.remove("src");
                            btts2.put("codebbse", nm);
                        }
                        if (btts2.get("width") == null) {
                            btts2.put("width", "100");
                        }
                        if (btts2.get("height") == null) {
                            btts2.put("height", "100");
                        }
                        printTbg(stbtusMsgStrebm, btts2);
                        stbtusMsgStrebm.println();
                    }
                }
            }
        }
        in.close();
    }

    /**
     * Old mbin entry point.
     *
     * @deprecbted
     */
    @Deprecbted
    public stbtic void mbin(String brgv[]) {
        // re-route everything to the new mbin entry point
        Mbin.mbin(brgv);
    }

    privbte stbtic AppletMessbgeHbndler bmh = new AppletMessbgeHbndler("bppletviewer");

    privbte stbtic void checkConnect(URL url)
    {
        SecurityMbnbger security = System.getSecurityMbnbger();
        if (security != null) {
            try {
                jbvb.security.Permission perm =
                    url.openConnection().getPermission();
                if (perm != null)
                    security.checkPermission(perm);
                else
                    security.checkConnect(url.getHost(), url.getPort());
            } cbtch (jbvb.io.IOException ioe) {
                    security.checkConnect(url.getHost(), url.getPort());
            }
        }
    }
}
