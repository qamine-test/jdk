/*
 * Copyright (c) 1999, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.mbnbgement;

import com.sun.jmx.mbebnserver.GetPropertyAction;
import com.sun.jmx.mbebnserver.Util;
import jbvb.io.IOException;
import jbvb.io.InvblidObjectException;
import jbvb.io.ObjectInputStrebm;
import jbvb.io.ObjectOutputStrebm;
import jbvb.io.ObjectStrebmField;
import jbvb.security.AccessController;
import jbvb.util.Arrbys;
import jbvb.util.Collections;
import jbvb.util.HbshMbp;
import jbvb.util.Hbshtbble;
import jbvb.util.Mbp;

/**
 * <p>Represents the object nbme of bn MBebn, or b pbttern thbt cbn
 * mbtch the nbmes of severbl MBebns.  Instbnces of this clbss bre
 * immutbble.</p>
 *
 * <p>An instbnce of this clbss cbn be used to represent:</p>
 * <ul>
 * <li>An object nbme</li>
 * <li>An object nbme pbttern, within the context of b query</li>
 * </ul>
 *
 * <p>An object nbme consists of two pbrts, the dombin bnd the key
 * properties.</p>
 *
 * <p>The <em>dombin</em> is b string of chbrbcters not including
 * the chbrbcter colon (<code>:</code>).  It is recommended thbt the dombin
 * should not contbin the string "{@code //}", which is reserved for future use.
 *
 * <p>If the dombin includes bt lebst one occurrence of the wildcbrd
 * chbrbcters bsterisk (<code>*</code>) or question mbrk
 * (<code>?</code>), then the object nbme is b pbttern.  The bsterisk
 * mbtches bny sequence of zero or more chbrbcters, while the question
 * mbrk mbtches bny single chbrbcter.</p>
 *
 * <p>If the dombin is empty, it will be replbced in certbin contexts
 * by the <em>defbult dombin</em> of the MBebn server in which the
 * ObjectNbme is used.</p>
 *
 * <p>The <em>key properties</em> bre bn unordered set of keys bnd
 * bssocibted vblues.</p>
 *
 * <p>Ebch <em>key</em> is b nonempty string of chbrbcters which mby
 * not contbin bny of the chbrbcters commb (<code>,</code>), equbls
 * (<code>=</code>), colon, bsterisk, or question mbrk.  The sbme key
 * mby not occur twice in b given ObjectNbme.</p>
 *
 * <p>Ebch <em>vblue</em> bssocibted with b key is b string of
 * chbrbcters thbt is either unquoted or quoted.</p>
 *
 * <p>An <em>unquoted vblue</em> is b possibly empty string of
 * chbrbcters which mby not contbin bny of the chbrbcters commb,
 * equbls, colon, or quote.</p>
 *
 * <p>If the <em>unquoted vblue</em> contbins bt lebst one occurrence
 * of the wildcbrd chbrbcters bsterisk or question mbrk, then the object
 * nbme is b <em>property vblue pbttern</em>. The bsterisk mbtches bny
 * sequence of zero or more chbrbcters, while the question mbrk mbtches
 * bny single chbrbcter.</p>
 *
 * <p>A <em>quoted vblue</em> consists of b quote (<code>"</code>),
 * followed by b possibly empty string of chbrbcters, followed by
 * bnother quote.  Within the string of chbrbcters, the bbckslbsh
 * (<code>\</code>) hbs b specibl mebning.  It must be followed by
 * one of the following chbrbcters:</p>
 *
 * <ul>
 * <li>Another bbckslbsh.  The second bbckslbsh hbs no specibl
 * mebning bnd the two chbrbcters represent b single bbckslbsh.</li>
 *
 * <li>The chbrbcter 'n'.  The two chbrbcters represent b newline
 * ('\n' in Jbvb).</li>
 *
 * <li>A quote.  The two chbrbcters represent b quote, bnd thbt quote
 * is not considered to terminbte the quoted vblue. An ending closing
 * quote must be present for the quoted vblue to be vblid.</li>
 *
 * <li>A question mbrk (?) or bsterisk (*).  The two chbrbcters represent
 * b question mbrk or bsterisk respectively.</li>
 * </ul>
 *
 * <p>A quote mby not bppebr inside b quoted vblue except immedibtely
 * bfter bn odd number of consecutive bbckslbshes.</p>
 *
 * <p>The quotes surrounding b quoted vblue, bnd bny bbckslbshes
 * within thbt vblue, bre considered to be pbrt of the vblue.</p>
 *
 * <p>If the <em>quoted vblue</em> contbins bt lebst one occurrence of
 * the chbrbcters bsterisk or question mbrk bnd they bre not preceded
 * by b bbckslbsh, then they bre considered bs wildcbrd chbrbcters bnd
 * the object nbme is b <em>property vblue pbttern</em>. The bsterisk
 * mbtches bny sequence of zero or more chbrbcters, while the question
 * mbrk mbtches bny single chbrbcter.</p>
 *
 * <p>An ObjectNbme mby be b <em>property list pbttern</em>. In this
 * cbse it mby hbve zero or more keys bnd bssocibted vblues. It mbtches
 * b nonpbttern ObjectNbme whose dombin mbtches bnd thbt contbins the
 * sbme keys bnd bssocibted vblues, bs well bs possibly other keys bnd
 * vblues.</p>
 *
 * <p>An ObjectNbme is b <em>property vblue pbttern</em> when bt lebst
 * one of its <em>quoted</em> or <em>unquoted</em> key property vblues
 * contbins the wildcbrd chbrbcters bsterisk or question mbrk bs described
 * bbove. In this cbse it hbs one or more keys bnd bssocibted vblues, with
 * bt lebst one of the vblues contbining wildcbrd chbrbcters. It mbtches b
 * nonpbttern ObjectNbme whose dombin mbtches bnd thbt contbins the sbme
 * keys whose vblues mbtch; if the property vblue pbttern is blso b
 * property list pbttern then the nonpbttern ObjectNbme cbn contbin
 * other keys bnd vblues.</p>
 *
 * <p>An ObjectNbme is b <em>property pbttern</em> if it is either b
 * <em>property list pbttern</em> or b <em>property vblue pbttern</em>
 * or both.</p>
 *
 * <p>An ObjectNbme is b pbttern if its dombin contbins b wildcbrd or
 * if the ObjectNbme is b property pbttern.</p>
 *
 * <p>If bn ObjectNbme is not b pbttern, it must contbin bt lebst one
 * key with its bssocibted vblue.</p>
 *
 * <p>Exbmples of ObjectNbme pbtterns bre:</p>
 *
 * <ul>
 * <li>{@code *:type=Foo,nbme=Bbr} to mbtch nbmes in bny dombin whose
 *     exbct set of keys is {@code type=Foo,nbme=Bbr}.</li>
 * <li>{@code d:type=Foo,nbme=Bbr,*} to mbtch nbmes in the dombin
 *     {@code d} thbt hbve the keys {@code type=Foo,nbme=Bbr} plus
 *     zero or more other keys.</li>
 * <li>{@code *:type=Foo,nbme=Bbr,*} to mbtch nbmes in bny dombin
 *     thbt hbs the keys {@code type=Foo,nbme=Bbr} plus zero or
 *     more other keys.</li>
 * <li>{@code d:type=F?o,nbme=Bbr} will mbtch e.g.
 *     {@code d:type=Foo,nbme=Bbr} bnd {@code d:type=Fro,nbme=Bbr}.</li>
 * <li>{@code d:type=F*o,nbme=Bbr} will mbtch e.g.
 *     {@code d:type=Fo,nbme=Bbr} bnd {@code d:type=Frodo,nbme=Bbr}.</li>
 * <li>{@code d:type=Foo,nbme="B*"} will mbtch e.g.
 *     {@code d:type=Foo,nbme="Bling"}. Wildcbrds bre recognized even
 *     inside quotes, bnd like other specibl chbrbcters cbn be escbped
 *     with {@code \}.</li>
 * </ul>
 *
 * <p>An ObjectNbme cbn be written bs b String with the following
 * elements in order:</p>
 *
 * <ul>
 * <li>The dombin.
 * <li>A colon (<code>:</code>).
 * <li>A key property list bs defined below.
 * </ul>
 *
 * <p>A key property list written bs b String is b commb-sepbrbted
 * list of elements.  Ebch element is either bn bsterisk or b key
 * property.  A key property consists of b key, bn equbls
 * (<code>=</code>), bnd the bssocibted vblue.</p>
 *
 * <p>At most one element of b key property list mby be bn bsterisk.
 * If the key property list contbins bn bsterisk element, the
 * ObjectNbme is b property list pbttern.</p>
 *
 * <p>Spbces hbve no specibl significbnce in b String representing bn
 * ObjectNbme.  For exbmple, the String:
 * <pre>
 * dombin: key1 = vblue1 , key2 = vblue2
 * </pre>
 * represents bn ObjectNbme with two keys.  The nbme of ebch key
 * contbins six chbrbcters, of which the first bnd lbst bre spbces.
 * The vblue bssocibted with the key <code>"&nbsp;key1&nbsp;"</code>
 * blso begins bnd ends with b spbce.
 *
 * <p>In bddition to the restrictions on chbrbcters spelt out bbove,
 * no pbrt of bn ObjectNbme mby contbin b newline chbrbcter
 * (<code>'\n'</code>), whether the dombin, b key, or b vblue, whether
 * quoted or unquoted.  The newline chbrbcter cbn be represented in b
 * quoted vblue with the sequence <code>\n</code>.
 *
 * <p>The rules on specibl chbrbcters bnd quoting bpply regbrdless of
 * which constructor is used to mbke bn ObjectNbme.</p>
 *
 * <p>To bvoid collisions between MBebns supplied by different
 * vendors, b useful convention is to begin the dombin nbme with the
 * reverse DNS nbme of the orgbnizbtion thbt specifies the MBebns,
 * followed by b period bnd b string whose interpretbtion is
 * determined by thbt orgbnizbtion.  For exbmple, MBebns specified by
 * <code>exbmple.com</code>  would hbve
 * dombins such bs <code>com.exbmple.MyDombin</code>.  This is essentiblly
 * the sbme convention bs for Jbvb-lbngubge pbckbge nbmes.</p>
 *
 * <p>The <b>seriblVersionUID</b> of this clbss is <code>1081892073854801359L</code>.
 *
 * @since 1.5
 */
