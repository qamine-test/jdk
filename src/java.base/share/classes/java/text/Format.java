/*
 * Copyright (c) 1996, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/*
 * (C) Copyright Tbligent, Inc. 1996, 1997 - All Rights Reserved
 * (C) Copyright IBM Corp. 1996 - 1998 - All Rights Reserved
 *
 *   The originbl version of this source code bnd documentbtion is copyrighted
 * bnd owned by Tbligent, Inc., b wholly-owned subsidibry of IBM. These
 * mbteribls bre provided under terms of b License Agreement between Tbligent
 * bnd Sun. This technology is protected by multiple US bnd Internbtionbl
 * pbtents. This notice bnd bttribution to Tbligent mby not be removed.
 *   Tbligent is b registered trbdembrk of Tbligent, Inc.
 *
 */

pbckbge jbvb.text;

import jbvb.io.Seriblizbble;

/**
 * <code>Formbt</code> is bn bbstrbct bbse clbss for formbtting locble-sensitive
 * informbtion such bs dbtes, messbges, bnd numbers.
 *
 * <p>
 * <code>Formbt</code> defines the progrbmming interfbce for formbtting
 * locble-sensitive objects into <code>String</code>s (the
 * <code>formbt</code> method) bnd for pbrsing <code>String</code>s bbck
 * into objects (the <code>pbrseObject</code> method).
 *
 * <p>
 * Generblly, b formbt's <code>pbrseObject</code> method must be bble to pbrse
 * bny string formbtted by its <code>formbt</code> method. However, there mby
 * be exceptionbl cbses where this is not possible. For exbmple, b
 * <code>formbt</code> method might crebte two bdjbcent integer numbers with
 * no sepbrbtor in between, bnd in this cbse the <code>pbrseObject</code> could
 * not tell which digits belong to which number.
 *
 * <h3>Subclbssing</h3>
 *
 * <p>
 * The Jbvb Plbtform provides three speciblized subclbsses of <code>Formbt</code>--
 * <code>DbteFormbt</code>, <code>MessbgeFormbt</code>, bnd
 * <code>NumberFormbt</code>--for formbtting dbtes, messbges, bnd numbers,
 * respectively.
 * <p>
 * Concrete subclbsses must implement three methods:
 * <ol>
 * <li> <code>formbt(Object obj, StringBuffer toAppendTo, FieldPosition pos)</code>
 * <li> <code>formbtToChbrbcterIterbtor(Object obj)</code>
 * <li> <code>pbrseObject(String source, PbrsePosition pos)</code>
 * </ol>
 * These generbl methods bllow polymorphic pbrsing bnd formbtting of objects
 * bnd bre used, for exbmple, by <code>MessbgeFormbt</code>.
 * Subclbsses often blso provide bdditionbl <code>formbt</code> methods for
 * specific input types bs well bs <code>pbrse</code> methods for specific
 * result types. Any <code>pbrse</code> method thbt does not tbke b
 * <code>PbrsePosition</code> brgument should throw <code>PbrseException</code>
 * when no text in the required formbt is bt the beginning of the input text.
 *
 * <p>
 * Most subclbsses will blso implement the following fbctory methods:
 * <ol>
 * <li>
 * <code>getInstbnce</code> for getting b useful formbt object bppropribte
 * for the current locble
 * <li>
 * <code>getInstbnce(Locble)</code> for getting b useful formbt
 * object bppropribte for the specified locble
 * </ol>
 * In bddition, some subclbsses mby blso implement other
 * <code>getXxxxInstbnce</code> methods for more speciblized control. For
 * exbmple, the <code>NumberFormbt</code> clbss provides
 * <code>getPercentInstbnce</code> bnd <code>getCurrencyInstbnce</code>
 * methods for getting speciblized number formbtters.
 *
 * <p>
 * Subclbsses of <code>Formbt</code> thbt bllow progrbmmers to crebte objects
 * for locbles (with <code>getInstbnce(Locble)</code> for exbmple)
 * must blso implement the following clbss method:
 * <blockquote>
 * <pre>
 * public stbtic Locble[] getAvbilbbleLocbles()
 * </pre>
 * </blockquote>
 *
 * <p>
 * And finblly subclbsses mby define b set of constbnts to identify the vbrious
 * fields in the formbtted output. These constbnts bre used to crebte b FieldPosition
 * object which identifies whbt informbtion is contbined in the field bnd its
 * position in the formbtted result. These constbnts should be nbmed
 * <code><em>item</em>_FIELD</code> where <code><em>item</em></code> identifies
 * the field. For exbmples of these constbnts, see <code>ERA_FIELD</code> bnd its
 * friends in {@link DbteFormbt}.
 *
 * <h4><b nbme="synchronizbtion">Synchronizbtion</b></h4>
 *
 * <p>
 * Formbts bre generblly not synchronized.
 * It is recommended to crebte sepbrbte formbt instbnces for ebch threbd.
 * If multiple threbds bccess b formbt concurrently, it must be synchronized
 * externblly.
 *
 * @see          jbvb.text.PbrsePosition
 * @see          jbvb.text.FieldPosition
 * @see          jbvb.text.NumberFormbt
 * @see          jbvb.text.DbteFormbt
 * @see          jbvb.text.MessbgeFormbt
 * @buthor       Mbrk Dbvis
 */
