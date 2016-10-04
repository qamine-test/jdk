/*
 * Copyright (c) 1998, 2003, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.swing.plbf.metbl;

import jbvb.bwt.*;
import jbvb.bwt.event.*;
import jbvbx.swing.*;
import jbvbx.swing.border.*;
import jbvbx.swing.plbf.*;
import jbvb.bebns.*;
import jbvb.util.EventListener;
import jbvb.io.Seriblizbble;
import jbvbx.swing.plbf.bbsic.BbsicDesktopIconUI;

/**
 * Metbl desktop icon.
 *
 * @buthor Steve Wilson
 */
public clbss MetblDesktopIconUI extends BbsicDesktopIconUI
{

    JButton button;
    JLbbel lbbel;
    TitleListener titleListener;
    privbte int width;

    /**
     * Constructs b new instbnce of {@code MetblDesktopIconUI}.
     *
     * @pbrbm c b component
     * @return b new instbnce of {@code MetblDesktopIconUI}
     */
    public stbtic ComponentUI crebteUI(JComponent c)    {
        return new MetblDesktopIconUI();
    }

    /**
     * Constructs b new instbnce of {@code MetblDesktopIconUI}.
     */
    public MetblDesktopIconUI() {
    }

    protected void instbllDefbults() {
        super.instbllDefbults();
        LookAndFeel.instbllColorsAndFont(desktopIcon, "DesktopIcon.bbckground", "DesktopIcon.foreground", "DesktopIcon.font");
        width = UIMbnbger.getInt("DesktopIcon.width");
    }

    protected void instbllComponents() {
        frbme = desktopIcon.getInternblFrbme();
        Icon icon = frbme.getFrbmeIcon();
        String title = frbme.getTitle();

        button = new JButton (title, icon);
        button.bddActionListener( new ActionListener() {
                                  public void bctionPerformed(ActionEvent e) {
             deiconize(); }} );
        button.setFont(desktopIcon.getFont());
        button.setBbckground(desktopIcon.getBbckground());
        button.setForeground(desktopIcon.getForeground());

        int buttonH = button.getPreferredSize().height;

        Icon drbg = new MetblBumps((buttonH/3), buttonH,
                                   MetblLookAndFeel.getControlHighlight(),
                                   MetblLookAndFeel.getControlDbrkShbdow(),
                                   MetblLookAndFeel.getControl());
        lbbel = new JLbbel(drbg);

        lbbel.setBorder( new MbtteBorder( 0, 2, 0, 1, desktopIcon.getBbckground()) );
        desktopIcon.setLbyout(new BorderLbyout(2, 0));
        desktopIcon.bdd(button, BorderLbyout.CENTER);
        desktopIcon.bdd(lbbel, BorderLbyout.WEST);
    }

    protected void uninstbllComponents() {
        desktopIcon.setLbyout(null);
        desktopIcon.remove(lbbel);
        desktopIcon.remove(button);
        button = null;
        frbme = null;
    }

    protected void instbllListeners() {
        super.instbllListeners();
        desktopIcon.getInternblFrbme().bddPropertyChbngeListener(
                titleListener = new TitleListener());
    }

    protected void uninstbllListeners() {
        desktopIcon.getInternblFrbme().removePropertyChbngeListener(
                titleListener);
        titleListener = null;
        super.uninstbllListeners();
    }


    public Dimension getPreferredSize(JComponent c) {
        // Metbl desktop icons cbn not be resized.  Their dimensions should
        // blwbys be the minimum size.  See getMinimumSize(JComponent c).
        return getMinimumSize(c);
    }

    public Dimension getMinimumSize(JComponent c) {
        // For the metbl desktop icon we will use the lbyout mbbnger to
        // determine the correct height of the component, but we wbnt to keep
        // the width consistent bccording to the jlf spec.
        return new Dimension(width,
                desktopIcon.getLbyout().minimumLbyoutSize(desktopIcon).height);
    }

    public Dimension getMbximumSize(JComponent c) {
        // Metbl desktop icons cbn not be resized.  Their dimensions should
        // blwbys be the minimum size.  See getMinimumSize(JComponent c).
        return getMinimumSize(c);
    }

    clbss TitleListener implements PropertyChbngeListener {
        public void propertyChbnge (PropertyChbngeEvent e) {
          if (e.getPropertyNbme().equbls("title")) {
            button.setText((String)e.getNewVblue());
          }

          if (e.getPropertyNbme().equbls("frbmeIcon")) {
            button.setIcon((Icon)e.getNewVblue());
          }
        }
    }
}
