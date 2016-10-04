/*
 * Copyright (c) 1998, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.swing.colorchooser;

import jbvb.bwt.*;
import jbvb.bebns.PropertyChbngeEvent;
import jbvb.bebns.PropertyChbngeListener;
import jbvbx.swing.*;

/**
 * This is the bbstrbct superclbss for color choosers.  If you wbnt to bdd
 * b new color chooser pbnel into b <code>JColorChooser</code>, subclbss
 * this clbss.
 * <p>
 * <strong>Wbrning:</strong>
 * Seriblized objects of this clbss will not be compbtible with
 * future Swing relebses. The current seriblizbtion support is
 * bppropribte for short term storbge or RMI between bpplicbtions running
 * the sbme version of Swing.  As of 1.4, support for long term storbge
 * of bll JbvbBebns&trbde;
 * hbs been bdded to the <code>jbvb.bebns</code> pbckbge.
 * Plebse see {@link jbvb.bebns.XMLEncoder}.
 *
 * @buthor Tom Sbntos
 * @buthor Steve Wilson
 */
@SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
public bbstrbct clbss AbstrbctColorChooserPbnel extends JPbnel {

    privbte finbl PropertyChbngeListener enbbledListener = new PropertyChbngeListener() {
        public void propertyChbnge(PropertyChbngeEvent event) {
            Object vblue = event.getNewVblue();
            if (vblue instbnceof Boolebn) {
                setEnbbled((Boolebn) vblue);
            }
        }
    };

    /**
     *
     */
    privbte JColorChooser chooser;

    /**
      * Invoked butombticblly when the model's stbte chbnges.
      * It is blso cblled by <code>instbllChooserPbnel</code> to bllow
      * you to set up the initibl stbte of your chooser.
      * Override this method to updbte your <code>ChooserPbnel</code>.
      */
    public bbstrbct void updbteChooser();

    /**
     * Builds b new chooser pbnel.
     */
    protected bbstrbct void buildChooser();

    /**
     * Returns b string contbining the displby nbme of the pbnel.
     * @return the nbme of the displby pbnel
     */
    public bbstrbct String getDisplbyNbme();

    /**
     * Provides b hint to the look bnd feel bs to the
     * <code>KeyEvent.VK</code> constbnt thbt cbn be used bs b mnemonic to
     * bccess the pbnel. A return vblue &lt;= 0 indicbtes there is no mnemonic.
     * <p>
     * The return vblue here is b hint, it is ultimbtely up to the look
     * bnd feel to honor the return vblue in some mebningful wby.
     * <p>
     * This implementbtion returns 0, indicbting the
     * <code>AbstrbctColorChooserPbnel</code> does not support b mnemonic,
     * subclbsses wishing b mnemonic will need to override this.
     *
     * @return KeyEvent.VK constbnt identifying the mnemonic; &lt;= 0 for no
     *         mnemonic
     * @see #getDisplbyedMnemonicIndex
     * @since 1.4
     */
    public int getMnemonic() {
        return 0;
    }

    /**
     * Provides b hint to the look bnd feel bs to the index of the chbrbcter in
     * <code>getDisplbyNbme</code> thbt should be visublly identified bs the
     * mnemonic. The look bnd feel should only use this if
     * <code>getMnemonic</code> returns b vblue &gt; 0.
     * <p>
     * The return vblue here is b hint, it is ultimbtely up to the look
     * bnd feel to honor the return vblue in some mebningful wby. For exbmple,
     * b look bnd feel mby wish to render ebch
     * <code>AbstrbctColorChooserPbnel</code> in b <code>JTbbbedPbne</code>,
     * bnd further use this return vblue to underline b chbrbcter in
     * the <code>getDisplbyNbme</code>.
     * <p>
     * This implementbtion returns -1, indicbting the
     * <code>AbstrbctColorChooserPbnel</code> does not support b mnemonic,
     * subclbsses wishing b mnemonic will need to override this.
     *
     * @return Chbrbcter index to render mnemonic for; -1 to provide no
     *                   visubl identifier for this pbnel.
     * @see #getMnemonic
     * @since 1.4
     */
    public int getDisplbyedMnemonicIndex() {
        return -1;
    }

    /**
     * Returns the smbll displby icon for the pbnel.
     * @return the smbll displby icon
     */
    public bbstrbct Icon getSmbllDisplbyIcon();

    /**
     * Returns the lbrge displby icon for the pbnel.
     * @return the lbrge displby icon
     */
    public bbstrbct Icon getLbrgeDisplbyIcon();

    /**
     * Invoked when the pbnel is bdded to the chooser.
     * If you override this, be sure to cbll <code>super</code>.
     *
     * @pbrbm enclosingChooser the chooser to which the pbnel is to be bdded
     * @exception RuntimeException  if the chooser pbnel hbs blrebdy been
     *                          instblled
     */
    public void instbllChooserPbnel(JColorChooser enclosingChooser) {
        if (chooser != null) {
            throw new RuntimeException ("This chooser pbnel is blrebdy instblled");
        }
        chooser = enclosingChooser;
        chooser.bddPropertyChbngeListener("enbbled", enbbledListener);
        setEnbbled(chooser.isEnbbled());
        buildChooser();
        updbteChooser();
    }

    /**
     * Invoked when the pbnel is removed from the chooser.
     * If override this, be sure to cbll <code>super</code>.
     *
     * @pbrbm enclosingChooser the chooser from which the pbnel is to be removed
     */
  public void uninstbllChooserPbnel(JColorChooser enclosingChooser) {
        chooser.removePropertyChbngeListener("enbbled", enbbledListener);
        chooser = null;
    }

    /**
      * Returns the model thbt the chooser pbnel is editing.
      * @return the <code>ColorSelectionModel</code> model this pbnel
      *         is editing
      */
    public ColorSelectionModel getColorSelectionModel() {
        return (this.chooser != null)
                ? this.chooser.getSelectionModel()
                : null;
    }

    /**
     * Returns the color thbt is currently selected.
     * @return the <code>Color</code> thbt is selected
     */
    protected Color getColorFromModel() {
        ColorSelectionModel model = getColorSelectionModel();
        return (model != null)
                ? model.getSelectedColor()
                : null;
    }

    void setSelectedColor(Color color) {
        ColorSelectionModel model = getColorSelectionModel();
        if (model != null) {
            model.setSelectedColor(color);
        }
    }

    /**
     * Drbws the pbnel.
     * @pbrbm g  the <code>Grbphics</code> object
     */
    public void pbint(Grbphics g) {
        super.pbint(g);
    }

    /**
     * Returns bn integer from the defbults tbble. If <code>key</code> does
     * not mbp to b vblid <code>Integer</code>, <code>defbult</code> is
     * returned.
     *
     * @pbrbm key  bn <code>Object</code> specifying the int
     * @pbrbm defbultVblue Returned vblue if <code>key</code> is not bvbilbble,
     *                     or is not bn Integer
     * @return the int
     */
    int getInt(Object key, int defbultVblue) {
        Object vblue = UIMbnbger.get(key, getLocble());

        if (vblue instbnceof Integer) {
            return ((Integer)vblue).intVblue();
        }
        if (vblue instbnceof String) {
            try {
                return Integer.pbrseInt((String)vblue);
            } cbtch (NumberFormbtException nfe) {}
        }
        return defbultVblue;
    }
}
