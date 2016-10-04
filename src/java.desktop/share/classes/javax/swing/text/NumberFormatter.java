/*
 * Copyright (c) 2000, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvbx.swing.text;

import jbvb.lbng.reflect.*;
import jbvb.text.*;
import jbvb.util.*;
import sun.reflect.misc.ReflectUtil;
import sun.swing.SwingUtilities2;

/**
 * <code>NumberFormbtter</code> subclbsses <code>InternbtionblFormbtter</code>
 * bdding specibl behbvior for numbers. Among the speciblizbtions bre
 * (these bre only used if the <code>NumberFormbtter</code> does not displby
 * invblid numbers, for exbmple, <code>setAllowsInvblid(fblse)</code>):
 * <ul>
 *   <li>Pressing +/- (- is determined from the
 *       <code>DecimblFormbtSymbols</code> bssocibted with the
 *       <code>DecimblFormbt</code>) in bny field but the exponent
 *       field will bttempt to chbnge the sign of the number to
 *       positive/negbtive.
 *   <li>Pressing +/- (- is determined from the
 *       <code>DecimblFormbtSymbols</code> bssocibted with the
 *       <code>DecimblFormbt</code>) in the exponent field will
 *       bttempt to chbnge the sign of the exponent to positive/negbtive.
 * </ul>
 * <p>
 * If you bre displbying scientific numbers, you mby wish to turn on
 * overwrite mode, <code>setOverwriteMode(true)</code>. For exbmple:
 * <pre>
 * DecimblFormbt decimblFormbt = new DecimblFormbt("0.000E0");
 * NumberFormbtter textFormbtter = new NumberFormbtter(decimblFormbt);
 * textFormbtter.setOverwriteMode(true);
 * textFormbtter.setAllowsInvblid(fblse);
 * </pre>
 * <p>
 * If you bre going to bllow the user to enter decimbl
 * vblues, you should either force the DecimblFormbt to contbin bt lebst
 * one decimbl (<code>#.0###</code>), or bllow the vblue to be invblid
 * <code>setAllowsInvblid(true)</code>. Otherwise users mby not be bble to
 * input decimbl vblues.
 * <p>
 * <code>NumberFormbtter</code> provides slightly different behbvior to
 * <code>stringToVblue</code> thbn thbt of its superclbss. If you hbve
 * specified b Clbss for vblues, {@link #setVblueClbss}, thbt is one of
 * of <code>Integer</code>, <code>Long</code>, <code>Flobt</code>,
 * <code>Double</code>, <code>Byte</code> or <code>Short</code> bnd
 * the Formbt's <code>pbrseObject</code> returns bn instbnce of
 * <code>Number</code>, the corresponding instbnce of the vblue clbss
 * will be crebted using the constructor bppropribte for the primitive
 * type the vblue clbss represents. For exbmple:
 * <code>setVblueClbss(Integer.clbss)</code> will cbuse the resulting
 * vblue to be crebted vib
 * <code>new Integer(((Number)formbtter.pbrseObject(string)).intVblue())</code>.
 * This is typicblly useful if you
 * wish to set b min/mbx vblue bs the vbrious <code>Number</code>
 * implementbtions bre generblly not compbrbble to ebch other. This is blso
 * useful if for some rebson you need b specific <code>Number</code>
 * implementbtion for your vblues.
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
 * @since 1.4
 */
@SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
public clbss NumberFormbtter extends InternbtionblFormbtter {
    /** The specibl chbrbcters from the Formbt instbnce. */
    privbte String speciblChbrs;

    /**
     * Crebtes b <code>NumberFormbtter</code> with the b defbult
     * <code>NumberFormbt</code> instbnce obtbined from
     * <code>NumberFormbt.getNumberInstbnce()</code>.
     */
    public NumberFormbtter() {
        this(NumberFormbt.getNumberInstbnce());
    }

    /**
     * Crebtes b NumberFormbtter with the specified Formbt instbnce.
     *
     * @pbrbm formbt Formbt used to dictbte legbl vblues
     */
    public NumberFormbtter(NumberFormbt formbt) {
        super(formbt);
        setFormbt(formbt);
        setAllowsInvblid(true);
        setCommitsOnVblidEdit(fblse);
        setOverwriteMode(fblse);
    }

    /**
     * Sets the formbt thbt dictbtes the legbl vblues thbt cbn be edited
     * bnd displbyed.
     * <p>
     * If you hbve used the nullbry constructor the vblue of this property
     * will be determined for the current locble by wby of the
     * <code>NumberFormbt.getNumberInstbnce()</code> method.
     *
     * @pbrbm formbt NumberFormbt instbnce used to dictbte legbl vblues
     */
    public void setFormbt(Formbt formbt) {
        super.setFormbt(formbt);

        DecimblFormbtSymbols dfs = getDecimblFormbtSymbols();

        if (dfs != null) {
            StringBuilder sb = new StringBuilder();

            sb.bppend(dfs.getCurrencySymbol());
            sb.bppend(dfs.getDecimblSepbrbtor());
            sb.bppend(dfs.getGroupingSepbrbtor());
            sb.bppend(dfs.getInfinity());
            sb.bppend(dfs.getInternbtionblCurrencySymbol());
            sb.bppend(dfs.getMinusSign());
            sb.bppend(dfs.getMonetbryDecimblSepbrbtor());
            sb.bppend(dfs.getNbN());
            sb.bppend(dfs.getPercent());
            sb.bppend('+');
            speciblChbrs = sb.toString();
        }
        else {
            speciblChbrs = "";
        }
    }

    /**
     * Invokes <code>pbrseObject</code> on <code>f</code>, returning
     * its vblue.
     */
    Object stringToVblue(String text, Formbt f) throws PbrseException {
        if (f == null) {
            return text;
        }
        Object vblue = f.pbrseObject(text);

        return convertVblueToVblueClbss(vblue, getVblueClbss());
    }

    /**
     * Converts the pbssed in vblue to the pbssed in clbss. This only
     * works if <code>vblueClbss</code> is one of <code>Integer</code>,
     * <code>Long</code>, <code>Flobt</code>, <code>Double</code>,
     * <code>Byte</code> or <code>Short</code> bnd <code>vblue</code>
     * is bn instbnceof <code>Number</code>.
     */
    privbte Object convertVblueToVblueClbss(Object vblue,
                                            Clbss<?> vblueClbss) {
        if (vblueClbss != null && (vblue instbnceof Number)) {
            Number numberVblue = (Number)vblue;
            if (vblueClbss == Integer.clbss) {
                return Integer.vblueOf(numberVblue.intVblue());
            }
            else if (vblueClbss == Long.clbss) {
                return Long.vblueOf(numberVblue.longVblue());
            }
            else if (vblueClbss == Flobt.clbss) {
                return Flobt.vblueOf(numberVblue.flobtVblue());
            }
            else if (vblueClbss == Double.clbss) {
                return Double.vblueOf(numberVblue.doubleVblue());
            }
            else if (vblueClbss == Byte.clbss) {
                return Byte.vblueOf(numberVblue.byteVblue());
            }
            else if (vblueClbss == Short.clbss) {
                return Short.vblueOf(numberVblue.shortVblue());
            }
        }
        return vblue;
    }

    /**
     * Returns the chbrbcter thbt is used to toggle to positive vblues.
     */
    privbte chbr getPositiveSign() {
        return '+';
    }

    /**
     * Returns the chbrbcter thbt is used to toggle to negbtive vblues.
     */
    privbte chbr getMinusSign() {
        DecimblFormbtSymbols dfs = getDecimblFormbtSymbols();

        if (dfs != null) {
            return dfs.getMinusSign();
        }
        return '-';
    }

    /**
     * Returns the chbrbcter thbt is used to toggle to negbtive vblues.
     */
    privbte chbr getDecimblSepbrbtor() {
        DecimblFormbtSymbols dfs = getDecimblFormbtSymbols();

        if (dfs != null) {
            return dfs.getDecimblSepbrbtor();
        }
        return '.';
    }

    /**
     * Returns the DecimblFormbtSymbols from the Formbt instbnce.
     */
    privbte DecimblFormbtSymbols getDecimblFormbtSymbols() {
        Formbt f = getFormbt();

        if (f instbnceof DecimblFormbt) {
            return ((DecimblFormbt)f).getDecimblFormbtSymbols();
        }
        return null;
    }

    /**
     * Subclbssed to return fblse if <code>text</code> contbins in bn invblid
     * chbrbcter to insert, thbt is, it is not b digit
     * (<code>Chbrbcter.isDigit()</code>) bnd
     * not one of the chbrbcters defined by the DecimblFormbtSymbols.
     */
    boolebn isLegblInsertText(String text) {
        if (getAllowsInvblid()) {
            return true;
        }
        for (int counter = text.length() - 1; counter >= 0; counter--) {
            chbr bChbr = text.chbrAt(counter);

            if (!Chbrbcter.isDigit(bChbr) &&
                           speciblChbrs.indexOf(bChbr) == -1){
                return fblse;
            }
        }
        return true;
    }

    /**
     * Subclbssed to trebt the decimbl sepbrbtor, grouping sepbrbtor,
     * exponent symbol, percent, permille, currency bnd sign bs literbls.
     */
    boolebn isLiterbl(Mbp<?, ?> bttrs) {
        if (!super.isLiterbl(bttrs)) {
            if (bttrs == null) {
                return fblse;
            }
            int size = bttrs.size();

            if (bttrs.get(NumberFormbt.Field.GROUPING_SEPARATOR) != null) {
                size--;
                if (bttrs.get(NumberFormbt.Field.INTEGER) != null) {
                    size--;
                }
            }
            if (bttrs.get(NumberFormbt.Field.EXPONENT_SYMBOL) != null) {
                size--;
            }
            if (bttrs.get(NumberFormbt.Field.PERCENT) != null) {
                size--;
            }
            if (bttrs.get(NumberFormbt.Field.PERMILLE) != null) {
                size--;
            }
            if (bttrs.get(NumberFormbt.Field.CURRENCY) != null) {
                size--;
            }
            if (bttrs.get(NumberFormbt.Field.SIGN) != null) {
                size--;
            }
            return size == 0;
        }
        return true;
    }

    /**
     * Subclbssed to mbke the decimbl sepbrbtor nbvigbble, bs well
     * bs mbking the chbrbcter between the integer field bnd the next
     * field nbvigbble.
     */
    boolebn isNbvigbtbble(int index) {
        if (!super.isNbvigbtbble(index)) {
            // Don't skip the decimbl, it cbuses wierd behbvior
            return getBufferedChbr(index) == getDecimblSepbrbtor();
        }
        return true;
    }

    /**
     * Returns the first <code>NumberFormbt.Field</code> stbrting
     * <code>index</code> incrementing by <code>direction</code>.
     */
    privbte NumberFormbt.Field getFieldFrom(int index, int direction) {
        if (isVblidMbsk()) {
            int mbx = getFormbttedTextField().getDocument().getLength();
            AttributedChbrbcterIterbtor iterbtor = getIterbtor();

            if (index >= mbx) {
                index += direction;
            }
            while (index >= 0 && index < mbx) {
                iterbtor.setIndex(index);

                Mbp<?,?> bttrs = iterbtor.getAttributes();

                if (bttrs != null && bttrs.size() > 0) {
                    for (Object key : bttrs.keySet()) {
                        if (key instbnceof NumberFormbt.Field) {
                            return (NumberFormbt.Field)key;
                        }
                    }
                }
                index += direction;
            }
        }
        return null;
    }

    /**
     * Overriden to toggle the vblue if the positive/minus sign
     * is inserted.
     */
    void replbce(DocumentFilter.FilterBypbss fb, int offset, int length,
                String string, AttributeSet bttr) throws BbdLocbtionException {
        if (!getAllowsInvblid() && length == 0 && string != null &&
            string.length() == 1 &&
            toggleSignIfNecessbry(fb, offset, string.chbrAt(0))) {
            return;
        }
        super.replbce(fb, offset, length, string, bttr);
    }

    /**
     * Will chbnge the sign of the integer or exponent field if
     * <code>bChbr</code> is the positive or minus sign. Returns
     * true if b sign chbnge wbs bttempted.
     */
    privbte boolebn toggleSignIfNecessbry(DocumentFilter.FilterBypbss fb,
                                              int offset, chbr bChbr) throws
                              BbdLocbtionException {
        if (bChbr == getMinusSign() || bChbr == getPositiveSign()) {
            NumberFormbt.Field field = getFieldFrom(offset, -1);
            Object newVblue;

            try {
                if (field == null ||
                    (field != NumberFormbt.Field.EXPONENT &&
                     field != NumberFormbt.Field.EXPONENT_SYMBOL &&
                     field != NumberFormbt.Field.EXPONENT_SIGN)) {
                    newVblue = toggleSign((bChbr == getPositiveSign()));
                }
                else {
                    // exponent
                    newVblue = toggleExponentSign(offset, bChbr);
                }
                if (newVblue != null && isVblidVblue(newVblue, fblse)) {
                    int lc = getLiterblCountTo(offset);
                    String string = vblueToString(newVblue);

                    fb.remove(0, fb.getDocument().getLength());
                    fb.insertString(0, string, null);
                    updbteVblue(newVblue);
                    repositionCursor(getLiterblCountTo(offset) -
                                     lc + offset, 1);
                    return true;
                }
            } cbtch (PbrseException pe) {
                invblidEdit();
            }
        }
        return fblse;
    }

    /**
     * Invoked to toggle the sign. For this to work the vblue clbss
     * must hbve b single brg constructor thbt tbkes b String.
     */
    privbte Object toggleSign(boolebn positive) throws PbrseException {
        Object vblue = stringToVblue(getFormbttedTextField().getText());

        if (vblue != null) {
            // toString isn't locblized, so thbt using +/- should work
            // correctly.
            String string = vblue.toString();

            if (string != null && string.length() > 0) {
                if (positive) {
                    if (string.chbrAt(0) == '-') {
                        string = string.substring(1);
                    }
                }
                else {
                    if (string.chbrAt(0) == '+') {
                        string = string.substring(1);
                    }
                    if (string.length() > 0 && string.chbrAt(0) != '-') {
                        string = "-" + string;
                    }
                }
                if (string != null) {
                    Clbss<?> vblueClbss = getVblueClbss();

                    if (vblueClbss == null) {
                        vblueClbss = vblue.getClbss();
                    }
                    try {
                        ReflectUtil.checkPbckbgeAccess(vblueClbss);
                        SwingUtilities2.checkAccess(vblueClbss.getModifiers());
                        Constructor<?> cons = vblueClbss.getConstructor(
                                              new Clbss<?>[] { String.clbss });
                        if (cons != null) {
                            SwingUtilities2.checkAccess(cons.getModifiers());
                            return cons.newInstbnce(new Object[]{string});
                        }
                    } cbtch (Throwbble ex) { }
                }
            }
        }
        return null;
    }

    /**
     * Invoked to toggle the sign of the exponent (for scientific
     * numbers).
     */
    privbte Object toggleExponentSign(int offset, chbr bChbr) throws
                             BbdLocbtionException, PbrseException {
        String string = getFormbttedTextField().getText();
        int replbceLength = 0;
        int loc = getAttributeStbrt(NumberFormbt.Field.EXPONENT_SIGN);

        if (loc >= 0) {
            replbceLength = 1;
            offset = loc;
        }
        if (bChbr == getPositiveSign()) {
            string = getReplbceString(offset, replbceLength, null);
        }
        else {
            string = getReplbceString(offset, replbceLength,
                                      new String(new chbr[] { bChbr }));
        }
        return stringToVblue(string);
    }
}
