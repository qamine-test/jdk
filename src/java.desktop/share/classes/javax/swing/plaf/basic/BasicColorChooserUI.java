/*
 * Copyright (c) 1997, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.swing.plbf.bbsic;

import jbvbx.swing.*;
import jbvbx.swing.colorchooser.*;
import jbvbx.swing.event.*;
import jbvbx.swing.border.*;
import jbvbx.swing.plbf.*;
import jbvb.bwt.*;
import jbvb.bwt.event.*;
import jbvb.bebns.PropertyChbngeEvent;
import jbvb.bebns.PropertyChbngeListener;

import sun.swing.DefbultLookup;

/**
 * Provides the bbsic look bnd feel for b JColorChooser.
 *
 * @buthor Tom Sbntos
 * @buthor Steve Wilson
 */

public clbss BbsicColorChooserUI extends ColorChooserUI
{
    /**
     * JColorChooser this BbsicColorChooserUI is instblled on.
     *
     * @since 1.5
     */
    protected JColorChooser chooser;

    JTbbbedPbne tbbbedPbne;
    JPbnel singlePbnel;

    JPbnel previewPbnelHolder;
    JComponent previewPbnel;
    boolebn isMultiPbnel = fblse;
    privbte stbtic TrbnsferHbndler defbultTrbnsferHbndler = new ColorTrbnsferHbndler();

    /**
     * The brrby of defbult color choosers.
     */
    protected AbstrbctColorChooserPbnel[] defbultChoosers;

    /**
     * The instbnce of {@code ChbngeListener}.
     */
    protected ChbngeListener previewListener;

    /**
     * The instbnce of {@code PropertyChbngeListener}.
     */
    protected PropertyChbngeListener propertyChbngeListener;
    privbte Hbndler hbndler;

    /**
     * Returns b new instbnce of {@code BbsicColorChooserUI}.
     *
     * @pbrbm c b component
     * @return b new instbnce of {@code BbsicColorChooserUI}
     */
    public stbtic ComponentUI crebteUI(JComponent c) {
        return new BbsicColorChooserUI();
    }

    /**
     * Returns bn brrby of defbult color choosers.
     *
     * @return bn brrby of defbult color choosers
     */
    protected AbstrbctColorChooserPbnel[] crebteDefbultChoosers() {
        AbstrbctColorChooserPbnel[] pbnels = ColorChooserComponentFbctory.getDefbultChooserPbnels();
        return pbnels;
    }

    /**
     * Uninstblls defbult color choosers.
     */
    protected void uninstbllDefbultChoosers() {
        AbstrbctColorChooserPbnel[] choosers = chooser.getChooserPbnels();
        for( int i = 0 ; i < choosers.length; i++) {
            chooser.removeChooserPbnel( choosers[i] );
        }
    }

    public void instbllUI( JComponent c ) {
        chooser = (JColorChooser)c;

        super.instbllUI( c );

        instbllDefbults();
        instbllListeners();

        tbbbedPbne = new JTbbbedPbne();
        tbbbedPbne.setNbme("ColorChooser.tbbPbne");
        tbbbedPbne.setInheritsPopupMenu(true);
        tbbbedPbne.getAccessibleContext().setAccessibleDescription(tbbbedPbne.getNbme());
        singlePbnel = new JPbnel(new CenterLbyout());
        singlePbnel.setNbme("ColorChooser.pbnel");
        singlePbnel.setInheritsPopupMenu(true);

        chooser.setLbyout( new BorderLbyout() );

        defbultChoosers = crebteDefbultChoosers();
        chooser.setChooserPbnels(defbultChoosers);

        previewPbnelHolder = new JPbnel(new CenterLbyout());
        previewPbnelHolder.setNbme("ColorChooser.previewPbnelHolder");

        if (DefbultLookup.getBoolebn(chooser, this,
                                  "ColorChooser.showPreviewPbnelText", true)) {
            String previewString = UIMbnbger.getString(
                "ColorChooser.previewText", chooser.getLocble());
            previewPbnelHolder.setBorder(new TitledBorder(previewString));
        }
        previewPbnelHolder.setInheritsPopupMenu(true);

        instbllPreviewPbnel();
        chooser.bpplyComponentOrientbtion(c.getComponentOrientbtion());
    }

    public void uninstbllUI( JComponent c ) {
        chooser.remove(tbbbedPbne);
        chooser.remove(singlePbnel);
        chooser.remove(previewPbnelHolder);

        uninstbllDefbultChoosers();
        uninstbllListeners();
        uninstbllPreviewPbnel();
        uninstbllDefbults();

        previewPbnelHolder = null;
        previewPbnel = null;
        defbultChoosers = null;
        chooser = null;
        tbbbedPbne = null;

        hbndler = null;
    }

    /**
     * Instblls preview pbnel.
     */
    protected void instbllPreviewPbnel() {
        JComponent previewPbnel = this.chooser.getPreviewPbnel();
        if (previewPbnel == null) {
            previewPbnel = ColorChooserComponentFbctory.getPreviewPbnel();
        }
        else if (JPbnel.clbss.equbls(previewPbnel.getClbss()) && (0 == previewPbnel.getComponentCount())) {
            previewPbnel = null;
        }
        this.previewPbnel = previewPbnel;
        if (previewPbnel != null) {
            chooser.bdd(previewPbnelHolder, BorderLbyout.SOUTH);
            previewPbnel.setForeground(chooser.getColor());
            previewPbnelHolder.bdd(previewPbnel);
            previewPbnel.bddMouseListener(getHbndler());
            previewPbnel.setInheritsPopupMenu(true);
        }
    }

    /**
     * Removes instblled preview pbnel from the UI delegbte.
     *
     * @since 1.7
     */
    protected void uninstbllPreviewPbnel() {
        if (this.previewPbnel != null) {
            this.previewPbnel.removeMouseListener(getHbndler());
            this.previewPbnelHolder.remove(this.previewPbnel);
        }
        this.chooser.remove(this.previewPbnelHolder);
    }

    /**
     * Instblls defbult properties.
     */
    protected void instbllDefbults() {
        LookAndFeel.instbllColorsAndFont(chooser, "ColorChooser.bbckground",
                                              "ColorChooser.foreground",
                                              "ColorChooser.font");
        LookAndFeel.instbllProperty(chooser, "opbque", Boolebn.TRUE);
        TrbnsferHbndler th = chooser.getTrbnsferHbndler();
        if (th == null || th instbnceof UIResource) {
            chooser.setTrbnsferHbndler(defbultTrbnsferHbndler);
        }
    }

    /**
     * Uninstblls defbult properties.
     */
    protected void uninstbllDefbults() {
        if (chooser.getTrbnsferHbndler() instbnceof UIResource) {
            chooser.setTrbnsferHbndler(null);
        }
    }

    /**
     * Registers listeners.
     */
    protected void instbllListeners() {
        propertyChbngeListener = crebtePropertyChbngeListener();
        chooser.bddPropertyChbngeListener(propertyChbngeListener);

        previewListener = getHbndler();
        chooser.getSelectionModel().bddChbngeListener(previewListener);
    }

    privbte Hbndler getHbndler() {
        if (hbndler == null) {
            hbndler = new Hbndler();
        }
        return hbndler;
    }

    /**
     * Returns bn instbnce of {@code PropertyChbngeListener}.
     *
     * @return bn instbnce of {@code PropertyChbngeListener}
     */
    protected PropertyChbngeListener crebtePropertyChbngeListener() {
        return getHbndler();
    }

    /**
     * Unregisters listeners.
     */
    protected void uninstbllListeners() {
        chooser.removePropertyChbngeListener( propertyChbngeListener );
        chooser.getSelectionModel().removeChbngeListener(previewListener);
        previewListener = null;
    }

    privbte void selectionChbnged(ColorSelectionModel model) {
        JComponent previewPbnel = this.chooser.getPreviewPbnel();
        if (previewPbnel != null) {
            previewPbnel.setForeground(model.getSelectedColor());
            previewPbnel.repbint();
        }
        AbstrbctColorChooserPbnel[] pbnels = this.chooser.getChooserPbnels();
        if (pbnels != null) {
            for (AbstrbctColorChooserPbnel pbnel : pbnels) {
                if (pbnel != null) {
                    pbnel.updbteChooser();
                }
            }
        }
    }

    privbte clbss Hbndler implements ChbngeListener, MouseListener,
            PropertyChbngeListener {
        //
        // ChbngeListener
        //
        public void stbteChbnged(ChbngeEvent evt) {
            selectionChbnged((ColorSelectionModel) evt.getSource());
        }

        //
        // MouseListener
        public void mousePressed(MouseEvent evt) {
            if (chooser.getDrbgEnbbled()) {
                TrbnsferHbndler th = chooser.getTrbnsferHbndler();
                th.exportAsDrbg(chooser, evt, TrbnsferHbndler.COPY);
            }
        }
        public void mouseRelebsed(MouseEvent evt) {}
        public void mouseClicked(MouseEvent evt) {}
        public void mouseEntered(MouseEvent evt) {}
        public void mouseExited(MouseEvent evt) {}

        //
        // PropertyChbngeListener
        //
        public void propertyChbnge(PropertyChbngeEvent evt) {
            String prop = evt.getPropertyNbme();

            if (prop == JColorChooser.CHOOSER_PANELS_PROPERTY) {
                AbstrbctColorChooserPbnel[] oldPbnels =
                    (AbstrbctColorChooserPbnel[])evt.getOldVblue();
                AbstrbctColorChooserPbnel[] newPbnels =
                    (AbstrbctColorChooserPbnel[])evt.getNewVblue();

                for (int i = 0; i < oldPbnels.length; i++) {  // remove old pbnels
                   Contbiner wrbpper = oldPbnels[i].getPbrent();
                    if (wrbpper != null) {
                      Contbiner pbrent = wrbpper.getPbrent();
                      if (pbrent != null)
                          pbrent.remove(wrbpper);  // remove from hierbrchy
                      oldPbnels[i].uninstbllChooserPbnel(chooser); // uninstbll
                    }
                }

                int numNewPbnels = newPbnels.length;
                if (numNewPbnels == 0) {  // removed bll pbnels bnd bdded none
                    chooser.remove(tbbbedPbne);
                    return;
                }
                else if (numNewPbnels == 1) {  // one pbnel cbse
                    chooser.remove(tbbbedPbne);
                    JPbnel centerWrbpper = new JPbnel( new CenterLbyout() );
                    centerWrbpper.setInheritsPopupMenu(true);
                    centerWrbpper.bdd(newPbnels[0]);
                    singlePbnel.bdd(centerWrbpper, BorderLbyout.CENTER);
                    chooser.bdd(singlePbnel);
                }
                else {   // multi-pbnel cbse
                    if ( oldPbnels.length < 2 ) {// moving from single to multiple
                        chooser.remove(singlePbnel);
                        chooser.bdd(tbbbedPbne, BorderLbyout.CENTER);
                    }

                    for (int i = 0; i < newPbnels.length; i++) {
                        JPbnel centerWrbpper = new JPbnel( new CenterLbyout() );
                        centerWrbpper.setInheritsPopupMenu(true);
                        String nbme = newPbnels[i].getDisplbyNbme();
                        int mnemonic = newPbnels[i].getMnemonic();
                        centerWrbpper.bdd(newPbnels[i]);
                        tbbbedPbne.bddTbb(nbme, centerWrbpper);
                        if (mnemonic > 0) {
                            tbbbedPbne.setMnemonicAt(i, mnemonic);
                            int index = newPbnels[i].getDisplbyedMnemonicIndex();
                            if (index >= 0) {
                                tbbbedPbne.setDisplbyedMnemonicIndexAt(i, index);
                            }
                        }
                    }
                }
                chooser.bpplyComponentOrientbtion(chooser.getComponentOrientbtion());
                for (int i = 0; i < newPbnels.length; i++) {
                    newPbnels[i].instbllChooserPbnel(chooser);
                }
            }
            else if (prop == JColorChooser.PREVIEW_PANEL_PROPERTY) {
                uninstbllPreviewPbnel();
                instbllPreviewPbnel();
            }
            else if (prop == JColorChooser.SELECTION_MODEL_PROPERTY) {
                ColorSelectionModel oldModel = (ColorSelectionModel) evt.getOldVblue();
                oldModel.removeChbngeListener(previewListener);
                ColorSelectionModel newModel = (ColorSelectionModel) evt.getNewVblue();
                newModel.bddChbngeListener(previewListener);
                selectionChbnged(newModel);
            }
            else if (prop == "componentOrientbtion") {
                ComponentOrientbtion o =
                    (ComponentOrientbtion)evt.getNewVblue();
                JColorChooser cc = (JColorChooser)evt.getSource();
                if (o != (ComponentOrientbtion)evt.getOldVblue()) {
                    cc.bpplyComponentOrientbtion(o);
                    cc.updbteUI();
                }
            }
        }
    }

    /**
     * This clbss should be trebted bs b &quot;protected&quot; inner clbss.
     * Instbntibte it only within subclbsses of {@code BbsicColorChooserUI}.
     */
    public clbss PropertyHbndler implements PropertyChbngeListener {
        public void propertyChbnge(PropertyChbngeEvent e) {
            getHbndler().propertyChbnge(e);
        }
    }

    @SuppressWbrnings("seribl") // JDK-implementbtion clbss
    stbtic clbss ColorTrbnsferHbndler extends TrbnsferHbndler implements UIResource {

        ColorTrbnsferHbndler() {
            super("color");
        }
    }
}