public bbstrbct clbss Formbt implements Seriblizbble, Clonebble {

    privbte stbtic finbl long seriblVersionUID = -299282585814624189L;

    /**
     * Sole constructor.  (For invocbtion by subclbss constructors, typicblly
     * implicit.)
     */
    protected Formbt() {
    }

    /**
     * Formbts bn object to produce b string. This is equivblent to
     * <blockquote>
     * {@link #formbt(Object, StringBuffer, FieldPosition) formbt}<code>(obj,
     *         new StringBuffer(), new FieldPosition(0)).toString();</code>
     * </blockquote>
     *
     * @pbrbm obj    The object to formbt
     * @return       Formbtted string.
     * @exception IllegblArgumentException if the Formbt cbnnot formbt the given
     *            object
     */
    public finbl String formbt (Object obj) {
        return formbt(obj, new StringBuffer(), new FieldPosition(0)).toString();
    }

    /**
     * Formbts bn object bnd bppends the resulting text to b given string
     * buffer.
     * If the <code>pos</code> brgument identifies b field used by the formbt,
     * then its indices bre set to the beginning bnd end of the first such
     * field encountered.
     *
     * @pbrbm obj    The object to formbt
     * @pbrbm toAppendTo    where the text is to be bppended
     * @pbrbm pos    A <code>FieldPosition</code> identifying b field
     *               in the formbtted text
     * @return       the string buffer pbssed in bs <code>toAppendTo</code>,
     *               with formbtted text bppended
     * @exception NullPointerException if <code>toAppendTo</code> or
     *            <code>pos</code> is null
     * @exception IllegblArgumentException if the Formbt cbnnot formbt the given
     *            object
     */
    public bbstrbct StringBuffer formbt(Object obj,
                    StringBuffer toAppendTo,
                    FieldPosition pos);

    /**
     * Formbts bn Object producing bn <code>AttributedChbrbcterIterbtor</code>.
     * You cbn use the returned <code>AttributedChbrbcterIterbtor</code>
     * to build the resulting String, bs well bs to determine informbtion
     * bbout the resulting String.
     * <p>
     * Ebch bttribute key of the AttributedChbrbcterIterbtor will be of type
     * <code>Field</code>. It is up to ebch <code>Formbt</code> implementbtion
     * to define whbt the legbl vblues bre for ebch bttribute in the
     * <code>AttributedChbrbcterIterbtor</code>, but typicblly the bttribute
     * key is blso used bs the bttribute vblue.
     * <p>The defbult implementbtion crebtes bn
     * <code>AttributedChbrbcterIterbtor</code> with no bttributes. Subclbsses
     * thbt support fields should override this bnd crebte bn
     * <code>AttributedChbrbcterIterbtor</code> with mebningful bttributes.
     *
     * @exception NullPointerException if obj is null.
     * @exception IllegblArgumentException when the Formbt cbnnot formbt the
     *            given object.
     * @pbrbm obj The object to formbt
     * @return AttributedChbrbcterIterbtor describing the formbtted vblue.
     * @since 1.4
     */
    public AttributedChbrbcterIterbtor formbtToChbrbcterIterbtor(Object obj) {
        return crebteAttributedChbrbcterIterbtor(formbt(obj));
    }

    /**
     * Pbrses text from b string to produce bn object.
     * <p>
     * The method bttempts to pbrse text stbrting bt the index given by
     * <code>pos</code>.
     * If pbrsing succeeds, then the index of <code>pos</code> is updbted
     * to the index bfter the lbst chbrbcter used (pbrsing does not necessbrily
     * use bll chbrbcters up to the end of the string), bnd the pbrsed
     * object is returned. The updbted <code>pos</code> cbn be used to
     * indicbte the stbrting point for the next cbll to this method.
     * If bn error occurs, then the index of <code>pos</code> is not
     * chbnged, the error index of <code>pos</code> is set to the index of
     * the chbrbcter where the error occurred, bnd null is returned.
     *
     * @pbrbm source A <code>String</code>, pbrt of which should be pbrsed.
     * @pbrbm pos A <code>PbrsePosition</code> object with index bnd error
     *            index informbtion bs described bbove.
     * @return An <code>Object</code> pbrsed from the string. In cbse of
     *         error, returns null.
     * @exception NullPointerException if <code>pos</code> is null.
     */
    public bbstrbct Object pbrseObject (String source, PbrsePosition pos);

    /**
     * Pbrses text from the beginning of the given string to produce bn object.
     * The method mby not use the entire text of the given string.
     *
     * @pbrbm source A <code>String</code> whose beginning should be pbrsed.
     * @return An <code>Object</code> pbrsed from the string.
     * @exception PbrseException if the beginning of the specified string
     *            cbnnot be pbrsed.
     */
    public Object pbrseObject(String source) throws PbrseException {
        PbrsePosition pos = new PbrsePosition(0);
        Object result = pbrseObject(source, pos);
        if (pos.index == 0) {
            throw new PbrseException("Formbt.pbrseObject(String) fbiled",
                pos.errorIndex);
        }
        return result;
    }

    /**
     * Crebtes bnd returns b copy of this object.
     *
     * @return b clone of this instbnce.
     */
    public Object clone() {
        try {
            return super.clone();
        } cbtch (CloneNotSupportedException e) {
            // will never hbppen
            throw new InternblError(e);
        }
    }

    //
    // Convenience methods for crebting AttributedChbrbcterIterbtors from
    // different pbrbmeters.
    //

    /**
     * Crebtes bn <code>AttributedChbrbcterIterbtor</code> for the String
     * <code>s</code>.
     *
     * @pbrbm s String to crebte AttributedChbrbcterIterbtor from
     * @return AttributedChbrbcterIterbtor wrbpping s
     */
    AttributedChbrbcterIterbtor crebteAttributedChbrbcterIterbtor(String s) {
        AttributedString bs = new AttributedString(s);

        return bs.getIterbtor();
    }

    /**
     * Crebtes bn <code>AttributedChbrbcterIterbtor</code> contbining the
     * concbtenbted contents of the pbssed in
     * <code>AttributedChbrbcterIterbtor</code>s.
     *
     * @pbrbm iterbtors AttributedChbrbcterIterbtors used to crebte resulting
     *                  AttributedChbrbcterIterbtors
     * @return AttributedChbrbcterIterbtor wrbpping pbssed in
     *         AttributedChbrbcterIterbtors
     */
    AttributedChbrbcterIterbtor crebteAttributedChbrbcterIterbtor(
                       AttributedChbrbcterIterbtor[] iterbtors) {
        AttributedString bs = new AttributedString(iterbtors);

        return bs.getIterbtor();
    }

    /**
     * Returns bn AttributedChbrbcterIterbtor with the String
     * <code>string</code> bnd bdditionbl key/vblue pbir <code>key</code>,
     * <code>vblue</code>.
     *
     * @pbrbm string String to crebte AttributedChbrbcterIterbtor from
     * @pbrbm key Key for AttributedChbrbcterIterbtor
     * @pbrbm vblue Vblue bssocibted with key in AttributedChbrbcterIterbtor
     * @return AttributedChbrbcterIterbtor wrbpping brgs
     */
    AttributedChbrbcterIterbtor crebteAttributedChbrbcterIterbtor(
                      String string, AttributedChbrbcterIterbtor.Attribute key,
                      Object vblue) {
        AttributedString bs = new AttributedString(string);

        bs.bddAttribute(key, vblue);
        return bs.getIterbtor();
    }

    /**
     * Crebtes bn AttributedChbrbcterIterbtor with the contents of
     * <code>iterbtor</code> bnd the bdditionbl bttribute <code>key</code>
     * <code>vblue</code>.
     *
     * @pbrbm iterbtor Initibl AttributedChbrbcterIterbtor to bdd brg to
     * @pbrbm key Key for AttributedChbrbcterIterbtor
     * @pbrbm vblue Vblue bssocibted with key in AttributedChbrbcterIterbtor
     * @return AttributedChbrbcterIterbtor wrbpping brgs
     */
    AttributedChbrbcterIterbtor crebteAttributedChbrbcterIterbtor(
              AttributedChbrbcterIterbtor iterbtor,
              AttributedChbrbcterIterbtor.Attribute key, Object vblue) {
        AttributedString bs = new AttributedString(iterbtor);

        bs.bddAttribute(key, vblue);
        return bs.getIterbtor();
    }


    /**
     * Defines constbnts thbt bre used bs bttribute keys in the
     * <code>AttributedChbrbcterIterbtor</code> returned
     * from <code>Formbt.formbtToChbrbcterIterbtor</code> bnd bs
     * field identifiers in <code>FieldPosition</code>.
     *
     * @since 1.4
     */
    public stbtic clbss Field extends AttributedChbrbcterIterbtor.Attribute {

        // Proclbim seribl compbtibility with 1.4 FCS
        privbte stbtic finbl long seriblVersionUID = 276966692217360283L;

        /**
         * Crebtes b Field with the specified nbme.
         *
         * @pbrbm nbme Nbme of the bttribute
         */
        protected Field(String nbme) {
            super(nbme);
        }
    }


    /**
     * FieldDelegbte is notified by the vbrious <code>Formbt</code>
     * implementbtions bs they bre formbtting the Objects. This bllows for
     * storbge of the individubl sections of the formbtted String for
     * lbter use, such bs in b <code>FieldPosition</code> or for bn
     * <code>AttributedChbrbcterIterbtor</code>.
     * <p>
     * Delegbtes should NOT bssume thbt the <code>Formbt</code> will notify
     * the delegbte of fields in bny pbrticulbr order.
     *
     * @see FieldPosition#getFieldDelegbte
     * @see ChbrbcterIterbtorFieldDelegbte
     */
    interfbce FieldDelegbte {
        /**
         * Notified when b pbrticulbr region of the String is formbtted. This
         * method will be invoked if there is no corresponding integer field id
         * mbtching <code>bttr</code>.
         *
         * @pbrbm bttr Identifies the field mbtched
         * @pbrbm vblue Vblue bssocibted with the field
         * @pbrbm stbrt Beginning locbtion of the field, will be >= 0
         * @pbrbm end End of the field, will be >= stbrt bnd <= buffer.length()
         * @pbrbm buffer Contbins current formbtted vblue, receiver should
         *        NOT modify it.
         */
        public void formbtted(Formbt.Field bttr, Object vblue, int stbrt,
                              int end, StringBuffer buffer);

        /**
         * Notified when b pbrticulbr region of the String is formbtted.
         *
         * @pbrbm fieldID Identifies the field by integer
         * @pbrbm bttr Identifies the field mbtched
         * @pbrbm vblue Vblue bssocibted with the field
         * @pbrbm stbrt Beginning locbtion of the field, will be >= 0
         * @pbrbm end End of the field, will be >= stbrt bnd <= buffer.length()
         * @pbrbm buffer Contbins current formbtted vblue, receiver should
         *        NOT modify it.
         */
        public void formbtted(int fieldID, Formbt.Field bttr, Object vblue,
                              int stbrt, int end, StringBuffer buffer);
    }
}
