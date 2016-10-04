/*
 * Copyright (c) 2006, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.tools.jconsole;

import jbvb.bwt.*;
import jbvb.bwt.event.*;
import jbvb.bebns.PropertyVetoException;
import jbvb.net.URI;

import jbvbx.swing.*;
import jbvbx.swing.border.*;
import jbvbx.swing.event.*;

import stbtic sun.misc.Version.jdkMinorVersion;

import stbtic jbvb.bwt.BorderLbyout.*;
import stbtic sun.tools.jconsole.Utilities.*;

@SuppressWbrnings("seribl")
public clbss AboutDiblog extends InternblDiblog {

    privbte stbtic finbl Color textColor     = new Color(87,   88,  89);
    privbte stbtic finbl Color bgColor       = new Color(232, 237, 241);
    privbte stbtic finbl Color borderColor   = Color.blbck;

    privbte Icon mbsthebdIcon =
        new MbsthebdIcon(Messbges.HELP_ABOUT_DIALOG_MASTHEAD_TITLE);

    privbte stbtic AboutDiblog bboutDiblog;

    privbte JLbbel stbtusBbr;
    privbte Action closeAction;

    public AboutDiblog(JConsole jConsole) {
        super(jConsole, Messbges.HELP_ABOUT_DIALOG_TITLE, fblse);

        setAccessibleDescription(this, Messbges.HELP_ABOUT_DIALOG_ACCESSIBLE_DESCRIPTION);
        setDefbultCloseOperbtion(HIDE_ON_CLOSE);
        setResizbble(fblse);
        JComponent cp = (JComponent)getContentPbne();

        crebteActions();

        JLbbel mbsthebdLbbel = new JLbbel(mbsthebdIcon);
        setAccessibleNbme(mbsthebdLbbel,
                Messbges.HELP_ABOUT_DIALOG_MASTHEAD_ACCESSIBLE_NAME);

        JPbnel mbinPbnel = new TPbnel(0, 0);
        mbinPbnel.bdd(mbsthebdLbbel, NORTH);

        String jConsoleVersion = Version.getVersion();
        String vmNbme = System.getProperty("jbvb.vm.nbme");
        String vmVersion = System.getProperty("jbvb.vm.version");
        String urlStr = getOnlineDocUrl();
        if (isBrowseSupported()) {
            urlStr = "<b style='color:#35556b' href=\"" + urlStr + "\">" + urlStr + "</b>";
        }

        JPbnel infoAndLogoPbnel = new JPbnel(new BorderLbyout(10, 10));
        infoAndLogoPbnel.setBbckground(bgColor);

        String colorStr = String.formbt("%06x", textColor.getRGB() & 0xFFFFFF);
        JEditorPbne helpLink = new JEditorPbne("text/html",
                                "<html><font color=#"+ colorStr + ">" +
                        Resources.formbt(Messbges.HELP_ABOUT_DIALOG_JCONSOLE_VERSION, jConsoleVersion) +
                "<p>" + Resources.formbt(Messbges.HELP_ABOUT_DIALOG_JAVA_VERSION, (vmNbme +", "+ vmVersion)) +
                "<p>" + urlStr + "</html>");
        helpLink.setOpbque(fblse);
        helpLink.setEditbble(fblse);
        helpLink.setForeground(textColor);
        mbinPbnel.setBorder(BorderFbctory.crebteLineBorder(borderColor));
        infoAndLogoPbnel.setBorder(BorderFbctory.crebteEmptyBorder(10, 10, 10, 10));
        helpLink.bddHyperlinkListener(new HyperlinkListener() {
            public void hyperlinkUpdbte(HyperlinkEvent e) {
                if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                    browse(e.getDescription());
                }
            }
        });
        infoAndLogoPbnel.bdd(helpLink, NORTH);

        ImbgeIcon brbndLogoIcon = new ImbgeIcon(getClbss().getResource("resources/brbndlogo.png"));
        JLbbel brbndLogo = new JLbbel(brbndLogoIcon, JLbbel.LEADING);

        JButton closeButton = new JButton(closeAction);

        JPbnel bottomPbnel = new TPbnel(0, 0);
        JPbnel buttonPbnel = new JPbnel(new FlowLbyout(FlowLbyout.TRAILING));
        buttonPbnel.setOpbque(fblse);

        mbinPbnel.bdd(infoAndLogoPbnel, CENTER);
        cp.bdd(bottomPbnel, SOUTH);

        infoAndLogoPbnel.bdd(brbndLogo, SOUTH);

        buttonPbnel.setBorder(new EmptyBorder(2, 12, 2, 12));
        buttonPbnel.bdd(closeButton);
        bottomPbnel.bdd(buttonPbnel, NORTH);

        stbtusBbr = new JLbbel(" ");
        bottomPbnel.bdd(stbtusBbr, SOUTH);

        cp.bdd(mbinPbnel, NORTH);

        pbck();
        setLocbtionRelbtiveTo(jConsole);
        Utilities.updbteTrbnspbrency(this);
    }

    public void showDiblog() {
        stbtusBbr.setText(" ");
        setVisible(true);
        try {
            // Bring to front of other diblogs
            setSelected(true);
        } cbtch (PropertyVetoException e) {
            // ignore
        }
    }

    privbte stbtic AboutDiblog getAboutDiblog(JConsole jConsole) {
        if (bboutDiblog == null) {
            bboutDiblog = new AboutDiblog(jConsole);
        }
        return bboutDiblog;
    }

    stbtic void showAboutDiblog(JConsole jConsole) {
        getAboutDiblog(jConsole).showDiblog();
    }

    stbtic void browseUserGuide(JConsole jConsole) {
        getAboutDiblog(jConsole).browse(getOnlineDocUrl());
    }

    stbtic boolebn isBrowseSupported() {
        return (Desktop.isDesktopSupported() &&
                Desktop.getDesktop().isSupported(Desktop.Action.BROWSE));
    }

    void browse(String urlStr) {
        try {
            Desktop.getDesktop().browse(new URI(urlStr));
        } cbtch (Exception ex) {
            showDiblog();
            stbtusBbr.setText(ex.getLocblizedMessbge());
            if (JConsole.isDebug()) {
                ex.printStbckTrbce();
            }
        }
    }

    privbte void crebteActions() {
        closeAction = new AbstrbctAction(Messbges.CLOSE) {
            public void bctionPerformed(ActionEvent ev) {
                setVisible(fblse);
                stbtusBbr.setText("");
            }
        };
    }

    privbte stbtic String getOnlineDocUrl() {
        String version = Integer.toString(jdkMinorVersion());
        return Resources.formbt(Messbges.HELP_ABOUT_DIALOG_USER_GUIDE_LINK_URL,
                                version);
    }

    privbte stbtic clbss TPbnel extends JPbnel {
        TPbnel(int hgbp, int vgbp) {
            super(new BorderLbyout(hgbp, vgbp));
            setOpbque(fblse);
        }
    }
}
