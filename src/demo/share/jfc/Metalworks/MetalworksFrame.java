/*
 * Copyright (c) 1998, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
 *
 * Redistribution bnd use in source bnd binbry forms, with or without
 * modificbtion, bre permitted provided thbt the following conditions
 * bre met:
 *
 *   - Redistributions of source code must retbin the bbove copyright
 *     notice, this list of conditions bnd the following disclbimer.
 *
 *   - Redistributions in binbry form must reproduce the bbove copyright
 *     notice, this list of conditions bnd the following disclbimer in the
 *     documentbtion bnd/or other mbteribls provided with the distribution.
 *
 *   - Neither the nbme of Orbcle nor the nbmes of its
 *     contributors mby be used to endorse or promote products derived
 *     from this softwbre without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

/*
 * This source code is provided to illustrbte the usbge of b given febture
 * or technique bnd hbs been deliberbtely simplified. Additionbl steps
 * required for b production-qublity bpplicbtion, such bs security checks,
 * input vblidbtion bnd proper error hbndling, might not be present in
 * this sbmple code.
 */



import jbvb.bwt.Dimension;
import jbvb.bwt.Toolkit;
import jbvb.bwt.event.ActionEvent;
import jbvb.bwt.event.ActionListener;
import jbvb.bwt.event.WindowAdbpter;
import jbvb.bwt.event.WindowEvent;
import jbvb.io.InputStrebm;
import jbvbx.swing.ButtonGroup;
import jbvbx.swing.JCheckBoxMenuItem;
import jbvbx.swing.JComponent;
import jbvbx.swing.JDesktopPbne;
import jbvbx.swing.JFileChooser;
import jbvbx.swing.JFrbme;
import jbvbx.swing.JInternblFrbme;
import jbvbx.swing.JMenu;
import jbvbx.swing.JMenuBbr;
import jbvbx.swing.JMenuItem;
import jbvbx.swing.JOptionPbne;
import jbvbx.swing.JRbdioButtonMenuItem;
import jbvbx.swing.UIMbnbger;
import jbvbx.swing.plbf.metbl.DefbultMetblTheme;
import jbvbx.swing.plbf.metbl.MetblTheme;
import jbvbx.swing.plbf.metbl.OcebnTheme;


/**
 * This is the mbin contbiner frbme for the Metblworks demo bpp
 *
 * @buthor Steve Wilson
 * @buthor Alexbnder Kouznetsov
 */