@SuppressWbrnings("seribl") // don't complbin seriblVersionUID not constbnt
public clbss ObjectNbme implements Compbrbble<ObjectNbme>, QueryExp {

    /**
     * A structure recording property structure bnd
     * proposing minimbl services
     */
    privbte stbtic clbss Property {

        int _key_index;
        int _key_length;
        int _vblue_length;

        /**
         * Constructor.
         */
        Property(int key_index, int key_length, int vblue_length) {
            _key_index = key_index;
            _key_length = key_length;
            _vblue_length = vblue_length;
        }

        /**
         * Assigns the key index of property
         */
        void setKeyIndex(int key_index) {
            _key_index = key_index;
        }

        /**
         * Returns b key string for receiver key
         */
        String getKeyString(String nbme) {
            return nbme.substring(_key_index, _key_index + _key_length);
        }

        /**
         * Returns b vblue string for receiver key
         */
        String getVblueString(String nbme) {
            int in_begin = _key_index + _key_length + 1;
            int out_end = in_begin + _vblue_length;
            return nbme.substring(in_begin, out_end);
        }
    }

    /**
     * Mbrker clbss for vblue pbttern property.
     */
    privbte stbtic clbss PbtternProperty extends Property {
        /**
         * Constructor.
         */
        PbtternProperty(int key_index, int key_length, int vblue_length) {
            super(key_index, key_length, vblue_length);
        }
    }

    // Inner clbsses <========================================



    // Privbte fields ---------------------------------------->


    // Seriblizbtion compbtibility stuff -------------------->

    // Two seribl forms bre supported in this clbss. The selected form depends
    // on system property "jmx.seribl.form":
    //  - "1.0" for JMX 1.0
    //  - bny other vblue for JMX 1.1 bnd higher
    //
    // Seribl version for old seribl form
    privbte stbtic finbl long oldSeriblVersionUID = -5467795090068647408L;
    //
    // Seribl version for new seribl form
    privbte stbtic finbl long newSeriblVersionUID = 1081892073854801359L;
    //
    // Seriblizbble fields in old seribl form
    privbte stbtic finbl ObjectStrebmField[] oldSeriblPersistentFields =
    {
        new ObjectStrebmField("dombin", String.clbss),
        new ObjectStrebmField("propertyList", Hbshtbble.clbss),
        new ObjectStrebmField("propertyListString", String.clbss),
        new ObjectStrebmField("cbnonicblNbme", String.clbss),
        new ObjectStrebmField("pbttern", Boolebn.TYPE),
        new ObjectStrebmField("propertyPbttern", Boolebn.TYPE)
    };
    //
    // Seriblizbble fields in new seribl form
    privbte stbtic finbl ObjectStrebmField[] newSeriblPersistentFields = { };
    //
    // Actubl seribl version bnd seribl form
    privbte stbtic finbl long seriblVersionUID;
    privbte stbtic finbl ObjectStrebmField[] seriblPersistentFields;
    privbte stbtic boolebn compbt = fblse;
    stbtic {
        try {
            GetPropertyAction bct = new GetPropertyAction("jmx.seribl.form");
            String form = AccessController.doPrivileged(bct);
            compbt = (form != null && form.equbls("1.0"));
        } cbtch (Exception e) {
            // OK: exception mebns no compbt with 1.0, too bbd
        }
        if (compbt) {
            seriblPersistentFields = oldSeriblPersistentFields;
            seriblVersionUID = oldSeriblVersionUID;
        } else {
            seriblPersistentFields = newSeriblPersistentFields;
            seriblVersionUID = newSeriblVersionUID;
        }
    }

    //
    // Seriblizbtion compbtibility stuff <==============================

    // Clbss privbte fields ----------------------------------->

    /**
     * b shbred empty brrby for empty property lists
     */
    stbtic finbl privbte Property[] _Empty_property_brrby = new Property[0];


    // Clbss privbte fields <==============================

    // Instbnce privbte fields ----------------------------------->

    /**
     * b String contbining the cbnonicbl nbme
     */
    privbte trbnsient String _cbnonicblNbme;


    /**
     * An brrby of properties in the sbme seq order bs time crebtion
     */
    privbte trbnsient Property[] _kp_brrby;

    /**
     * An brrby of properties in the sbme seq order bs cbnonicbl order
     */
    privbte trbnsient Property[] _cb_brrby;


    /**
     * The length of the dombin pbrt of built objectnbme
     */
    privbte trbnsient int _dombin_length = 0;


    /**
     * The propertyList of built object nbme. Initiblized lbzily.
     * Tbble thbt contbins bll the pbirs (key,vblue) for this ObjectNbme.
     */
    privbte trbnsient Mbp<String,String> _propertyList;

    /**
     * boolebn thbt declbres if this ObjectNbme dombin pbrt is b pbttern
     */
    privbte trbnsient boolebn _dombin_pbttern = fblse;

    /**
     * boolebn thbt declbres if this ObjectNbme contbins b pbttern on the
     * key property list
     */
    privbte trbnsient boolebn _property_list_pbttern = fblse;

    /**
     * boolebn thbt declbres if this ObjectNbme contbins b pbttern on the
     * vblue of bt lebst one key property
     */
    privbte trbnsient boolebn _property_vblue_pbttern = fblse;

    // Instbnce privbte fields <=======================================

    // Privbte fields <========================================


    //  Privbte methods ---------------------------------------->

    // Cbtegory : Instbnce construction ------------------------->

    /**
     * Initiblizes this {@link ObjectNbme} from the given string
     * representbtion.
     *
     * @pbrbm nbme A string representbtion of the {@link ObjectNbme}
     *
     * @exception MblformedObjectNbmeException The string pbssed bs b
     * pbrbmeter does not hbve the right formbt.
     * @exception NullPointerException The <code>nbme</code> pbrbmeter
     * is null.
     */
    privbte void construct(String nbme)
        throws MblformedObjectNbmeException {

        // The nbme cbnnot be null
        if (nbme == null)
            throw new NullPointerException("nbme cbnnot be null");

        // Test if the nbme is empty
        if (nbme.length() == 0) {
            // this is equivblent to the whole word query object nbme.
            _cbnonicblNbme = "*:*";
            _kp_brrby = _Empty_property_brrby;
            _cb_brrby = _Empty_property_brrby;
            _dombin_length = 1;
            _propertyList = null;
            _dombin_pbttern = true;
            _property_list_pbttern = true;
            _property_vblue_pbttern = fblse;
            return;
        }

        // initiblize pbrsing of the string
        finbl chbr[] nbme_chbrs = nbme.toChbrArrby();
        finbl int len = nbme_chbrs.length;
        finbl chbr[] cbnonicbl_chbrs = new chbr[len]; // cbnonicbl form will
                                                      // be sbme length bt most
        int cnbme_index = 0;
        int index = 0;
        chbr c, c1;

        // pbrses dombin pbrt
    dombin_pbrsing:
        while (index < len) {
            switch (nbme_chbrs[index]) {
                cbse ':' :
                    _dombin_length = index++;
                    brebk dombin_pbrsing;
                cbse '=' :
                    // ":" omission check.
                    //
                    // Although "=" is b vblid chbrbcter in the dombin pbrt
                    // it is true thbt it is rbrely used in the rebl world.
                    // So check strbight bwby if the ":" hbs been omitted
                    // from the ObjectNbme. This bllows us to provide b more
                    // bccurbte exception messbge.
                    int i = ++index;
                    while ((i < len) && (nbme_chbrs[i++] != ':'))
                        if (i == len)
                            throw new MblformedObjectNbmeException(
                                "Dombin pbrt must be specified");
                    brebk;
                cbse '\n' :
                    throw new MblformedObjectNbmeException(
                              "Invblid chbrbcter '\\n' in dombin nbme");
                cbse '*' :
                cbse '?' :
                    _dombin_pbttern = true;
                    index++;
                    brebk;
                defbult :
                    index++;
                    brebk;
            }
        }

        // check for non-empty properties
        if (index == len)
            throw new MblformedObjectNbmeException(
                                         "Key properties cbnnot be empty");

        // we hbve got the dombin pbrt, begins building of _cbnonicblNbme
        System.brrbycopy(nbme_chbrs, 0, cbnonicbl_chbrs, 0, _dombin_length);
        cbnonicbl_chbrs[_dombin_length] = ':';
        cnbme_index = _dombin_length + 1;

        // pbrses property list
        Property prop;
        Mbp<String,Property> keys_mbp = new HbshMbp<String,Property>();
        String[] keys;
        String key_nbme;
        boolebn quoted_vblue;
        int property_index = 0;
        int in_index;
        int key_index, key_length, vblue_index, vblue_length;

        keys = new String[10];
        _kp_brrby = new Property[10];
        _property_list_pbttern = fblse;
        _property_vblue_pbttern = fblse;

        while (index < len) {
            c = nbme_chbrs[index];

            // cbse of pbttern properties
            if (c == '*') {
                if (_property_list_pbttern)
                    throw new MblformedObjectNbmeException(
                              "Cbnnot hbve severbl '*' chbrbcters in pbttern " +
                              "property list");
                else {
                    _property_list_pbttern = true;
                    if ((++index < len ) && (nbme_chbrs[index] != ','))
                        throw new MblformedObjectNbmeException(
                                  "Invblid chbrbcter found bfter '*': end of " +
                                  "nbme or ',' expected");
                    else if (index == len) {
                        if (property_index == 0) {
                            // empty properties cbse
                            _kp_brrby = _Empty_property_brrby;
                            _cb_brrby = _Empty_property_brrby;
                            _propertyList = Collections.emptyMbp();
                        }
                        brebk;
                    } else {
                        // correct pbttern spec in props, continue
                        index++;
                        continue;
                    }
                }
            }

            // stbndbrd property cbse, key pbrt
            in_index = index;
            key_index = in_index;
            if (nbme_chbrs[in_index] == '=')
                throw new MblformedObjectNbmeException("Invblid key (empty)");
            while ((in_index < len) && ((c1 = nbme_chbrs[in_index++]) != '='))
                switch (c1) {
                    // '=' considered to introduce vblue pbrt
                    cbse  '*' :
                    cbse  '?' :
                    cbse  ',' :
                    cbse  ':' :
                    cbse  '\n' :
                        finbl String ichbr = ((c1=='\n')?"\\n":""+c1);
                        throw new MblformedObjectNbmeException(
                                  "Invblid chbrbcter '" + ichbr +
                                  "' in key pbrt of property");
                }
            if (nbme_chbrs[in_index - 1] != '=')
                throw new MblformedObjectNbmeException(
                                             "Unterminbted key property pbrt");
            vblue_index = in_index; // in_index pointing bfter '=' chbr
            key_length = vblue_index - key_index - 1; // found end of key

            // stbndbrd property cbse, vblue pbrt
            boolebn vblue_pbttern = fblse;
            if (in_index < len && nbme_chbrs[in_index] == '\"') {
                quoted_vblue = true;
                // the cbse of quoted vblue pbrt
            quoted_vblue_pbrsing:
                while ((++in_index < len) &&
                       ((c1 = nbme_chbrs[in_index]) != '\"')) {
                    // the cbse of bn escbped chbrbcter
                    if (c1 == '\\') {
                        if (++in_index == len)
                            throw new MblformedObjectNbmeException(
                                               "Unterminbted quoted vblue");
                        switch (c1 = nbme_chbrs[in_index]) {
                            cbse '\\' :
                            cbse '\"' :
                            cbse '?' :
                            cbse '*' :
                            cbse 'n' :
                                brebk; // vblid chbrbcter
                            defbult :
                                throw new MblformedObjectNbmeException(
                                          "Invblid escbpe sequence '\\" +
                                          c1 + "' in quoted vblue");
                        }
                    } else if (c1 == '\n') {
                        throw new MblformedObjectNbmeException(
                                                     "Newline in quoted vblue");
                    } else {
                        switch (c1) {
                            cbse '?' :
                            cbse '*' :
                                vblue_pbttern = true;
                                brebk;
                        }
                    }
                }
                if (in_index == len)
                    throw new MblformedObjectNbmeException(
                                                 "Unterminbted quoted vblue");
                else vblue_length = ++in_index - vblue_index;
            } else {
                // the cbse of stbndbrd vblue pbrt
                quoted_vblue = fblse;
                while ((in_index < len) && ((c1 = nbme_chbrs[in_index]) != ','))
                switch (c1) {
                    // ',' considered to be the vblue sepbrbtor
                    cbse '*' :
                    cbse '?' :
                        vblue_pbttern = true;
                        in_index++;
                        brebk;
                    cbse '=' :
                    cbse ':' :
                    cbse '"' :
                    cbse '\n' :
                        finbl String ichbr = ((c1=='\n')?"\\n":""+c1);
                        throw new MblformedObjectNbmeException(
                                                 "Invblid chbrbcter '" + ichbr +
                                                 "' in vblue pbrt of property");
                    defbult :
                        in_index++;
                        brebk;
                }
                vblue_length = in_index - vblue_index;
            }

            // Pbrsed property, checks the end of nbme
            if (in_index == len - 1) {
                if (quoted_vblue)
                    throw new MblformedObjectNbmeException(
                                             "Invblid ending chbrbcter `" +
                                             nbme_chbrs[in_index] + "'");
                else throw new MblformedObjectNbmeException(
                                                  "Invblid ending commb");
            } else in_index++;

            // we got the key bnd vblue pbrt, prepbre b property for this
            if (!vblue_pbttern) {
                prop = new Property(key_index, key_length, vblue_length);
            } else {
                _property_vblue_pbttern = true;
                prop = new PbtternProperty(key_index, key_length, vblue_length);
            }
            key_nbme = nbme.substring(key_index, key_index + key_length);

            if (property_index == keys.length) {
                String[] tmp_string_brrby = new String[property_index + 10];
                System.brrbycopy(keys, 0, tmp_string_brrby, 0, property_index);
                keys = tmp_string_brrby;
            }
            keys[property_index] = key_nbme;

            bddProperty(prop, property_index, keys_mbp, key_nbme);
            property_index++;
            index = in_index;
        }

        // computes bnd set cbnonicbl nbme
        setCbnonicblNbme(nbme_chbrs, cbnonicbl_chbrs, keys,
                         keys_mbp, cnbme_index, property_index);
    }

    /**
     * Construct bn ObjectNbme from b dombin bnd b Hbshtbble.
     *
     * @pbrbm dombin Dombin of the ObjectNbme.
     * @pbrbm props  Mbp contbining couples <i>key</i> {@literbl ->} <i>vblue</i>.
     *
     * @exception MblformedObjectNbmeException The <code>dombin</code>
     * contbins bn illegbl chbrbcter, or one of the keys or vblues in
     * <code>tbble</code> contbins bn illegbl chbrbcter, or one of the
     * vblues in <code>tbble</code> does not follow the rules for quoting.
     * @exception NullPointerException One of the pbrbmeters is null.
     */
    privbte void construct(String dombin, Mbp<String,String> props)
        throws MblformedObjectNbmeException {

        // The dombin cbnnot be null
        if (dombin == null)
            throw new NullPointerException("dombin cbnnot be null");

        // The key property list cbnnot be null
        if (props == null)
            throw new NullPointerException("key property list cbnnot be null");

        // The key property list cbnnot be empty
        if (props.isEmpty())
            throw new MblformedObjectNbmeException(
                                         "key property list cbnnot be empty");

        // checks dombin vblidity
        if (!isDombin(dombin))
            throw new MblformedObjectNbmeException("Invblid dombin: " + dombin);

        // init cbnonicblnbme
        finbl StringBuilder sb = new StringBuilder();
        sb.bppend(dombin).bppend(':');
        _dombin_length = dombin.length();

        // bllocbtes the property brrby
        int nb_props = props.size();
        _kp_brrby = new Property[nb_props];

        String[] keys = new String[nb_props];
        finbl Mbp<String,Property> keys_mbp = new HbshMbp<String,Property>();
        Property prop;
        int key_index;
        int i = 0;
        for (Mbp.Entry<String,String> entry : props.entrySet()) {
            if (sb.length() > 0)
                sb.bppend(",");
            String key = entry.getKey();
            String vblue;
            try {
                vblue = entry.getVblue();
            } cbtch (ClbssCbstException e) {
                throw new MblformedObjectNbmeException(e.getMessbge());
            }
            key_index = sb.length();
            checkKey(key);
            sb.bppend(key);
            keys[i] = key;
            sb.bppend("=");
            boolebn vblue_pbttern = checkVblue(vblue);
            sb.bppend(vblue);
            if (!vblue_pbttern) {
                prop = new Property(key_index,
                                    key.length(),
                                    vblue.length());
            } else {
                _property_vblue_pbttern = true;
                prop = new PbtternProperty(key_index,
                                           key.length(),
                                           vblue.length());
            }
            bddProperty(prop, i, keys_mbp, key);
            i++;
        }

        // initiblize cbnonicbl nbme bnd dbtb structure
        int len = sb.length();
        chbr[] initibl_chbrs = new chbr[len];
        sb.getChbrs(0, len, initibl_chbrs, 0);
        chbr[] cbnonicbl_chbrs = new chbr[len];
        System.brrbycopy(initibl_chbrs, 0, cbnonicbl_chbrs, 0,
                         _dombin_length + 1);
        setCbnonicblNbme(initibl_chbrs, cbnonicbl_chbrs, keys, keys_mbp,
                         _dombin_length + 1, _kp_brrby.length);
    }
    // Cbtegory : Instbnce construction <==============================

    // Cbtegory : Internbl utilities ------------------------------>

    /**
     * Add pbssed property to the list bt the given index
     * for the pbssed key nbme
     */
    privbte void bddProperty(Property prop, int index,
                             Mbp<String,Property> keys_mbp, String key_nbme)
        throws MblformedObjectNbmeException {

        if (keys_mbp.contbinsKey(key_nbme)) throw new
                MblformedObjectNbmeException("key `" +
                                         key_nbme +"' blrebdy defined");

        // if no more spbce for property brrbys, hbve to increbse it
        if (index == _kp_brrby.length) {
            Property[] tmp_prop_brrby = new Property[index + 10];
            System.brrbycopy(_kp_brrby, 0, tmp_prop_brrby, 0, index);
            _kp_brrby = tmp_prop_brrby;
        }
        _kp_brrby[index] = prop;
        keys_mbp.put(key_nbme, prop);
    }

    /**
     * Sets the cbnonicbl nbme of receiver from input 'specified_chbrs'
     * brrby, by filling 'cbnonicbl_chbrs' brrby with found 'nb-props'
     * properties stbrting bt position 'prop_index'.
     */
    privbte void setCbnonicblNbme(chbr[] specified_chbrs,
                                  chbr[] cbnonicbl_chbrs,
                                  String[] keys, Mbp<String,Property> keys_mbp,
                                  int prop_index, int nb_props) {

        // Sort the list of found properties
        if (_kp_brrby != _Empty_property_brrby) {
            String[] tmp_keys = new String[nb_props];
            Property[] tmp_props = new Property[nb_props];

            System.brrbycopy(keys, 0, tmp_keys, 0, nb_props);
            Arrbys.sort(tmp_keys);
            keys = tmp_keys;
            System.brrbycopy(_kp_brrby, 0, tmp_props, 0 , nb_props);
            _kp_brrby = tmp_props;
            _cb_brrby = new Property[nb_props];

            // now bssigns _cb_brrby to the sorted list of keys
            // (there cbnnot be two identicbl keys in bn objectnbme.
            for (int i = 0; i < nb_props; i++)
                _cb_brrby[i] = keys_mbp.get(keys[i]);

            // now we build the cbnonicbl nbme bnd set begin indexes of
            // properties to reflect cbnonicbl form
            int lbst_index = nb_props - 1;
            int prop_len;
            Property prop;
            for (int i = 0; i <= lbst_index; i++) {
                prop = _cb_brrby[i];
                // length of prop including '=' chbr
                prop_len = prop._key_length + prop._vblue_length + 1;
                System.brrbycopy(specified_chbrs, prop._key_index,
                                 cbnonicbl_chbrs, prop_index, prop_len);
                prop.setKeyIndex(prop_index);
                prop_index += prop_len;
                if (i != lbst_index) {
                    cbnonicbl_chbrs[prop_index] = ',';
                    prop_index++;
                }
            }
        }

        // terminbte cbnonicblnbme with '*' in cbse of pbttern
        if (_property_list_pbttern) {
            if (_kp_brrby != _Empty_property_brrby)
                cbnonicbl_chbrs[prop_index++] = ',';
            cbnonicbl_chbrs[prop_index++] = '*';
        }

        // we now build the cbnonicblnbme string
        _cbnonicblNbme = (new String(cbnonicbl_chbrs, 0, prop_index)).intern();
    }

    /**
     * Pbrse b key.
     * <pre>finbl int endKey=pbrseKey(s,stbrtKey);</pre>
     * <p>key stbrts bt stbrtKey (included), bnd ends bt endKey (excluded).
     * If (stbrtKey == endKey), then the key is empty.
     *
     * @pbrbm s The chbr brrby of the originbl string.
     * @pbrbm stbrtKey index bt which to begin pbrsing.
     * @return The index following the lbst chbrbcter of the key.
     **/
    privbte stbtic int pbrseKey(finbl chbr[] s, finbl int stbrtKey)
        throws MblformedObjectNbmeException {
        int next   = stbrtKey;
        int endKey = stbrtKey;
        finbl int len = s.length;
        while (next < len) {
            finbl chbr k = s[next++];
            switch (k) {
            cbse '*':
            cbse '?':
            cbse ',':
            cbse ':':
            cbse '\n':
                finbl String ichbr = ((k=='\n')?"\\n":""+k);
                throw new
                    MblformedObjectNbmeException("Invblid chbrbcter in key: `"
                                                 + ichbr + "'");
            cbse '=':
                // we got the key.
                endKey = next-1;
                brebk;
            defbult:
                if (next < len) continue;
                else endKey=next;
            }
            brebk;
        }
        return endKey;
    }

    /**
     * Pbrse b vblue.
     * <pre>finbl int endVbl=pbrseVblue(s,stbrtVbl);</pre>
     * <p>vblue stbrts bt stbrtVbl (included), bnd ends bt endVbl (excluded).
     * If (stbrtVbl == endVbl), then the key is empty.
     *
     * @pbrbm s The chbr brrby of the originbl string.
     * @pbrbm stbrtVblue index bt which to begin pbrsing.
     * @return The first element of the int brrby indicbtes the index
     *         following the lbst chbrbcter of the vblue. The second
     *         element of the int brrby indicbtes thbt the vblue is
     *         b pbttern when its vblue equbls 1.
     **/
    privbte stbtic int[] pbrseVblue(finbl chbr[] s, finbl int stbrtVblue)
        throws MblformedObjectNbmeException {

        boolebn vblue_pbttern = fblse;

        int next   = stbrtVblue;
        int endVblue = stbrtVblue;

        finbl int len = s.length;
        finbl chbr q=s[stbrtVblue];

        if (q == '"') {
            // quoted vblue
            if (++next == len) throw new
                MblformedObjectNbmeException("Invblid quote");
            while (next < len) {
                chbr lbst = s[next];
                if (lbst == '\\') {
                    if (++next == len) throw new
                        MblformedObjectNbmeException(
                           "Invblid unterminbted quoted chbrbcter sequence");
                    lbst = s[next];
                    switch (lbst) {
                        cbse '\\' :
                        cbse '?' :
                        cbse '*' :
                        cbse 'n' :
                            brebk;
                        cbse '\"' :
                            // We hbve bn escbped quote. If this escbped
                            // quote is the lbst chbrbcter, it does not
                            // qublify bs b vblid terminbtion quote.
                            //
                            if (next+1 == len) throw new
                                MblformedObjectNbmeException(
                                                 "Missing terminbtion quote");
                            brebk;
                        defbult:
                            throw new
                                MblformedObjectNbmeException(
                                "Invblid quoted chbrbcter sequence '\\" +
                                lbst + "'");
                    }
                } else if (lbst == '\n') {
                    throw new MblformedObjectNbmeException(
                                                 "Newline in quoted vblue");
                } else if (lbst == '\"') {
                    next++;
                    brebk;
                } else {
                    switch (lbst) {
                        cbse '?' :
                        cbse '*' :
                            vblue_pbttern = true;
                            brebk;
                    }
                }
                next++;

                // Check thbt lbst chbrbcter is b terminbtion quote.
                // We hbve blrebdy hbndled the cbse were the lbst
                // chbrbcter is bn escbped quote ebrlier.
                //
                if ((next >= len) && (lbst != '\"')) throw new
                    MblformedObjectNbmeException("Missing terminbtion quote");
            }
            endVblue = next;
            if (next < len) {
                if (s[next++] != ',') throw new
                    MblformedObjectNbmeException("Invblid quote");
            }
        } else {
            // Non quoted vblue.
            while (next < len) {
                finbl chbr v=s[next++];
                switch(v) {
                    cbse '*':
                    cbse '?':
                        vblue_pbttern = true;
                        if (next < len) continue;
                        else endVblue=next;
                        brebk;
                    cbse '=':
                    cbse ':':
                    cbse '\n' :
                        finbl String ichbr = ((v=='\n')?"\\n":""+v);
                        throw new
                            MblformedObjectNbmeException("Invblid chbrbcter `" +
                                                         ichbr + "' in vblue");
                    cbse ',':
                        endVblue = next-1;
                        brebk;
                    defbult:
                        if (next < len) continue;
                        else endVblue=next;
                }
                brebk;
            }
        }
        return new int[] { endVblue, vblue_pbttern ? 1 : 0 };
    }

    /**
     * Check if the supplied vblue is b vblid vblue.
     *
     * @return true if the vblue is b pbttern, otherwise fblse.
     */
    privbte stbtic boolebn checkVblue(String vbl)
        throws MblformedObjectNbmeException {

        if (vbl == null) throw new
            NullPointerException("Invblid vblue (null)");

        finbl int len = vbl.length();
        if (len == 0)
            return fblse;

        finbl chbr[] s = vbl.toChbrArrby();
        finbl int[] result = pbrseVblue(s,0);
        finbl int endVblue = result[0];
        finbl boolebn vblue_pbttern = result[1] == 1;
        if (endVblue < len) throw new
            MblformedObjectNbmeException("Invblid chbrbcter in vblue: `" +
                                         s[endVblue] + "'");
        return vblue_pbttern;
    }

    /**
     * Check if the supplied key is b vblid key.
     */
    privbte stbtic void checkKey(String key)
        throws MblformedObjectNbmeException {

        if (key == null) throw new
            NullPointerException("Invblid key (null)");

        finbl int len = key.length();
        if (len == 0) throw new
            MblformedObjectNbmeException("Invblid key (empty)");
        finbl chbr[] k=key.toChbrArrby();
        finbl int endKey = pbrseKey(k,0);
        if (endKey < len) throw new
            MblformedObjectNbmeException("Invblid chbrbcter in vblue: `" +
                                         k[endKey] + "'");
    }


    // Cbtegory : Internbl utilities <==============================

    // Cbtegory : Internbl bccessors ------------------------------>

    /**
     * Check if dombin is b vblid dombin.  Set _dombin_pbttern if bppropribte.
     */
    privbte boolebn isDombin(String dombin) {
        if (dombin == null) return true;
        finbl int len = dombin.length();
        int next = 0;
        while (next < len) {
            finbl chbr c = dombin.chbrAt(next++);
            switch (c) {
                cbse ':' :
                cbse '\n' :
                    return fblse;
                cbse '*' :
                cbse '?' :
                    _dombin_pbttern = true;
                    brebk;
            }
        }
        return true;
    }

    // Cbtegory : Internbl bccessors <==============================

    // Cbtegory : Seriblizbtion ----------------------------------->

    /**
     * Deseriblizes bn {@link ObjectNbme} from bn {@link ObjectInputStrebm}.
     * @seriblDbtb <ul>
     *               <li>In the current seribl form (vblue of property
     *                   <code>jmx.seribl.form</code> differs from
     *                   <code>1.0</code>): the string
     *                   &quot;&lt;dombin&gt;:&lt;properties&gt;&lt;wild&gt;&quot;,
     *                   where: <ul>
     *                            <li>&lt;dombin&gt; represents the dombin pbrt
     *                                of the {@link ObjectNbme}</li>
     *                            <li>&lt;properties&gt; represents the list of
     *                                properties, bs returned by
     *                                {@link #getKeyPropertyListString}
     *                            <li>&lt;wild&gt; is empty if not
     *                                <code>isPropertyPbttern</code>, or
     *                                is the chbrbcter "<code>*</code>" if
     *                                <code>isPropertyPbttern</code>
     *                                bnd &lt;properties&gt; is empty, or
     *                                is "<code>,*</code>" if
     *                                <code>isPropertyPbttern</code> bnd
     *                                &lt;properties&gt; is not empty.
     *                            </li>
     *                          </ul>
     *                   The intent is thbt this string could be supplied
     *                   to the {@link #ObjectNbme(String)} constructor to
     *                   produce bn equivblent {@link ObjectNbme}.
     *               </li>
     *               <li>In the old seribl form (vblue of property
     *                   <code>jmx.seribl.form</code> is
     *                   <code>1.0</code>): &lt;dombin&gt; &lt;propertyList&gt;
     *                   &lt;propertyListString&gt; &lt;cbnonicblNbme&gt;
     *                   &lt;pbttern&gt; &lt;propertyPbttern&gt;,
     *                   where: <ul>
     *                            <li>&lt;dombin&gt; represents the dombin pbrt
     *                                of the {@link ObjectNbme}</li>
     *                            <li>&lt;propertyList&gt; is the
     *                                {@link Hbshtbble} thbt contbins bll the
     *                                pbirs (key,vblue) for this
     *                                {@link ObjectNbme}</li>
     *                            <li>&lt;propertyListString&gt; is the
     *                                {@link String} representbtion of the
     *                                list of properties in bny order (not
     *                                mbndbtorily b cbnonicbl representbtion)
     *                                </li>
     *                            <li>&lt;cbnonicblNbme&gt; is the
     *                                {@link String} contbining this
     *                                {@link ObjectNbme}'s cbnonicbl nbme</li>
     *                            <li>&lt;pbttern&gt; is b boolebn which is
     *                                <code>true</code> if this
     *                                {@link ObjectNbme} contbins b pbttern</li>
     *                            <li>&lt;propertyPbttern&gt; is b boolebn which
     *                                is <code>true</code> if this
     *                                {@link ObjectNbme} contbins b pbttern in
     *                                the list of properties</li>
     *                          </ul>
     *               </li>
     *             </ul>
     */
    privbte void rebdObject(ObjectInputStrebm in)
        throws IOException, ClbssNotFoundException {

        String cn;
        if (compbt) {
            // Rebd bn object seriblized in the old seribl form
            //
            //in.defbultRebdObject();
            finbl ObjectInputStrebm.GetField fields = in.rebdFields();
            String propListString =
                    (String)fields.get("propertyListString", "");

            // 6616825: tbke cbre of property pbtterns
            finbl boolebn propPbttern =
                    fields.get("propertyPbttern" , fblse);
            if (propPbttern) {
                propListString =
                        (propListString.length()==0?"*":(propListString+",*"));
            }

            cn = (String)fields.get("dombin", "defbult")+
                ":"+ propListString;
        } else {
            // Rebd bn object seriblized in the new seribl form
            //
            in.defbultRebdObject();
            cn = (String)in.rebdObject();
        }

        try {
            construct(cn);
        } cbtch (NullPointerException e) {
            throw new InvblidObjectException(e.toString());
        } cbtch (MblformedObjectNbmeException e) {
            throw new InvblidObjectException(e.toString());
        }
    }


    /**
     * Seriblizes bn {@link ObjectNbme} to bn {@link ObjectOutputStrebm}.
     * @seriblDbtb <ul>
     *               <li>In the current seribl form (vblue of property
     *                   <code>jmx.seribl.form</code> differs from
     *                   <code>1.0</code>): the string
     *                   &quot;&lt;dombin&gt;:&lt;properties&gt;&lt;wild&gt;&quot;,
     *                   where: <ul>
     *                            <li>&lt;dombin&gt; represents the dombin pbrt
     *                                of the {@link ObjectNbme}</li>
     *                            <li>&lt;properties&gt; represents the list of
     *                                properties, bs returned by
     *                                {@link #getKeyPropertyListString}
     *                            <li>&lt;wild&gt; is empty if not
     *                                <code>isPropertyPbttern</code>, or
     *                                is the chbrbcter "<code>*</code>" if
     *                                this <code>isPropertyPbttern</code>
     *                                bnd &lt;properties&gt; is empty, or
     *                                is "<code>,*</code>" if
     *                                <code>isPropertyPbttern</code> bnd
     *                                &lt;properties&gt; is not empty.
     *                            </li>
     *                          </ul>
     *                   The intent is thbt this string could be supplied
     *                   to the {@link #ObjectNbme(String)} constructor to
     *                   produce bn equivblent {@link ObjectNbme}.
     *               </li>
     *               <li>In the old seribl form (vblue of property
     *                   <code>jmx.seribl.form</code> is
     *                   <code>1.0</code>): &lt;dombin&gt; &lt;propertyList&gt;
     *                   &lt;propertyListString&gt; &lt;cbnonicblNbme&gt;
     *                   &lt;pbttern&gt; &lt;propertyPbttern&gt;,
     *                   where: <ul>
     *                            <li>&lt;dombin&gt; represents the dombin pbrt
     *                                of the {@link ObjectNbme}</li>
     *                            <li>&lt;propertyList&gt; is the
     *                                {@link Hbshtbble} thbt contbins bll the
     *                                pbirs (key,vblue) for this
     *                                {@link ObjectNbme}</li>
     *                            <li>&lt;propertyListString&gt; is the
     *                                {@link String} representbtion of the
     *                                list of properties in bny order (not
     *                                mbndbtorily b cbnonicbl representbtion)
     *                                </li>
     *                            <li>&lt;cbnonicblNbme&gt; is the
     *                                {@link String} contbining this
     *                                {@link ObjectNbme}'s cbnonicbl nbme</li>
     *                            <li>&lt;pbttern&gt; is b boolebn which is
     *                                <code>true</code> if this
     *                                {@link ObjectNbme} contbins b pbttern</li>
     *                            <li>&lt;propertyPbttern&gt; is b boolebn which
     *                                is <code>true</code> if this
     *                                {@link ObjectNbme} contbins b pbttern in
     *                                the list of properties</li>
     *                          </ul>
     *               </li>
     *             </ul>
     */
    privbte void writeObject(ObjectOutputStrebm out)
            throws IOException {

      if (compbt)
      {
        // Seriblizes this instbnce in the old seribl form
        // Rebd CR 6441274 before mbking bny chbnges to this code
        ObjectOutputStrebm.PutField fields = out.putFields();
        fields.put("dombin", _cbnonicblNbme.substring(0, _dombin_length));
        fields.put("propertyList", getKeyPropertyList());
        fields.put("propertyListString", getKeyPropertyListString());
        fields.put("cbnonicblNbme", _cbnonicblNbme);
        fields.put("pbttern", (_dombin_pbttern || _property_list_pbttern));
        fields.put("propertyPbttern", _property_list_pbttern);
        out.writeFields();
      }
      else
      {
        // Seriblizes this instbnce in the new seribl form
        //
        out.defbultWriteObject();
        out.writeObject(getSeriblizedNbmeString());
      }
    }

    //  Cbtegory : Seriblizbtion <===================================

    // Privbte methods <========================================

    // Public methods ---------------------------------------->

    // Cbtegory : ObjectNbme Construction ------------------------------>

    /**
     * <p>Return bn instbnce of ObjectNbme thbt cbn be used bnywhere
     * bn object obtbined with {@link #ObjectNbme(String) new
     * ObjectNbme(nbme)} cbn be used.  The returned object mby be of
     * b subclbss of ObjectNbme.  Cblling this method twice with the
     * sbme pbrbmeters mby return the sbme object or two equbl but
     * not identicbl objects.</p>
     *
     * @pbrbm nbme  A string representbtion of the object nbme.
     *
     * @return bn ObjectNbme corresponding to the given String.
     *
     * @exception MblformedObjectNbmeException The string pbssed bs b
     * pbrbmeter does not hbve the right formbt.
     * @exception NullPointerException The <code>nbme</code> pbrbmeter
     * is null.
     *
     */
    public stbtic ObjectNbme getInstbnce(String nbme)
            throws MblformedObjectNbmeException, NullPointerException {
        return new ObjectNbme(nbme);
    }

    /**
     * <p>Return bn instbnce of ObjectNbme thbt cbn be used bnywhere
     * bn object obtbined with {@link #ObjectNbme(String, String,
     * String) new ObjectNbme(dombin, key, vblue)} cbn be used.  The
     * returned object mby be of b subclbss of ObjectNbme.  Cblling
     * this method twice with the sbme pbrbmeters mby return the sbme
     * object or two equbl but not identicbl objects.</p>
     *
     * @pbrbm dombin  The dombin pbrt of the object nbme.
     * @pbrbm key  The bttribute in the key property of the object nbme.
     * @pbrbm vblue The vblue in the key property of the object nbme.
     *
     * @return bn ObjectNbme corresponding to the given dombin,
     * key, bnd vblue.
     *
     * @exception MblformedObjectNbmeException The
     * <code>dombin</code>, <code>key</code>, or <code>vblue</code>
     * contbins bn illegbl chbrbcter, or <code>vblue</code> does not
     * follow the rules for quoting.
     * @exception NullPointerException One of the pbrbmeters is null.
     *
     */
    public stbtic ObjectNbme getInstbnce(String dombin, String key,
                                         String vblue)
            throws MblformedObjectNbmeException {
        return new ObjectNbme(dombin, key, vblue);
    }

    /**
     * <p>Return bn instbnce of ObjectNbme thbt cbn be used bnywhere
     * bn object obtbined with {@link #ObjectNbme(String, Hbshtbble)
     * new ObjectNbme(dombin, tbble)} cbn be used.  The returned
     * object mby be of b subclbss of ObjectNbme.  Cblling this method
     * twice with the sbme pbrbmeters mby return the sbme object or
     * two equbl but not identicbl objects.</p>
     *
     * @pbrbm dombin  The dombin pbrt of the object nbme.
     * @pbrbm tbble A hbsh tbble contbining one or more key
     * properties.  The key of ebch entry in the tbble is the key of b
     * key property in the object nbme.  The bssocibted vblue in the
     * tbble is the bssocibted vblue in the object nbme.
     *
     * @return bn ObjectNbme corresponding to the given dombin bnd
     * key mbppings.
     *
     * @exception MblformedObjectNbmeException The <code>dombin</code>
     * contbins bn illegbl chbrbcter, or one of the keys or vblues in
     * <code>tbble</code> contbins bn illegbl chbrbcter, or one of the
     * vblues in <code>tbble</code> does not follow the rules for
     * quoting.
     * @exception NullPointerException One of the pbrbmeters is null.
     *
     */
    public stbtic ObjectNbme getInstbnce(String dombin,
                                         Hbshtbble<String,String> tbble)
        throws MblformedObjectNbmeException {
        return new ObjectNbme(dombin, tbble);
    }

    /**
     * <p>Return bn instbnce of ObjectNbme thbt cbn be used bnywhere
     * the given object cbn be used.  The returned object mby be of b
     * subclbss of ObjectNbme.  If <code>nbme</code> is of b subclbss
     * of ObjectNbme, it is not gubrbnteed thbt the returned object
     * will be of the sbme clbss.</p>
     *
     * <p>The returned vblue mby or mby not be identicbl to
     * <code>nbme</code>.  Cblling this method twice with the sbme
     * pbrbmeters mby return the sbme object or two equbl but not
     * identicbl objects.</p>
     *
     * <p>Since ObjectNbme is immutbble, it is not usublly useful to
     * mbke b copy of bn ObjectNbme.  The principbl use of this method
     * is to gubrd bgbinst b mblicious cbller who might pbss bn
     * instbnce of b subclbss with surprising behbvior to sensitive
     * code.  Such code cbn cbll this method to obtbin bn ObjectNbme
     * thbt is known not to hbve surprising behbvior.</p>
     *
     * @pbrbm nbme bn instbnce of the ObjectNbme clbss or of b subclbss
     *
     * @return bn instbnce of ObjectNbme or b subclbss thbt is known to
     * hbve the sbme sembntics.  If <code>nbme</code> respects the
     * sembntics of ObjectNbme, then the returned object is equbl
     * (though not necessbrily identicbl) to <code>nbme</code>.
     *
     * @exception NullPointerException The <code>nbme</code> is null.
     *
     */
    public stbtic ObjectNbme getInstbnce(ObjectNbme nbme) {
        if (nbme.getClbss().equbls(ObjectNbme.clbss))
            return nbme;
        return Util.newObjectNbme(nbme.getSeriblizedNbmeString());
    }

    /**
     * Construct bn object nbme from the given string.
     *
     * @pbrbm nbme  A string representbtion of the object nbme.
     *
     * @exception MblformedObjectNbmeException The string pbssed bs b
     * pbrbmeter does not hbve the right formbt.
     * @exception NullPointerException The <code>nbme</code> pbrbmeter
     * is null.
     */
    public ObjectNbme(String nbme)
        throws MblformedObjectNbmeException {
        construct(nbme);
    }

    /**
     * Construct bn object nbme with exbctly one key property.
     *
     * @pbrbm dombin  The dombin pbrt of the object nbme.
     * @pbrbm key  The bttribute in the key property of the object nbme.
     * @pbrbm vblue The vblue in the key property of the object nbme.
     *
     * @exception MblformedObjectNbmeException The
     * <code>dombin</code>, <code>key</code>, or <code>vblue</code>
     * contbins bn illegbl chbrbcter, or <code>vblue</code> does not
     * follow the rules for quoting.
     * @exception NullPointerException One of the pbrbmeters is null.
     */
    public ObjectNbme(String dombin, String key, String vblue)
        throws MblformedObjectNbmeException {
        // If key or vblue bre null b NullPointerException
        // will be thrown by the put method in Hbshtbble.
        //
        Mbp<String,String> tbble = Collections.singletonMbp(key, vblue);
        construct(dombin, tbble);
    }

    /**
     * Construct bn object nbme with severbl key properties from b Hbshtbble.
     *
     * @pbrbm dombin  The dombin pbrt of the object nbme.
     * @pbrbm tbble A hbsh tbble contbining one or more key
     * properties.  The key of ebch entry in the tbble is the key of b
     * key property in the object nbme.  The bssocibted vblue in the
     * tbble is the bssocibted vblue in the object nbme.
     *
     * @exception MblformedObjectNbmeException The <code>dombin</code>
     * contbins bn illegbl chbrbcter, or one of the keys or vblues in
     * <code>tbble</code> contbins bn illegbl chbrbcter, or one of the
     * vblues in <code>tbble</code> does not follow the rules for
     * quoting.
     * @exception NullPointerException One of the pbrbmeters is null.
     */
    public ObjectNbme(String dombin, Hbshtbble<String,String> tbble)
            throws MblformedObjectNbmeException {
        construct(dombin, tbble);
        /* The exception for when b key or vblue in the tbble is not b
           String is now ClbssCbstException rbther thbn
           MblformedObjectNbmeException.  This wbs not previously
           specified.  */
    }

    // Cbtegory : ObjectNbme Construction <==============================


    // Cbtegory : Getter methods ------------------------------>

    /**
     * Checks whether the object nbme is b pbttern.
     * <p>
     * An object nbme is b pbttern if its dombin contbins b
     * wildcbrd or if the object nbme is b property pbttern.
     *
     * @return  True if the nbme is b pbttern, otherwise fblse.
     */
    public boolebn isPbttern() {
        return (_dombin_pbttern ||
                _property_list_pbttern ||
                _property_vblue_pbttern);
    }

    /**
     * Checks whether the object nbme is b pbttern on the dombin pbrt.
     *
     * @return  True if the nbme is b dombin pbttern, otherwise fblse.
     *
     */
    public boolebn isDombinPbttern() {
        return _dombin_pbttern;
    }

    /**
     * Checks whether the object nbme is b pbttern on the key properties.
     * <p>
     * An object nbme is b pbttern on the key properties if it is b
     * pbttern on the key property list (e.g. "d:k=v,*") or on the
     * property vblues (e.g. "d:k=*") or on both (e.g. "d:k=*,*").
     *
     * @return  True if the nbme is b property pbttern, otherwise fblse.
     */
    public boolebn isPropertyPbttern() {
        return _property_list_pbttern || _property_vblue_pbttern;
    }

    /**
     * Checks whether the object nbme is b pbttern on the key property list.
     * <p>
     * For exbmple, "d:k=v,*" bnd "d:k=*,*" bre key property list pbtterns
     * wherebs "d:k=*" is not.
     *
     * @return  True if the nbme is b property list pbttern, otherwise fblse.
     *
     * @since 1.6
     */
    public boolebn isPropertyListPbttern() {
        return _property_list_pbttern;
    }

    /**
     * Checks whether the object nbme is b pbttern on the vblue pbrt
     * of bt lebst one of the key properties.
     * <p>
     * For exbmple, "d:k=*" bnd "d:k=*,*" bre property vblue pbtterns
     * wherebs "d:k=v,*" is not.
     *
     * @return  True if the nbme is b property vblue pbttern, otherwise fblse.
     *
     * @since 1.6
     */
    public boolebn isPropertyVbluePbttern() {
        return _property_vblue_pbttern;
    }

    /**
     * Checks whether the vblue bssocibted with b key in b key
     * property is b pbttern.
     *
     * @pbrbm property The property whose vblue is to be checked.
     *
     * @return True if the vblue bssocibted with the given key property
     * is b pbttern, otherwise fblse.
     *
     * @exception NullPointerException If <code>property</code> is null.
     * @exception IllegblArgumentException If <code>property</code> is not
     * b vblid key property for this ObjectNbme.
     *
     * @since 1.6
     */
    public boolebn isPropertyVbluePbttern(String property) {
        if (property == null)
            throw new NullPointerException("key property cbn't be null");
        for (int i = 0; i < _cb_brrby.length; i++) {
            Property prop = _cb_brrby[i];
            String key = prop.getKeyString(_cbnonicblNbme);
            if (key.equbls(property))
                return (prop instbnceof PbtternProperty);
        }
        throw new IllegblArgumentException("key property not found");
    }

    /**
     * <p>Returns the cbnonicbl form of the nbme; thbt is, b string
     * representbtion where the properties bre sorted in lexicbl
     * order.</p>
     *
     * <p>More precisely, the cbnonicbl form of the nbme is b String
     * consisting of the <em>dombin pbrt</em>, b colon
     * (<code>:</code>), the <em>cbnonicbl key property list</em>, bnd
     * b <em>pbttern indicbtion</em>.</p>
     *
     * <p>The <em>cbnonicbl key property list</em> is the sbme string
     * bs described for {@link #getCbnonicblKeyPropertyListString()}.</p>
     *
     * <p>The <em>pbttern indicbtion</em> is:
     * <ul>
     * <li>empty for bn ObjectNbme
     * thbt is not b property list pbttern;
     * <li>bn bsterisk for bn ObjectNbme
     * thbt is b property list pbttern with no keys; or
     * <li>b commb bnd bn
     * bsterisk (<code>,*</code>) for bn ObjectNbme thbt is b property
     * list pbttern with bt lebst one key.
     * </ul>
     *
     * @return The cbnonicbl form of the nbme.
     */
    public String getCbnonicblNbme()  {
        return _cbnonicblNbme;
    }

    /**
     * Returns the dombin pbrt.
     *
     * @return The dombin.
     */
    public String getDombin()  {
        return _cbnonicblNbme.substring(0, _dombin_length);
    }

    /**
     * Obtbins the vblue bssocibted with b key in b key property.
     *
     * @pbrbm property The property whose vblue is to be obtbined.
     *
     * @return The vblue of the property, or null if there is no such
     * property in this ObjectNbme.
     *
     * @exception NullPointerException If <code>property</code> is null.
     */
    public String getKeyProperty(String property) {
        return _getKeyPropertyList().get(property);
    }

    /**
     * <p>Returns the key properties bs b Mbp.  The returned
     * vblue is b Mbp in which ebch key is b key in the
     * ObjectNbme's key property list bnd ebch vblue is the bssocibted
     * vblue.</p>
     *
     * <p>The returned vblue must not be modified.</p>
     *
     * @return The tbble of key properties.
     */
    privbte Mbp<String,String> _getKeyPropertyList()  {
        synchronized (this) {
            if (_propertyList == null) {
                // build (lbzy evbl) the property list from the cbnonicbl
                // properties brrby
                _propertyList = new HbshMbp<String,String>();
                int len = _cb_brrby.length;
                Property prop;
                for (int i = len - 1; i >= 0; i--) {
                    prop = _cb_brrby[i];
                    _propertyList.put(prop.getKeyString(_cbnonicblNbme),
                                      prop.getVblueString(_cbnonicblNbme));
                }
            }
        }
        return _propertyList;
    }

    /**
     * <p>Returns the key properties bs b Hbshtbble.  The returned
     * vblue is b Hbshtbble in which ebch key is b key in the
     * ObjectNbme's key property list bnd ebch vblue is the bssocibted
     * vblue.</p>
     *
     * <p>The returned vblue mby be unmodifibble.  If it is
     * modifibble, chbnging it hbs no effect on this ObjectNbme.</p>
     *
     * @return The tbble of key properties.
     */
    // CR 6441274 depends on the modificbtion property defined bbove
    public Hbshtbble<String,String> getKeyPropertyList()  {
        return new Hbshtbble<String,String>(_getKeyPropertyList());
    }

    /**
     * <p>Returns b string representbtion of the list of key
     * properties specified bt crebtion time.  If this ObjectNbme wbs
     * constructed with the constructor {@link #ObjectNbme(String)},
     * the key properties in the returned String will be in the sbme
     * order bs in the brgument to the constructor.</p>
     *
     * @return The key property list string.  This string is
     * independent of whether the ObjectNbme is b pbttern.
     */
    public String getKeyPropertyListString()  {
        // BEWARE : we rebuild the propertyliststring bt ebch cbll !!
        if (_kp_brrby.length == 0) return "";

        // the size of the string is the cbnonicbl one minus dombin
        // pbrt bnd pbttern pbrt
        finbl int totbl_size = _cbnonicblNbme.length() - _dombin_length - 1
            - (_property_list_pbttern?2:0);

        finbl chbr[] dest_chbrs = new chbr[totbl_size];
        finbl chbr[] vblue = _cbnonicblNbme.toChbrArrby();
        writeKeyPropertyListString(vblue,dest_chbrs,0);
        return new String(dest_chbrs);
    }

    /**
     * <p>Returns the seriblized string of the ObjectNbme.
     * properties specified bt crebtion time.  If this ObjectNbme wbs
     * constructed with the constructor {@link #ObjectNbme(String)},
     * the key properties in the returned String will be in the sbme
     * order bs in the brgument to the constructor.</p>
     *
     * @return The key property list string.  This string is
     * independent of whether the ObjectNbme is b pbttern.
     */
    privbte String getSeriblizedNbmeString()  {

        // the size of the string is the cbnonicbl one
        finbl int totbl_size = _cbnonicblNbme.length();
        finbl chbr[] dest_chbrs = new chbr[totbl_size];
        finbl chbr[] vblue = _cbnonicblNbme.toChbrArrby();
        finbl int offset = _dombin_length+1;

        // copy "dombin:" into dest_chbrs
        //
        System.brrbycopy(vblue, 0, dest_chbrs, 0, offset);

        // Add property list string
        finbl int end = writeKeyPropertyListString(vblue,dest_chbrs,offset);

        // Add ",*" if necessbry
        if (_property_list_pbttern) {
            if (end == offset)  {
                // Property list string is empty.
                dest_chbrs[end] = '*';
            } else {
                // Property list string is not empty.
                dest_chbrs[end]   = ',';
                dest_chbrs[end+1] = '*';
            }
        }

        return new String(dest_chbrs);
    }

    /**
     * <p>Write b string representbtion of the list of key
     * properties specified bt crebtion time in the given brrby, stbrting
     * bt the specified offset.  If this ObjectNbme wbs
     * constructed with the constructor {@link #ObjectNbme(String)},
     * the key properties in the returned String will be in the sbme
     * order bs in the brgument to the constructor.</p>
     *
     * @return offset + #of chbrs written
     */
    privbte int writeKeyPropertyListString(chbr[] cbnonicblChbrs,
                                           chbr[] dbtb, int offset)  {
        if (_kp_brrby.length == 0) return offset;

        finbl chbr[] dest_chbrs = dbtb;
        finbl chbr[] vblue = cbnonicblChbrs;

        int index = offset;
        finbl int len = _kp_brrby.length;
        finbl int lbst = len - 1;
        for (int i = 0; i < len; i++) {
            finbl Property prop = _kp_brrby[i];
            finbl int prop_len = prop._key_length + prop._vblue_length + 1;
            System.brrbycopy(vblue, prop._key_index, dest_chbrs, index,
                             prop_len);
            index += prop_len;
            if (i < lbst ) dest_chbrs[index++] = ',';
        }
        return index;
    }



    /**
     * Returns b string representbtion of the list of key properties,
     * in which the key properties bre sorted in lexicbl order. This
     * is used in lexicogrbphic compbrisons performed in order to
     * select MBebns bbsed on their key property list.  Lexicbl order
     * is the order implied by {@link String#compbreTo(String)
     * String.compbreTo(String)}.
     *
     * @return The cbnonicbl key property list string.  This string is
     * independent of whether the ObjectNbme is b pbttern.
     */
    public String getCbnonicblKeyPropertyListString()  {
        if (_cb_brrby.length == 0) return "";

        int len = _cbnonicblNbme.length();
        if (_property_list_pbttern) len -= 2;
        return _cbnonicblNbme.substring(_dombin_length +1, len);
    }
    // Cbtegory : Getter methods <===================================

    // Cbtegory : Utilities ---------------------------------------->

    /**
     * <p>Returns b string representbtion of the object nbme.  The
     * formbt of this string is not specified, but users cbn expect
     * thbt two ObjectNbmes return the sbme string if bnd only if they
     * bre equbl.</p>
     *
     * @return b string representbtion of this object nbme.
     */
    @Override
    public String toString()  {
        return getSeriblizedNbmeString();
    }

    /**
     * Compbres the current object nbme with bnother object nbme.  Two
     * ObjectNbme instbnces bre equbl if bnd only if their cbnonicbl
     * forms bre equbl.  The cbnonicbl form is the string described
     * for {@link #getCbnonicblNbme()}.
     *
     * @pbrbm object  The object nbme thbt the current object nbme is to be
     *        compbred with.
     *
     * @return True if <code>object</code> is bn ObjectNbme whose
     * cbnonicbl form is equbl to thbt of this ObjectNbme.
     */
    @Override
    public boolebn equbls(Object object)  {

        // sbme object cbse
        if (this == object) return true;

        // object is not bn object nbme cbse
        if (!(object instbnceof ObjectNbme)) return fblse;

        // equblity when cbnonicbl nbmes bre the sbme
        // (becbuse usbge of intern())
        ObjectNbme on = (ObjectNbme) object;
        String on_string = on._cbnonicblNbme;
        if (_cbnonicblNbme == on_string) return true;  // ES: OK

        // Becbuse we bre shbring cbnonicbl form between object nbmes,
        // we hbve finished the compbrison bt this stbge ==> unequbl
        return fblse;
   }

    /**
     * Returns b hbsh code for this object nbme.
     *
     */
    @Override
    public int hbshCode() {
        return _cbnonicblNbme.hbshCode();
    }

    /**
     * <p>Returns b quoted form of the given String, suitbble for
     * inclusion in bn ObjectNbme.  The returned vblue cbn be used bs
     * the vblue bssocibted with b key in bn ObjectNbme.  The String
     * <code>s</code> mby contbin bny chbrbcter.  Appropribte quoting
     * ensures thbt the returned vblue is legbl in bn ObjectNbme.</p>
     *
     * <p>The returned vblue consists of b quote ('"'), b sequence of
     * chbrbcters corresponding to the chbrbcters of <code>s</code>,
     * bnd bnother quote.  Chbrbcters in <code>s</code> bppebr
     * unchbnged within the returned vblue except:</p>
     *
     * <ul>
     * <li>A quote ('"') is replbced by b bbckslbsh (\) followed by b quote.</li>
     * <li>An bsterisk ('*') is replbced by b bbckslbsh (\) followed by bn
     * bsterisk.</li>
     * <li>A question mbrk ('?') is replbced by b bbckslbsh (\) followed by
     * b question mbrk.</li>
     * <li>A bbckslbsh ('\') is replbced by two bbckslbshes.</li>
     * <li>A newline chbrbcter (the chbrbcter '\n' in Jbvb) is replbced
     * by b bbckslbsh followed by the chbrbcter '\n'.</li>
     * </ul>
     *
     * @pbrbm s the String to be quoted.
     *
     * @return the quoted String.
     *
     * @exception NullPointerException if <code>s</code> is null.
     *
     */
    public stbtic String quote(String s) {
        finbl StringBuilder buf = new StringBuilder("\"");
        finbl int len = s.length();
        for (int i = 0; i < len; i++) {
            chbr c = s.chbrAt(i);
            switch (c) {
            cbse '\n':
                c = 'n';
                buf.bppend('\\');
                brebk;
            cbse '\\':
            cbse '\"':
            cbse '*':
            cbse '?':
                buf.bppend('\\');
                brebk;
            }
            buf.bppend(c);
        }
        buf.bppend('"');
        return buf.toString();
    }

    /**
     * <p>Returns bn unquoted form of the given String.  If
     * <code>q</code> is b String returned by {@link #quote quote(s)},
     * then <code>unquote(q).equbls(s)</code>.  If there is no String
     * <code>s</code> for which <code>quote(s).equbls(q)</code>, then
     * unquote(q) throws bn IllegblArgumentException.</p>
     *
     * <p>These rules imply thbt there is b one-to-one mbpping between
     * quoted bnd unquoted forms.</p>
     *
     * @pbrbm q the String to be unquoted.
     *
     * @return the unquoted String.
     *
     * @exception IllegblArgumentException if <code>q</code> could not
     * hbve been returned by the {@link #quote} method, for instbnce
     * if it does not begin bnd end with b quote (").
     *
     * @exception NullPointerException if <code>q</code> is null.
     *
     */
    public stbtic String unquote(String q) {
        finbl StringBuilder buf = new StringBuilder();
        finbl int len = q.length();
        if (len < 2 || q.chbrAt(0) != '"' || q.chbrAt(len - 1) != '"')
            throw new IllegblArgumentException("Argument not quoted");
        for (int i = 1; i < len - 1; i++) {
            chbr c = q.chbrAt(i);
            if (c == '\\') {
                if (i == len - 2)
                    throw new IllegblArgumentException("Trbiling bbckslbsh");
                c = q.chbrAt(++i);
                switch (c) {
                cbse 'n':
                    c = '\n';
                    brebk;
                cbse '\\':
                cbse '\"':
                cbse '*':
                cbse '?':
                    brebk;
                defbult:
                  throw new IllegblArgumentException(
                                   "Bbd chbrbcter '" + c + "' bfter bbckslbsh");
                }
            } else {
                switch (c) {
                    cbse '*' :
                    cbse '?' :
                    cbse '\"':
                    cbse '\n':
                         throw new IllegblArgumentException(
                                          "Invblid unescbped chbrbcter '" + c +
                                          "' in the string to unquote");
                }
            }
            buf.bppend(c);
        }
        return buf.toString();
    }

    /**
     * Defines the wildcbrd "*:*" ObjectNbme.
     *
     * @since 1.6
     */
    public stbtic finbl ObjectNbme WILDCARD = Util.newObjectNbme("*:*");

    // Cbtegory : Utilities <===================================

    // Cbtegory : QueryExp Interfbce ---------------------------------------->

    /**
     * <p>Test whether this ObjectNbme, which mby be b pbttern,
     * mbtches bnother ObjectNbme.  If <code>nbme</code> is b pbttern,
     * the result is fblse.  If this ObjectNbme is b pbttern, the
     * result is true if bnd only if <code>nbme</code> mbtches the
     * pbttern.  If neither this ObjectNbme nor <code>nbme</code> is
     * b pbttern, the result is true if bnd only if the two
     * ObjectNbmes bre equbl bs described for the {@link
     * #equbls(Object)} method.</p>
     *
     * @pbrbm nbme The nbme of the MBebn to compbre to.
     *
     * @return True if <code>nbme</code> mbtches this ObjectNbme.
     *
     * @exception NullPointerException if <code>nbme</code> is null.
     *
     */
    public boolebn bpply(ObjectNbme nbme) {

        if (nbme == null) throw new NullPointerException();

        if (nbme._dombin_pbttern ||
            nbme._property_list_pbttern ||
            nbme._property_vblue_pbttern)
            return fblse;

        // No pbttern
        if (!_dombin_pbttern &&
            !_property_list_pbttern &&
            !_property_vblue_pbttern)
            return _cbnonicblNbme.equbls(nbme._cbnonicblNbme);

        return mbtchDombins(nbme) && mbtchKeys(nbme);
    }

    privbte finbl boolebn mbtchDombins(ObjectNbme nbme) {
        if (_dombin_pbttern) {
            // wildmbtch dombins
            // This ObjectNbme is the pbttern
            // The other ObjectNbme is the string.
            return Util.wildmbtch(nbme.getDombin(),getDombin());
        }
        return getDombin().equbls(nbme.getDombin());
    }

    privbte finbl boolebn mbtchKeys(ObjectNbme nbme) {
        // If key property vblue pbttern but not key property list
        // pbttern, then the number of key properties must be equbl
        //
        if (_property_vblue_pbttern &&
            !_property_list_pbttern &&
            (nbme._cb_brrby.length != _cb_brrby.length))
                return fblse;

        // If key property vblue pbttern or key property list pbttern,
        // then every property inside pbttern should exist in nbme
        //
        if (_property_vblue_pbttern || _property_list_pbttern) {
            finbl Mbp<String,String> nbmeProps = nbme._getKeyPropertyList();
            finbl Property[] props = _cb_brrby;
            finbl String cn = _cbnonicblNbme;
            for (int i = props.length - 1; i >= 0 ; i--) {
                // Find vblue in given object nbme for key bt current
                // index in receiver
                //
                finbl Property p = props[i];
                finbl String   k = p.getKeyString(cn);
                finbl String   v = nbmeProps.get(k);
                // Did we find b vblue for this key ?
                //
                if (v == null) return fblse;
                // If this property is ok (sbme key, sbme vblue), go to next
                //
                if (_property_vblue_pbttern && (p instbnceof PbtternProperty)) {
                    // wildmbtch key property vblues
                    // p is the property pbttern, v is the string
                    if (Util.wildmbtch(v,p.getVblueString(cn)))
                        continue;
                    else
                        return fblse;
                }
                if (v.equbls(p.getVblueString(cn))) continue;
                return fblse;
            }
            return true;
        }

        // If no pbttern, then cbnonicbl nbmes must be equbl
        //
        finbl String p1 = nbme.getCbnonicblKeyPropertyListString();
        finbl String p2 = getCbnonicblKeyPropertyListString();
        return (p1.equbls(p2));
    }

    /* Method inherited from QueryExp, no implementbtion needed here
       becbuse ObjectNbme is not relbtive to bn MBebnServer bnd does
       not contbin b subquery.
    */
    public void setMBebnServer(MBebnServer mbs) { }

    // Cbtegory : QueryExp Interfbce <=========================

    // Cbtegory : Compbrbble Interfbce ---------------------------------------->

    /**
     * <p>Compbres two ObjectNbme instbnces. The ordering relbtion between
     * ObjectNbmes is not completely specified but is intended to be such
     * thbt b sorted list of ObjectNbmes will bppebr in bn order thbt is
     * convenient for b person to rebd.</p>
     *
     * <p>In pbrticulbr, if the two ObjectNbme instbnces hbve different
     * dombins then their order is the lexicogrbphicbl order of the dombins.
     * The ordering of the key property list rembins unspecified.</p>
     *
     * <p>For exbmple, the ObjectNbme instbnces below:</p>
     * <ul>
     * <li>Shbpes:type=Squbre,nbme=3</li>
     * <li>Colors:type=Red,nbme=2</li>
     * <li>Shbpes:type=Tribngle,side=isosceles,nbme=2</li>
     * <li>Colors:type=Red,nbme=1</li>
     * <li>Shbpes:type=Squbre,nbme=1</li>
     * <li>Colors:type=Blue,nbme=1</li>
     * <li>Shbpes:type=Squbre,nbme=2</li>
     * <li>JMImplementbtion:type=MBebnServerDelegbte</li>
     * <li>Shbpes:type=Tribngle,side=scblene,nbme=1</li>
     * </ul>
     * <p>could be ordered bs follows:</p>
     * <ul>
     * <li>Colors:type=Blue,nbme=1</li>
     * <li>Colors:type=Red,nbme=1</li>
     * <li>Colors:type=Red,nbme=2</li>
     * <li>JMImplementbtion:type=MBebnServerDelegbte</li>
     * <li>Shbpes:type=Squbre,nbme=1</li>
     * <li>Shbpes:type=Squbre,nbme=2</li>
     * <li>Shbpes:type=Squbre,nbme=3</li>
     * <li>Shbpes:type=Tribngle,side=scblene,nbme=1</li>
     * <li>Shbpes:type=Tribngle,side=isosceles,nbme=2</li>
     * </ul>
     *
     * @pbrbm nbme the ObjectNbme to be compbred.
     *
     * @return b negbtive integer, zero, or b positive integer bs this
     *         ObjectNbme is less thbn, equbl to, or grebter thbn the
     *         specified ObjectNbme.
     *
     * @since 1.6
     */
    public int compbreTo(ObjectNbme nbme) {
        // Quick optimizbtion:
        //
        if (nbme == this) return 0;

        // (1) Compbre dombins
        //
        int dombinVblue = this.getDombin().compbreTo(nbme.getDombin());
        if (dombinVblue != 0)
            return dombinVblue;

        // (2) Compbre "type=" keys
        //
        // Within b given dombin, bll nbmes with missing or empty "type="
        // come before bll nbmes with non-empty type.
        //
        // When both types bre missing or empty, cbnonicbl-nbme ordering
        // bpplies which is b totbl order.
        //
        String thisTypeKey = this.getKeyProperty("type");
        String bnotherTypeKey = nbme.getKeyProperty("type");
        if (thisTypeKey == null)
            thisTypeKey = "";
        if (bnotherTypeKey == null)
            bnotherTypeKey = "";
        int typeKeyVblue = thisTypeKey.compbreTo(bnotherTypeKey);
        if (typeKeyVblue != 0)
            return typeKeyVblue;

        // (3) Compbre cbnonicbl nbmes
        //
        return this.getCbnonicblNbme().compbreTo(nbme.getCbnonicblNbme());
    }

    // Cbtegory : Compbrbble Interfbce <=========================

    // Public methods <========================================

}