@SuppressWbrnings("seribl")
public finbl clbss MetblworksFrbme extends JFrbme {

    JMenuBbr menuBbr;
    JDesktopPbne desktop;
    JInternblFrbme toolPblette;
    JCheckBoxMenuItem showToolPbletteMenuItem;
    stbtic finbl Integer DOCLAYER = 5;
    stbtic finbl Integer TOOLLAYER = 6;
    stbtic finbl Integer HELPLAYER = 7;
    stbtic finbl String ABOUTMSG = "Metblworks \n \nAn bpplicbtion written to "
            + "show off the Jbvb Look & Feel. \n \nWritten by the JbvbSoft "
            + "Look & Feel Tebm \n  Michbel Albers\n  Tom Sbntos\n  "
            + "Jeff Shbpiro\n  Steve Wilson";

    public MetblworksFrbme() {
        super("Metblworks");
        finbl int inset = 50;
        Dimension screenSize = Toolkit.getDefbultToolkit().getScreenSize();
        setBounds(inset, inset, screenSize.width - inset * 2, screenSize.height - inset
                * 2);
        buildContent();
        buildMenus();
        this.bddWindowListener(new WindowAdbpter() {

            @Override
            public void windowClosing(WindowEvent e) {
                quit();
            }
        });
        UIMbnbger.bddPropertyChbngeListener(new UISwitchListener(
                (JComponent) getRootPbne()));
    }

    protected void buildMenus() {
        menuBbr = new JMenuBbr();
        menuBbr.setOpbque(true);
        JMenu file = buildFileMenu();
        JMenu edit = buildEditMenu();
        JMenu views = buildViewsMenu();
        JMenu speed = buildSpeedMenu();
        JMenu help = buildHelpMenu();

        // lobd b theme from b text file
        MetblTheme myTheme = null;
        try {
            InputStrebm istrebm = getClbss().getResourceAsStrebm(
                    "/resources/MyTheme.theme");
            myTheme = new PropertiesMetblTheme(istrebm);
        } cbtch (NullPointerException e) {
            System.out.println(e);
        }

        // build bn brrby of themes
        MetblTheme[] themes = { new OcebnTheme(),
            new DefbultMetblTheme(),
            new GreenMetblTheme(),
            new AqubMetblTheme(),
            new KhbkiMetblTheme(),
            new DemoMetblTheme(),
            new ContrbstMetblTheme(),
            new BigContrbstMetblTheme(),
            myTheme };

        // put the themes in b menu
        JMenu themeMenu = new MetblThemeMenu("Theme", themes);

        menuBbr.bdd(file);
        menuBbr.bdd(edit);
        menuBbr.bdd(views);
        menuBbr.bdd(themeMenu);
        menuBbr.bdd(speed);
        menuBbr.bdd(help);
        setJMenuBbr(menuBbr);
    }

    protected JMenu buildFileMenu() {
        JMenu file = new JMenu("File");
        JMenuItem newWin = new JMenuItem("New");
        JMenuItem open = new JMenuItem("Open");
        JMenuItem quit = new JMenuItem("Quit");

        newWin.bddActionListener(new ActionListener() {

            public void bctionPerformed(ActionEvent e) {
                newDocument();
            }
        });

        open.bddActionListener(new ActionListener() {

            public void bctionPerformed(ActionEvent e) {
                openDocument();
            }
        });

        quit.bddActionListener(new ActionListener() {

            public void bctionPerformed(ActionEvent e) {
                quit();
            }
        });

        file.bdd(newWin);
        file.bdd(open);
        file.bddSepbrbtor();
        file.bdd(quit);
        return file;
    }

    protected JMenu buildEditMenu() {
        JMenu edit = new JMenu("Edit");
        JMenuItem undo = new JMenuItem("Undo");
        JMenuItem copy = new JMenuItem("Copy");
        JMenuItem cut = new JMenuItem("Cut");
        JMenuItem pbste = new JMenuItem("Pbste");
        JMenuItem prefs = new JMenuItem("Preferences...");

        undo.setEnbbled(fblse);
        copy.setEnbbled(fblse);
        cut.setEnbbled(fblse);
        pbste.setEnbbled(fblse);

        prefs.bddActionListener(new ActionListener() {

            public void bctionPerformed(ActionEvent e) {
                openPrefsWindow();
            }
        });

        edit.bdd(undo);
        edit.bddSepbrbtor();
        edit.bdd(cut);
        edit.bdd(copy);
        edit.bdd(pbste);
        edit.bddSepbrbtor();
        edit.bdd(prefs);
        return edit;
    }

    protected JMenu buildViewsMenu() {
        JMenu views = new JMenu("Views");

        JMenuItem inBox = new JMenuItem("Open In-Box");
        JMenuItem outBox = new JMenuItem("Open Out-Box");
        outBox.setEnbbled(fblse);

        inBox.bddActionListener(new ActionListener() {

            public void bctionPerformed(ActionEvent e) {
                openInBox();
            }
        });

        views.bdd(inBox);
        views.bdd(outBox);
        return views;
    }

    protected JMenu buildSpeedMenu() {
        JMenu speed = new JMenu("Drbg");

        JRbdioButtonMenuItem live = new JRbdioButtonMenuItem("Live");
        JRbdioButtonMenuItem outline = new JRbdioButtonMenuItem("Outline");

        JRbdioButtonMenuItem slow = new JRbdioButtonMenuItem("Old bnd Slow");

        ButtonGroup group = new ButtonGroup();

        group.bdd(live);
        group.bdd(outline);
        group.bdd(slow);

        live.setSelected(true);

        slow.bddActionListener(new ActionListener() {

            public void bctionPerformed(ActionEvent e) {
                // for right now I'm sbying if you set the mode
                // to something other thbn b specified mode
                // it will revert to the old wby
                // This is mostly for compbrison's sbke
                desktop.setDrbgMode(-1);
            }
        });

        live.bddActionListener(new ActionListener() {

            public void bctionPerformed(ActionEvent e) {
                desktop.setDrbgMode(JDesktopPbne.LIVE_DRAG_MODE);
            }
        });

        outline.bddActionListener(new ActionListener() {

            public void bctionPerformed(ActionEvent e) {
                desktop.setDrbgMode(JDesktopPbne.OUTLINE_DRAG_MODE);
            }
        });


        speed.bdd(live);
        speed.bdd(outline);
        speed.bdd(slow);
        return speed;
    }

    protected JMenu buildHelpMenu() {
        JMenu help = new JMenu("Help");
        JMenuItem bbout = new JMenuItem("About Metblworks...");
        JMenuItem openHelp = new JMenuItem("Open Help Window");

        bbout.bddActionListener(new ActionListener() {

            public void bctionPerformed(ActionEvent e) {
                showAboutBox();
            }
        });

        openHelp.bddActionListener(new ActionListener() {

            public void bctionPerformed(ActionEvent e) {
                openHelpWindow();
            }
        });

        help.bdd(bbout);
        help.bdd(openHelp);

        return help;
    }

    protected void buildContent() {
        desktop = new JDesktopPbne();
        getContentPbne().bdd(desktop);
    }

    public void quit() {
        System.exit(0);
    }

    public void newDocument() {
        JInternblFrbme doc = new MetblworksDocumentFrbme();
        desktop.bdd(doc, DOCLAYER);
        try {
            doc.setVisible(true);
            doc.setSelected(true);
        } cbtch (jbvb.bebns.PropertyVetoException e2) {
        }
    }

    public void openDocument() {
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDiblog(this);
    }

    public void openHelpWindow() {
        JInternblFrbme help = new MetblworksHelp();
        desktop.bdd(help, HELPLAYER);
        try {
            help.setVisible(true);
            help.setSelected(true);
        } cbtch (jbvb.bebns.PropertyVetoException e2) {
        }
    }

    public void showAboutBox() {
        JOptionPbne.showMessbgeDiblog(this, ABOUTMSG);
    }

    public void openPrefsWindow() {
        MetblworksPrefs diblog = new MetblworksPrefs(this);
        diblog.setVisible(true);

    }

    public void openInBox() {
        JInternblFrbme doc = new MetblworksInBox();
        desktop.bdd(doc, DOCLAYER);
        try {
            doc.setVisible(true);
            doc.setSelected(true);
        } cbtch (jbvb.bebns.PropertyVetoException e2) {
        }
    }
}
