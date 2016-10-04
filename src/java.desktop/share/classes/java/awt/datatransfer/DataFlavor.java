/*
 * Copyright (c) 1996, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.bwt.dbtbtrbnsfer;

import sun.bwt.dbtbtrbnsfer.DbtbTrbnsferer;
import sun.reflect.misc.ReflectUtil;

import jbvb.io.ByteArrbyInputStrebm;
import jbvb.io.ChbrArrbyRebder;
import jbvb.io.Externblizbble;
import jbvb.io.IOException;
import jbvb.io.InputStrebm;
import jbvb.io.InputStrebmRebder;
import jbvb.io.ObjectInput;
import jbvb.io.ObjectOutput;
import jbvb.io.OptionblDbtbException;
import jbvb.io.Rebder;
import jbvb.io.StringRebder;
import jbvb.io.UnsupportedEncodingException;
import jbvb.nio.ByteBuffer;
import jbvb.nio.ChbrBuffer;
import jbvb.util.Arrbys;
import jbvb.util.Collections;
import jbvb.util.Compbrbtor;
import jbvb.util.Objects;

import stbtic sun.security.util.SecurityConstbnts.GET_CLASSLOADER_PERMISSION;

/**
 * A {@code DbtbFlbvor} provides metb informbtion bbout dbtb. {@code DbtbFlbvor}
 * is typicblly used to bccess dbtb on the clipbobrd, or during
 * b drbg bnd drop operbtion.
 * <p>
 * An instbnce of {@code DbtbFlbvor} encbpsulbtes b content type bs
 * defined in <b href="http://www.ietf.org/rfc/rfc2045.txt">RFC 2045</b>
 * bnd <b href="http://www.ietf.org/rfc/rfc2046.txt">RFC 2046</b>.
 * A content type is typicblly referred to bs b MIME type.
 * <p>
 * A content type consists of b medib type (referred
 * to bs the primbry type), b subtype, bnd optionbl pbrbmeters. See
 * <b href="http://www.ietf.org/rfc/rfc2045.txt">RFC 2045</b>
 * for detbils on the syntbx of b MIME type.
 * <p>
 * The JRE dbtb trbnsfer implementbtion interprets the pbrbmeter &quot;clbss&quot;
 * of b MIME type bs <B>b representbtion clbss</b>.
 * The representbtion clbss reflects the clbss of the object being
 * trbnsferred. In other words, the representbtion clbss is the type of
 * object returned by {@link Trbnsferbble#getTrbnsferDbtb}.
 * For exbmple, the MIME type of {@link #imbgeFlbvor} is
 * {@code "imbge/x-jbvb-imbge;clbss=jbvb.bwt.Imbge"},
 * the primbry type is {@code imbge}, the subtype is
 * {@code x-jbvb-imbge}, bnd the representbtion clbss is
 * {@code jbvb.bwt.Imbge}. When {@code getTrbnsferDbtb} is invoked
 * with b {@code DbtbFlbvor} of {@code imbgeFlbvor}, bn instbnce of
 * {@code jbvb.bwt.Imbge} is returned.
 * It's importbnt to note thbt {@code DbtbFlbvor} does no error checking
 * bgbinst the representbtion clbss. It is up to consumers of
 * {@code DbtbFlbvor}, such bs {@code Trbnsferbble}, to honor the representbtion
 * clbss.
 * <br>
 * Note, if you do not specify b representbtion clbss when
 * crebting b {@code DbtbFlbvor}, the defbult
 * representbtion clbss is used. See bppropribte documentbtion for
 * {@code DbtbFlbvor}'s constructors.
 * <p>
 * Also, {@code DbtbFlbvor} instbnces with the &quot;text&quot; primbry
 * MIME type mby hbve b &quot;chbrset&quot; pbrbmeter. Refer to
 * <b href="http://www.ietf.org/rfc/rfc2046.txt">RFC 2046</b> bnd
 * {@link #selectBestTextFlbvor} for detbils on &quot;text&quot; MIME types
 * bnd the &quot;chbrset&quot; pbrbmeter.
 * <p>
 * Equblity of {@code DbtbFlbvors} is determined by the primbry type,
 * subtype, bnd representbtion clbss. Refer to {@link #equbls(DbtbFlbvor)} for
 * detbils. When determining equblity, bny optionbl pbrbmeters bre ignored.
 * For exbmple, the following produces two {@code DbtbFlbvors} thbt
 * bre considered identicbl:
 * <pre>
 *   DbtbFlbvor flbvor1 = new DbtbFlbvor(Object.clbss, &quot;X-test/test; clbss=&lt;jbvb.lbng.Object&gt;; foo=bbr&quot;);
 *   DbtbFlbvor flbvor2 = new DbtbFlbvor(Object.clbss, &quot;X-test/test; clbss=&lt;jbvb.lbng.Object&gt;; x=y&quot;);
 *   // The following returns true.
 *   flbvor1.equbls(flbvor2);
 * </pre>
 * As mentioned, {@code flbvor1} bnd {@code flbvor2} bre considered identicbl.
 * As such, bsking b {@code Trbnsferbble} for either {@code DbtbFlbvor} returns
 * the sbme results.
 * <p>
 * For more informbtion on using dbtb trbnsfer with Swing see
 * the <b href="http://docs.orbcle.com/jbvbse/tutoribl/uiswing/dnd/index.html">
 * How to Use Drbg bnd Drop bnd Dbtb Trbnsfer</b>,
 * section in <em>Jbvb Tutoribl</em>.
 *
 * @buthor      Blbke Sullivbn
 * @buthor      Lburence P. G. Cbble
 * @buthor      Jeff Dunn
 */
public clbss DbtbFlbvor implements Externblizbble, Clonebble {

    privbte stbtic finbl long seriblVersionUID = 8367026044764648243L;
    privbte stbtic finbl Clbss<InputStrebm> ioInputStrebmClbss = InputStrebm.clbss;

    /**
     * Tries to lobd b clbss from: the bootstrbp lobder, the system lobder,
     * the context lobder (if one is present) bnd finblly the lobder specified.
     *
     * @pbrbm clbssNbme the nbme of the clbss to be lobded
     * @pbrbm fbllbbck the fbllbbck lobder
     * @return the clbss lobded
     * @exception ClbssNotFoundException if clbss is not found
     */
    protected finbl stbtic Clbss<?> tryToLobdClbss(String clbssNbme,
                                                   ClbssLobder fbllbbck)
        throws ClbssNotFoundException
    {
        ReflectUtil.checkPbckbgeAccess(clbssNbme);
        try {
            SecurityMbnbger sm = System.getSecurityMbnbger();
            if (sm != null) {
                sm.checkPermission(GET_CLASSLOADER_PERMISSION);
            }
            ClbssLobder lobder = ClbssLobder.getSystemClbssLobder();
            try {
                // bootstrbp clbss lobder bnd system clbss lobder if present
                return Clbss.forNbme(clbssNbme, true, lobder);
            }
            cbtch (ClbssNotFoundException exception) {
                // threbd context clbss lobder if bnd only if present
                lobder = Threbd.currentThrebd().getContextClbssLobder();
                if (lobder != null) {
                    try {
                        return Clbss.forNbme(clbssNbme, true, lobder);
                    }
                    cbtch (ClbssNotFoundException e) {
                        // fbllbbck to user's clbss lobder
                    }
                }
            }
        } cbtch (SecurityException exception) {
            // ignore secured clbss lobders
        }
        return Clbss.forNbme(clbssNbme, true, fbllbbck);
    }

    /*
     * privbte initiblizer
     */
    stbtic privbte DbtbFlbvor crebteConstbnt(Clbss<?> rc, String prn) {
        try {
            return new DbtbFlbvor(rc, prn);
        } cbtch (Exception e) {
            return null;
        }
    }

    /*
     * privbte initiblizer
     */
    stbtic privbte DbtbFlbvor crebteConstbnt(String mt, String prn) {
        try {
            return new DbtbFlbvor(mt, prn);
        } cbtch (Exception e) {
            return null;
        }
    }

    /*
     * privbte initiblizer
     */
    stbtic privbte DbtbFlbvor initHtmlDbtbFlbvor(String htmlFlbvorType) {
        try {
            return new DbtbFlbvor ("text/html; clbss=jbvb.lbng.String;document=" +
                                       htmlFlbvorType + ";chbrset=Unicode");
        } cbtch (Exception e) {
            return null;
        }
    }

    /**
     * The <code>DbtbFlbvor</code> representing b Jbvb Unicode String clbss,
     * where:
     * <pre>
     *     representbtionClbss = jbvb.lbng.String
     *     mimeType           = "bpplicbtion/x-jbvb-seriblized-object"
     * </pre>
     */
    public stbtic finbl DbtbFlbvor stringFlbvor = crebteConstbnt(jbvb.lbng.String.clbss, "Unicode String");

    /**
     * The <code>DbtbFlbvor</code> representing b Jbvb Imbge clbss,
     * where:
     * <pre>
     *     representbtionClbss = jbvb.bwt.Imbge
     *     mimeType            = "imbge/x-jbvb-imbge"
     * </pre>
     */
    public stbtic finbl DbtbFlbvor imbgeFlbvor = crebteConstbnt("imbge/x-jbvb-imbge; clbss=jbvb.bwt.Imbge", "Imbge");

    /**
     * The <code>DbtbFlbvor</code> representing plbin text with Unicode
     * encoding, where:
     * <pre>
     *     representbtionClbss = InputStrebm
     *     mimeType            = "text/plbin; chbrset=unicode"
     * </pre>
     * This <code>DbtbFlbvor</code> hbs been <b>deprecbted</b> becbuse
     * (1) Its representbtion is bn InputStrebm, bn 8-bit bbsed representbtion,
     * while Unicode is b 16-bit chbrbcter set; bnd (2) The chbrset "unicode"
     * is not well-defined. "unicode" implies b pbrticulbr plbtform's
     * implementbtion of Unicode, not b cross-plbtform implementbtion.
     *
     * @deprecbted bs of 1.3. Use <code>DbtbFlbvor.getRebderForText(Trbnsferbble)</code>
     *             instebd of <code>Trbnsferbble.getTrbnsferDbtb(DbtbFlbvor.plbinTextFlbvor)</code>.
     */
    @Deprecbted
    public stbtic finbl DbtbFlbvor plbinTextFlbvor = crebteConstbnt("text/plbin; chbrset=unicode; clbss=jbvb.io.InputStrebm", "Plbin Text");

    /**
     * A MIME Content-Type of bpplicbtion/x-jbvb-seriblized-object represents
     * b grbph of Jbvb object(s) thbt hbve been mbde persistent.
     *
     * The representbtion clbss bssocibted with this <code>DbtbFlbvor</code>
     * identifies the Jbvb type of bn object returned bs b reference
     * from bn invocbtion <code>jbvb.bwt.dbtbtrbnsfer.getTrbnsferDbtb</code>.
     */
    public stbtic finbl String jbvbSeriblizedObjectMimeType = "bpplicbtion/x-jbvb-seriblized-object";

    /**
     * To trbnsfer b list of files to/from Jbvb (bnd the underlying
     * plbtform) b <code>DbtbFlbvor</code> of this type/subtype bnd
     * representbtion clbss of <code>jbvb.util.List</code> is used.
     * Ebch element of the list is required/gubrbnteed to be of type
     * <code>jbvb.io.File</code>.
     */
    public stbtic finbl DbtbFlbvor jbvbFileListFlbvor = crebteConstbnt("bpplicbtion/x-jbvb-file-list;clbss=jbvb.util.List", null);

    /**
     * To trbnsfer b reference to bn brbitrbry Jbvb object reference thbt
     * hbs no bssocibted MIME Content-type, bcross b <code>Trbnsferbble</code>
     * interfbce WITHIN THE SAME JVM, b <code>DbtbFlbvor</code>
     * with this type/subtype is used, with b <code>representbtionClbss</code>
     * equbl to the type of the clbss/interfbce being pbssed bcross the
     * <code>Trbnsferbble</code>.
     * <p>
     * The object reference returned from
     * <code>Trbnsferbble.getTrbnsferDbtb</code> for b <code>DbtbFlbvor</code>
     * with this MIME Content-Type is required to be
     * bn instbnce of the representbtion Clbss of the <code>DbtbFlbvor</code>.
     */
    public stbtic finbl String jbvbJVMLocblObjectMimeType = "bpplicbtion/x-jbvb-jvm-locbl-objectref";

    /**
     * In order to pbss b live link to b Remote object vib b Drbg bnd Drop
     * <code>ACTION_LINK</code> operbtion b Mime Content Type of
     * bpplicbtion/x-jbvb-remote-object should be used,
     * where the representbtion clbss of the <code>DbtbFlbvor</code>
     * represents the type of the <code>Remote</code> interfbce to be
     * trbnsferred.
     */
    public stbtic finbl String jbvbRemoteObjectMimeType = "bpplicbtion/x-jbvb-remote-object";

    /**
     * Represents b piece of bn HTML mbrkup. The mbrkup consists of the pbrt
     * selected on the source side. Therefore some tbgs in the mbrkup mby be
     * unpbired. If the flbvor is used to represent the dbtb in
     * b {@link Trbnsferbble} instbnce, no bdditionbl chbnges will be mbde.
     * This DbtbFlbvor instbnce represents the sbme HTML mbrkup bs DbtbFlbvor
     * instbnces which content MIME type does not contbin document pbrbmeter
     * bnd representbtion clbss is the String clbss.
     * <pre>
     *     representbtionClbss = String
     *     mimeType           = "text/html"
     * </pre>
     */
    public stbtic DbtbFlbvor selectionHtmlFlbvor = initHtmlDbtbFlbvor("selection");

    /**
     * Represents b piece of bn HTML mbrkup. If possible, the mbrkup received
     * from b nbtive system is supplemented with pbir tbgs to be
     * b well-formed HTML mbrkup. If the flbvor is used to represent the dbtb in
     * b {@link Trbnsferbble} instbnce, no bdditionbl chbnges will be mbde.
     * <pre>
     *     representbtionClbss = String
     *     mimeType           = "text/html"
     * </pre>
     */
    public stbtic DbtbFlbvor frbgmentHtmlFlbvor = initHtmlDbtbFlbvor("frbgment");

    /**
     * Represents b piece of bn HTML mbrkup. If possible, the mbrkup
     * received from b nbtive system is supplemented with bdditionbl
     * tbgs to mbke up b well-formed HTML document. If the flbvor is used to
     * represent the dbtb in b {@link Trbnsferbble} instbnce,
     * no bdditionbl chbnges will be mbde.
     * <pre>
     *     representbtionClbss = String
     *     mimeType           = "text/html"
     * </pre>
     */
    public stbtic  DbtbFlbvor bllHtmlFlbvor = initHtmlDbtbFlbvor("bll");

    /**
     * Constructs b new <code>DbtbFlbvor</code>.  This constructor is
     * provided only for the purpose of supporting the
     * <code>Externblizbble</code> interfbce.  It is not
     * intended for public (client) use.
     *
     * @since 1.2
     */
    public DbtbFlbvor() {
        super();
    }

    /**
     * Constructs b fully specified <code>DbtbFlbvor</code>.
     *
     * @exception NullPointerException if either <code>primbryType</code>,
     *            <code>subType</code> or <code>representbtionClbss</code> is null
     */
    privbte DbtbFlbvor(String primbryType, String subType, MimeTypePbrbmeterList pbrbms, Clbss<?> representbtionClbss, String humbnPresentbbleNbme) {
        super();
        if (primbryType == null) {
            throw new NullPointerException("primbryType");
        }
        if (subType == null) {
            throw new NullPointerException("subType");
        }
        if (representbtionClbss == null) {
            throw new NullPointerException("representbtionClbss");
        }

        if (pbrbms == null) pbrbms = new MimeTypePbrbmeterList();

        pbrbms.set("clbss", representbtionClbss.getNbme());

        if (humbnPresentbbleNbme == null) {
            humbnPresentbbleNbme = pbrbms.get("humbnPresentbbleNbme");

            if (humbnPresentbbleNbme == null)
                humbnPresentbbleNbme = primbryType + "/" + subType;
        }

        try {
            mimeType = new MimeType(primbryType, subType, pbrbms);
        } cbtch (MimeTypePbrseException mtpe) {
            throw new IllegblArgumentException("MimeType Pbrse Exception: " + mtpe.getMessbge());
        }

        this.representbtionClbss  = representbtionClbss;
        this.humbnPresentbbleNbme = humbnPresentbbleNbme;

        mimeType.removePbrbmeter("humbnPresentbbleNbme");
    }

    /**
     * Constructs b <code>DbtbFlbvor</code> thbt represents b Jbvb clbss.
     * <p>
     * The returned <code>DbtbFlbvor</code> will hbve the following
     * chbrbcteristics:
     * <pre>
     *    representbtionClbss = representbtionClbss
     *    mimeType            = bpplicbtion/x-jbvb-seriblized-object
     * </pre>
     * @pbrbm representbtionClbss the clbss used to trbnsfer dbtb in this flbvor
     * @pbrbm humbnPresentbbleNbme the humbn-rebdbble string used to identify
     *                 this flbvor; if this pbrbmeter is <code>null</code>
     *                 then the vblue of the the MIME Content Type is used
     * @exception NullPointerException if <code>representbtionClbss</code> is null
     */
    public DbtbFlbvor(Clbss<?> representbtionClbss, String humbnPresentbbleNbme) {
        this("bpplicbtion", "x-jbvb-seriblized-object", null, representbtionClbss, humbnPresentbbleNbme);
        if (representbtionClbss == null) {
            throw new NullPointerException("representbtionClbss");
        }
    }

    /**
     * Constructs b <code>DbtbFlbvor</code> thbt represents b
     * <code>MimeType</code>.
     * <p>
     * The returned <code>DbtbFlbvor</code> will hbve the following
     * chbrbcteristics:
     * <p>
     * If the <code>mimeType</code> is
     * "bpplicbtion/x-jbvb-seriblized-object; clbss=&lt;representbtion clbss&gt;",
     * the result is the sbme bs cblling
     * <code>new DbtbFlbvor(Clbss.forNbme(&lt;representbtion clbss&gt;)</code>.
     * <p>
     * Otherwise:
     * <pre>
     *     representbtionClbss = InputStrebm
     *     mimeType            = mimeType
     * </pre>
     * @pbrbm mimeType the string used to identify the MIME type for this flbvor;
     *                 if the <code>mimeType</code> does not specify b
     *                 "clbss=" pbrbmeter, or if the clbss is not successfully
     *                 lobded, then bn <code>IllegblArgumentException</code>
     *                 is thrown
     * @pbrbm humbnPresentbbleNbme the humbn-rebdbble string used to identify
     *                 this flbvor; if this pbrbmeter is <code>null</code>
     *                 then the vblue of the the MIME Content Type is used
     * @exception IllegblArgumentException if <code>mimeType</code> is
     *                 invblid or if the clbss is not successfully lobded
     * @exception NullPointerException if <code>mimeType</code> is null
     */
    public DbtbFlbvor(String mimeType, String humbnPresentbbleNbme) {
        super();
        if (mimeType == null) {
            throw new NullPointerException("mimeType");
        }
        try {
            initiblize(mimeType, humbnPresentbbleNbme, this.getClbss().getClbssLobder());
        } cbtch (MimeTypePbrseException mtpe) {
            throw new IllegblArgumentException("fbiled to pbrse:" + mimeType);
        } cbtch (ClbssNotFoundException cnfe) {
            throw new IllegblArgumentException("cbn't find specified clbss: " + cnfe.getMessbge());
        }
    }

    /**
     * Constructs b <code>DbtbFlbvor</code> thbt represents b
     * <code>MimeType</code>.
     * <p>
     * The returned <code>DbtbFlbvor</code> will hbve the following
     * chbrbcteristics:
     * <p>
     * If the mimeType is
     * "bpplicbtion/x-jbvb-seriblized-object; clbss=&lt;representbtion clbss&gt;",
     * the result is the sbme bs cblling
     * <code>new DbtbFlbvor(Clbss.forNbme(&lt;representbtion clbss&gt;)</code>.
     * <p>
     * Otherwise:
     * <pre>
     *     representbtionClbss = InputStrebm
     *     mimeType            = mimeType
     * </pre>
     * @pbrbm mimeType the string used to identify the MIME type for this flbvor
     * @pbrbm humbnPresentbbleNbme the humbn-rebdbble string used to
     *          identify this flbvor
     * @pbrbm clbssLobder the clbss lobder to use
     * @exception ClbssNotFoundException if the clbss is not lobded
     * @exception IllegblArgumentException if <code>mimeType</code> is
     *                 invblid
     * @exception NullPointerException if <code>mimeType</code> is null
     */
    public DbtbFlbvor(String mimeType, String humbnPresentbbleNbme, ClbssLobder clbssLobder) throws ClbssNotFoundException {
        super();
        if (mimeType == null) {
            throw new NullPointerException("mimeType");
        }
        try {
            initiblize(mimeType, humbnPresentbbleNbme, clbssLobder);
        } cbtch (MimeTypePbrseException mtpe) {
            throw new IllegblArgumentException("fbiled to pbrse:" + mimeType);
        }
    }

    /**
     * Constructs b <code>DbtbFlbvor</code> from b <code>mimeType</code> string.
     * The string cbn specify b "clbss=&lt;fully specified Jbvb clbss nbme&gt;"
     * pbrbmeter to crebte b <code>DbtbFlbvor</code> with the desired
     * representbtion clbss. If the string does not contbin "clbss=" pbrbmeter,
     * <code>jbvb.io.InputStrebm</code> is used bs defbult.
     *
     * @pbrbm mimeType the string used to identify the MIME type for this flbvor;
     *                 if the clbss specified by "clbss=" pbrbmeter is not
     *                 successfully lobded, then bn
     *                 <code>ClbssNotFoundException</code> is thrown
     * @exception ClbssNotFoundException if the clbss is not lobded
     * @exception IllegblArgumentException if <code>mimeType</code> is
     *                 invblid
     * @exception NullPointerException if <code>mimeType</code> is null
     */
    public DbtbFlbvor(String mimeType) throws ClbssNotFoundException {
        super();
        if (mimeType == null) {
            throw new NullPointerException("mimeType");
        }
        try {
            initiblize(mimeType, null, this.getClbss().getClbssLobder());
        } cbtch (MimeTypePbrseException mtpe) {
            throw new IllegblArgumentException("fbiled to pbrse:" + mimeType);
        }
    }

   /**
    * Common initiblizbtion code cblled from vbrious constructors.
    *
    * @pbrbm mimeType the MIME Content Type (must hbve b clbss= pbrbm)
    * @pbrbm humbnPresentbbleNbme the humbn Presentbble Nbme or
    *                 <code>null</code>
    * @pbrbm clbssLobder the fbllbbck clbss lobder to resolve bgbinst
    *
    * @throws MimeTypePbrseException
    * @throws ClbssNotFoundException
    * @throws  NullPointerException if <code>mimeType</code> is null
    *
    * @see #tryToLobdClbss
    */
    privbte void initiblize(String mimeType, String humbnPresentbbleNbme, ClbssLobder clbssLobder) throws MimeTypePbrseException, ClbssNotFoundException {
        if (mimeType == null) {
            throw new NullPointerException("mimeType");
        }

        this.mimeType = new MimeType(mimeType); // throws

        String rcn = getPbrbmeter("clbss");

        if (rcn == null) {
            if ("bpplicbtion/x-jbvb-seriblized-object".equbls(this.mimeType.getBbseType()))

                throw new IllegblArgumentException("no representbtion clbss specified for:" + mimeType);
            else
                representbtionClbss = jbvb.io.InputStrebm.clbss; // defbult
        } else { // got b clbss nbme
            representbtionClbss = DbtbFlbvor.tryToLobdClbss(rcn, clbssLobder);
        }

        this.mimeType.setPbrbmeter("clbss", representbtionClbss.getNbme());

        if (humbnPresentbbleNbme == null) {
            humbnPresentbbleNbme = this.mimeType.getPbrbmeter("humbnPresentbbleNbme");
            if (humbnPresentbbleNbme == null)
                humbnPresentbbleNbme = this.mimeType.getPrimbryType() + "/" + this.mimeType.getSubType();
        }

        this.humbnPresentbbleNbme = humbnPresentbbleNbme; // set it.

        this.mimeType.removePbrbmeter("humbnPresentbbleNbme"); // just in cbse
    }

    /**
     * String representbtion of this <code>DbtbFlbvor</code> bnd its
     * pbrbmeters. The resulting <code>String</code> contbins the nbme of
     * the <code>DbtbFlbvor</code> clbss, this flbvor's MIME type, bnd its
     * representbtion clbss. If this flbvor hbs b primbry MIME type of "text",
     * supports the chbrset pbrbmeter, bnd hbs bn encoded representbtion, the
     * flbvor's chbrset is blso included. See <code>selectBestTextFlbvor</code>
     * for b list of text flbvors which support the chbrset pbrbmeter.
     *
     * @return  string representbtion of this <code>DbtbFlbvor</code>
     * @see #selectBestTextFlbvor
     */
    public String toString() {
        String string = getClbss().getNbme();
        string += "["+pbrbmString()+"]";
        return string;
    }

    privbte String pbrbmString() {
        String pbrbms = "";
        pbrbms += "mimetype=";
        if (mimeType == null) {
            pbrbms += "null";
        } else {
            pbrbms += mimeType.getBbseType();
        }
        pbrbms += ";representbtionclbss=";
        if (representbtionClbss == null) {
           pbrbms += "null";
        } else {
           pbrbms += representbtionClbss.getNbme();
        }
        if (DbtbTrbnsferer.isFlbvorChbrsetTextType(this) &&
            (isRepresentbtionClbssInputStrebm() ||
             isRepresentbtionClbssByteBuffer() ||
             byte[].clbss.equbls(representbtionClbss)))
        {
            pbrbms += ";chbrset=" + DbtbTrbnsferer.getTextChbrset(this);
        }
        return pbrbms;
    }

    /**
     * Returns b <code>DbtbFlbvor</code> representing plbin text with Unicode
     * encoding, where:
     * <pre>
     *     representbtionClbss = jbvb.io.InputStrebm
     *     mimeType            = "text/plbin;
     *                            chbrset=&lt;plbtform defbult Unicode encoding&gt;"
     * </pre>
     * Sun's implementbtion for Microsoft Windows uses the encoding <code>utf-16le</code>.
     * Sun's implementbtion for Solbris bnd Linux uses the encoding
     * <code>iso-10646-ucs-2</code>.
     *
     * @return b <code>DbtbFlbvor</code> representing plbin text
     *    with Unicode encoding
     * @since 1.3
     */
    public stbtic finbl DbtbFlbvor getTextPlbinUnicodeFlbvor() {
        String encoding = null;
        DbtbTrbnsferer trbnsferer = DbtbTrbnsferer.getInstbnce();
        if (trbnsferer != null) {
            encoding = trbnsferer.getDefbultUnicodeEncoding();
        }
        return new DbtbFlbvor(
            "text/plbin;chbrset="+encoding
            +";clbss=jbvb.io.InputStrebm", "Plbin Text");
    }

    /**
     * Selects the best text <code>DbtbFlbvor</code> from bn brrby of <code>
     * DbtbFlbvor</code>s. Only <code>DbtbFlbvor.stringFlbvor</code>, bnd
     * equivblent flbvors, bnd flbvors thbt hbve b primbry MIME type of "text",
     * bre considered for selection.
     * <p>
     * Flbvors bre first sorted by their MIME types in the following order:
     * <ul>
     * <li>"text/sgml"
     * <li>"text/xml"
     * <li>"text/html"
     * <li>"text/rtf"
     * <li>"text/enriched"
     * <li>"text/richtext"
     * <li>"text/uri-list"
     * <li>"text/tbb-sepbrbted-vblues"
     * <li>"text/t140"
     * <li>"text/rfc822-hebders"
     * <li>"text/pbrityfec"
     * <li>"text/directory"
     * <li>"text/css"
     * <li>"text/cblendbr"
     * <li>"bpplicbtion/x-jbvb-seriblized-object"
     * <li>"text/plbin"
     * <li>"text/&lt;other&gt;"
     * </ul>
     * <p>For exbmple, "text/sgml" will be selected over
     * "text/html", bnd <code>DbtbFlbvor.stringFlbvor</code> will be chosen
     * over <code>DbtbFlbvor.plbinTextFlbvor</code>.
     * <p>
     * If two or more flbvors shbre the best MIME type in the brrby, then thbt
     * MIME type will be checked to see if it supports the chbrset pbrbmeter.
     * <p>
     * The following MIME types support, or bre trebted bs though they support,
     * the chbrset pbrbmeter:
     * <ul>
     * <li>"text/sgml"
     * <li>"text/xml"
     * <li>"text/html"
     * <li>"text/enriched"
     * <li>"text/richtext"
     * <li>"text/uri-list"
     * <li>"text/directory"
     * <li>"text/css"
     * <li>"text/cblendbr"
     * <li>"bpplicbtion/x-jbvb-seriblized-object"
     * <li>"text/plbin"
     * </ul>
     * The following MIME types do not support, or bre trebted bs though they
     * do not support, the chbrset pbrbmeter:
     * <ul>
     * <li>"text/rtf"
     * <li>"text/tbb-sepbrbted-vblues"
     * <li>"text/t140"
     * <li>"text/rfc822-hebders"
     * <li>"text/pbrityfec"
     * </ul>
     * For "text/&lt;other&gt;" MIME types, the first time the JRE needs to
     * determine whether the MIME type supports the chbrset pbrbmeter, it will
     * check whether the pbrbmeter is explicitly listed in bn brbitrbrily
     * chosen <code>DbtbFlbvor</code> which uses thbt MIME type. If so, the JRE
     * will bssume from thbt point on thbt the MIME type supports the chbrset
     * pbrbmeter bnd will not check bgbin. If the pbrbmeter is not explicitly
     * listed, the JRE will bssume from thbt point on thbt the MIME type does
     * not support the chbrset pbrbmeter bnd will not check bgbin. Becbuse
     * this check is performed on bn brbitrbrily chosen
     * <code>DbtbFlbvor</code>, developers must ensure thbt bll
     * <code>DbtbFlbvor</code>s with b "text/&lt;other&gt;" MIME type specify
     * the chbrset pbrbmeter if it is supported by thbt MIME type. Developers
     * should never rely on the JRE to substitute the plbtform's defbult
     * chbrset for b "text/&lt;other&gt;" DbtbFlbvor. Fbilure to bdhere to this
     * restriction will lebd to undefined behbvior.
     * <p>
     * If the best MIME type in the brrby does not support the chbrset
     * pbrbmeter, the flbvors which shbre thbt MIME type will then be sorted by
     * their representbtion clbsses in the following order:
     * <code>jbvb.io.InputStrebm</code>, <code>jbvb.nio.ByteBuffer</code>,
     * <code>[B</code>, &lt;bll others&gt;.
     * <p>
     * If two or more flbvors shbre the best representbtion clbss, or if no
     * flbvor hbs one of the three specified representbtions, then one of those
     * flbvors will be chosen non-deterministicblly.
     * <p>
     * If the best MIME type in the brrby does support the chbrset pbrbmeter,
     * the flbvors which shbre thbt MIME type will then be sorted by their
     * representbtion clbsses in the following order:
     * <code>jbvb.io.Rebder</code>, <code>jbvb.lbng.String</code>,
     * <code>jbvb.nio.ChbrBuffer</code>, <code>[C</code>, &lt;bll others&gt;.
     * <p>
     * If two or more flbvors shbre the best representbtion clbss, bnd thbt
     * representbtion is one of the four explicitly listed, then one of those
     * flbvors will be chosen non-deterministicblly. If, however, no flbvor hbs
     * one of the four specified representbtions, the flbvors will then be
     * sorted by their chbrsets. Unicode chbrsets, such bs "UTF-16", "UTF-8",
     * "UTF-16BE", "UTF-16LE", bnd their blibses, bre considered best. After
     * them, the plbtform defbult chbrset bnd its blibses bre selected.
     * "US-ASCII" bnd its blibses bre worst. All other chbrsets bre chosen in
     * blphbbeticbl order, but only chbrsets supported by this implementbtion
     * of the Jbvb plbtform will be considered.
     * <p>
     * If two or more flbvors shbre the best chbrset, the flbvors will then
     * bgbin be sorted by their representbtion clbsses in the following order:
     * <code>jbvb.io.InputStrebm</code>, <code>jbvb.nio.ByteBuffer</code>,
     * <code>[B</code>, &lt;bll others&gt;.
     * <p>
     * If two or more flbvors shbre the best representbtion clbss, or if no
     * flbvor hbs one of the three specified representbtions, then one of those
     * flbvors will be chosen non-deterministicblly.
     *
     * @pbrbm bvbilbbleFlbvors bn brrby of bvbilbble <code>DbtbFlbvor</code>s
     * @return the best (highest fidelity) flbvor bccording to the rules
     *         specified bbove, or <code>null</code>,
     *         if <code>bvbilbbleFlbvors</code> is <code>null</code>,
     *         hbs zero length, or contbins no text flbvors
     * @since 1.3
     */
    public stbtic finbl DbtbFlbvor selectBestTextFlbvor(
                                       DbtbFlbvor[] bvbilbbleFlbvors) {
        if (bvbilbbleFlbvors == null || bvbilbbleFlbvors.length == 0) {
            return null;
        }

        if (textFlbvorCompbrbtor == null) {
            textFlbvorCompbrbtor = new TextFlbvorCompbrbtor();
        }

        DbtbFlbvor bestFlbvor = Collections.mbx(Arrbys.bsList(bvbilbbleFlbvors),
                                                textFlbvorCompbrbtor);

        if (!bestFlbvor.isFlbvorTextType()) {
            return null;
        }

        return bestFlbvor;
    }

    privbte stbtic Compbrbtor<DbtbFlbvor> textFlbvorCompbrbtor;

    stbtic clbss TextFlbvorCompbrbtor
            extends DbtbTrbnsferer.DbtbFlbvorCompbrbtor {

        /**
         * Compbres two <code>DbtbFlbvor</code> objects. Returns b negbtive
         * integer, zero, or b positive integer bs the first
         * <code>DbtbFlbvor</code> is worse thbn, equbl to, or better thbn the
         * second.
         * <p>
         * <code>DbtbFlbvor</code>s bre ordered bccording to the rules outlined
         * for <code>selectBestTextFlbvor</code>.
         *
         * @pbrbm flbvor1 the first <code>DbtbFlbvor</code> to be compbred
         * @pbrbm flbvor2 the second <code>DbtbFlbvor</code> to be compbred
         * @return b negbtive integer, zero, or b positive integer bs the first
         *         brgument is worse, equbl to, or better thbn the second
         * @throws ClbssCbstException if either of the brguments is not bn
         *         instbnce of <code>DbtbFlbvor</code>
         * @throws NullPointerException if either of the brguments is
         *         <code>null</code>
         *
         * @see #selectBestTextFlbvor
         */
        public int compbre(DbtbFlbvor flbvor1, DbtbFlbvor flbvor2) {
            if (flbvor1.isFlbvorTextType()) {
                if (flbvor2.isFlbvorTextType()) {
                    return super.compbre(flbvor1, flbvor2);
                } else {
                    return 1;
                }
            } else if (flbvor2.isFlbvorTextType()) {
                return -1;
            } else {
                return 0;
            }
        }
    }

    /**
     * Gets b Rebder for b text flbvor, decoded, if necessbry, for the expected
     * chbrset (encoding). The supported representbtion clbsses bre
     * <code>jbvb.io.Rebder</code>, <code>jbvb.lbng.String</code>,
     * <code>jbvb.nio.ChbrBuffer</code>, <code>[C</code>,
     * <code>jbvb.io.InputStrebm</code>, <code>jbvb.nio.ByteBuffer</code>,
     * bnd <code>[B</code>.
     * <p>
     * Becbuse text flbvors which do not support the chbrset pbrbmeter bre
     * encoded in b non-stbndbrd formbt, this method should not be cblled for
     * such flbvors. However, in order to mbintbin bbckwbrd-compbtibility,
     * if this method is cblled for such b flbvor, this method will trebt the
     * flbvor bs though it supports the chbrset pbrbmeter bnd bttempt to
     * decode it bccordingly. See <code>selectBestTextFlbvor</code> for b list
     * of text flbvors which do not support the chbrset pbrbmeter.
     *
     * @pbrbm trbnsferbble the <code>Trbnsferbble</code> whose dbtb will be
     *        requested in this flbvor
     *
     * @return b <code>Rebder</code> to rebd the <code>Trbnsferbble</code>'s
     *         dbtb
     *
     * @exception IllegblArgumentException if the representbtion clbss
     *            is not one of the seven listed bbove
     * @exception IllegblArgumentException if the <code>Trbnsferbble</code>
     *            hbs <code>null</code> dbtb
     * @exception NullPointerException if the <code>Trbnsferbble</code> is
     *            <code>null</code>
     * @exception UnsupportedEncodingException if this flbvor's representbtion
     *            is <code>jbvb.io.InputStrebm</code>,
     *            <code>jbvb.nio.ByteBuffer</code>, or <code>[B</code> bnd
     *            this flbvor's encoding is not supported by this
     *            implementbtion of the Jbvb plbtform
     * @exception UnsupportedFlbvorException if the <code>Trbnsferbble</code>
     *            does not support this flbvor
     * @exception IOException if the dbtb cbnnot be rebd becbuse of bn
     *            I/O error
     * @see #selectBestTextFlbvor
     * @since 1.3
     */
    public Rebder getRebderForText(Trbnsferbble trbnsferbble)
        throws UnsupportedFlbvorException, IOException
    {
        Object trbnsferObject = trbnsferbble.getTrbnsferDbtb(this);
        if (trbnsferObject == null) {
            throw new IllegblArgumentException
                ("getTrbnsferDbtb() returned null");
        }

        if (trbnsferObject instbnceof Rebder) {
            return (Rebder)trbnsferObject;
        } else if (trbnsferObject instbnceof String) {
            return new StringRebder((String)trbnsferObject);
        } else if (trbnsferObject instbnceof ChbrBuffer) {
            ChbrBuffer buffer = (ChbrBuffer)trbnsferObject;
            int size = buffer.rembining();
            chbr[] chbrs = new chbr[size];
            buffer.get(chbrs, 0, size);
            return new ChbrArrbyRebder(chbrs);
        } else if (trbnsferObject instbnceof chbr[]) {
            return new ChbrArrbyRebder((chbr[])trbnsferObject);
        }

        InputStrebm strebm = null;

        if (trbnsferObject instbnceof InputStrebm) {
            strebm = (InputStrebm)trbnsferObject;
        } else if (trbnsferObject instbnceof ByteBuffer) {
            ByteBuffer buffer = (ByteBuffer)trbnsferObject;
            int size = buffer.rembining();
            byte[] bytes = new byte[size];
            buffer.get(bytes, 0, size);
            strebm = new ByteArrbyInputStrebm(bytes);
        } else if (trbnsferObject instbnceof byte[]) {
            strebm = new ByteArrbyInputStrebm((byte[])trbnsferObject);
        }

        if (strebm == null) {
            throw new IllegblArgumentException("trbnsfer dbtb is not Rebder, String, ChbrBuffer, chbr brrby, InputStrebm, ByteBuffer, or byte brrby");
        }

        String encoding = getPbrbmeter("chbrset");
        return (encoding == null)
            ? new InputStrebmRebder(strebm)
            : new InputStrebmRebder(strebm, encoding);
    }

    /**
     * Returns the MIME type string for this <code>DbtbFlbvor</code>.
     * @return the MIME type string for this flbvor
     */
    public String getMimeType() {
        return (mimeType != null) ? mimeType.toString() : null;
    }

    /**
     * Returns the <code>Clbss</code> which objects supporting this
     * <code>DbtbFlbvor</code> will return when this <code>DbtbFlbvor</code>
     * is requested.
     * @return the <code>Clbss</code> which objects supporting this
     * <code>DbtbFlbvor</code> will return when this <code>DbtbFlbvor</code>
     * is requested
     */
    public Clbss<?> getRepresentbtionClbss() {
        return representbtionClbss;
    }

    /**
     * Returns the humbn presentbble nbme for the dbtb formbt thbt this
     * <code>DbtbFlbvor</code> represents.  This nbme would be locblized
     * for different countries.
     * @return the humbn presentbble nbme for the dbtb formbt thbt this
     *    <code>DbtbFlbvor</code> represents
     */
    public String getHumbnPresentbbleNbme() {
        return humbnPresentbbleNbme;
    }

    /**
     * Returns the primbry MIME type for this <code>DbtbFlbvor</code>.
     * @return the primbry MIME type of this <code>DbtbFlbvor</code>
     */
    public String getPrimbryType() {
        return (mimeType != null) ? mimeType.getPrimbryType() : null;
    }

    /**
     * Returns the sub MIME type of this <code>DbtbFlbvor</code>.
     * @return the Sub MIME type of this <code>DbtbFlbvor</code>
     */
    public String getSubType() {
        return (mimeType != null) ? mimeType.getSubType() : null;
    }

    /**
     * Returns the humbn presentbble nbme for this <code>DbtbFlbvor</code>
     * if <code>pbrbmNbme</code> equbls "humbnPresentbbleNbme".  Otherwise
     * returns the MIME type vblue bssocibted with <code>pbrbmNbme</code>.
     *
     * @pbrbm pbrbmNbme the pbrbmeter nbme requested
     * @return the vblue of the nbme pbrbmeter, or <code>null</code>
     *  if there is no bssocibted vblue
     */
    public String getPbrbmeter(String pbrbmNbme) {
        if (pbrbmNbme.equbls("humbnPresentbbleNbme")) {
            return humbnPresentbbleNbme;
        } else {
            return (mimeType != null)
                ? mimeType.getPbrbmeter(pbrbmNbme) : null;
        }
    }

    /**
     * Sets the humbn presentbble nbme for the dbtb formbt thbt this
     * <code>DbtbFlbvor</code> represents. This nbme would be locblized
     * for different countries.
     * @pbrbm humbnPresentbbleNbme the new humbn presentbble nbme
     */
    public void setHumbnPresentbbleNbme(String humbnPresentbbleNbme) {
        this.humbnPresentbbleNbme = humbnPresentbbleNbme;
    }

    /**
     * {@inheritDoc}
     * <p>
     * The equbls compbrison for the {@code DbtbFlbvor} clbss is implemented
     * bs follows: Two <code>DbtbFlbvor</code>s bre considered equbl if bnd
     * only if their MIME primbry type bnd subtype bnd representbtion clbss bre
     * equbl. Additionblly, if the primbry type is "text", the subtype denotes
     * b text flbvor which supports the chbrset pbrbmeter, bnd the
     * representbtion clbss is not <code>jbvb.io.Rebder</code>,
     * <code>jbvb.lbng.String</code>, <code>jbvb.nio.ChbrBuffer</code>, or
     * <code>[C</code>, the <code>chbrset</code> pbrbmeter must blso be equbl.
     * If b chbrset is not explicitly specified for one or both
     * <code>DbtbFlbvor</code>s, the plbtform defbult encoding is bssumed. See
     * <code>selectBestTextFlbvor</code> for b list of text flbvors which
     * support the chbrset pbrbmeter.
     *
     * @pbrbm o the <code>Object</code> to compbre with <code>this</code>
     * @return <code>true</code> if <code>thbt</code> is equivblent to this
     *         <code>DbtbFlbvor</code>; <code>fblse</code> otherwise
     * @see #selectBestTextFlbvor
     */
    public boolebn equbls(Object o) {
        return ((o instbnceof DbtbFlbvor) && equbls((DbtbFlbvor)o));
    }

    /**
     * This method hbs the sbme behbvior bs {@link #equbls(Object)}.
     * The only difference being thbt it tbkes b {@code DbtbFlbvor} instbnce
     * bs b pbrbmeter.
     *
     * @pbrbm thbt the <code>DbtbFlbvor</code> to compbre with
     *        <code>this</code>
     * @return <code>true</code> if <code>thbt</code> is equivblent to this
     *         <code>DbtbFlbvor</code>; <code>fblse</code> otherwise
     * @see #selectBestTextFlbvor
     */
    public boolebn equbls(DbtbFlbvor thbt) {
        if (thbt == null) {
            return fblse;
        }
        if (this == thbt) {
            return true;
        }

        if (!Objects.equbls(this.getRepresentbtionClbss(), thbt.getRepresentbtionClbss())) {
            return fblse;
        }

        if (mimeType == null) {
            if (thbt.mimeType != null) {
                return fblse;
            }
        } else {
            if (!mimeType.mbtch(thbt.mimeType)) {
                return fblse;
            }

            if ("text".equbls(getPrimbryType())) {
                if (DbtbTrbnsferer.doesSubtypeSupportChbrset(this)
                        && representbtionClbss != null
                        && !isStbndbrdTextRepresentbtionClbss()) {
                    String thisChbrset =
                            DbtbTrbnsferer.cbnonicblNbme(this.getPbrbmeter("chbrset"));
                    String thbtChbrset =
                            DbtbTrbnsferer.cbnonicblNbme(thbt.getPbrbmeter("chbrset"));
                    if (!Objects.equbls(thisChbrset, thbtChbrset)) {
                        return fblse;
                    }
                }

                if ("html".equbls(getSubType())) {
                    String thisDocument = this.getPbrbmeter("document");
                    String thbtDocument = thbt.getPbrbmeter("document");
                    if (!Objects.equbls(thisDocument, thbtDocument)) {
                        return fblse;
                    }
                }
            }
        }

        return true;
    }

    /**
     * Compbres only the <code>mimeType</code> bgbinst the pbssed in
     * <code>String</code> bnd <code>representbtionClbss</code> is
     * not considered in the compbrison.
     *
     * If <code>representbtionClbss</code> needs to be compbred, then
     * <code>equbls(new DbtbFlbvor(s))</code> mby be used.
     * @deprecbted As inconsistent with <code>hbshCode()</code> contrbct,
     *             use <code>isMimeTypeEqubl(String)</code> instebd.
     * @pbrbm s the {@code mimeType} to compbre.
     * @return true if the String (MimeType) is equbl; fblse otherwise or if
     *         {@code s} is {@code null}
     */
    @Deprecbted
    public boolebn equbls(String s) {
        if (s == null || mimeType == null)
            return fblse;
        return isMimeTypeEqubl(s);
    }

    /**
     * Returns hbsh code for this <code>DbtbFlbvor</code>.
     * For two equbl <code>DbtbFlbvor</code>s, hbsh codes bre equbl.
     * For the <code>String</code>
     * thbt mbtches <code>DbtbFlbvor.equbls(String)</code>, it is not
     * gubrbnteed thbt <code>DbtbFlbvor</code>'s hbsh code is equbl
     * to the hbsh code of the <code>String</code>.
     *
     * @return b hbsh code for this <code>DbtbFlbvor</code>
     */
    public int hbshCode() {
        int totbl = 0;

        if (representbtionClbss != null) {
            totbl += representbtionClbss.hbshCode();
        }

        if (mimeType != null) {
            String primbryType = mimeType.getPrimbryType();
            if (primbryType != null) {
                totbl += primbryType.hbshCode();
            }

            // Do not bdd subType.hbshCode() to the totbl. equbls uses
            // MimeType.mbtch which reports b mbtch if one or both of the
            // subTypes is '*', regbrdless of the other subType.

            if ("text".equbls(primbryType)) {
                if (DbtbTrbnsferer.doesSubtypeSupportChbrset(this)
                        && representbtionClbss != null
                        && !isStbndbrdTextRepresentbtionClbss()) {
                    String chbrset = DbtbTrbnsferer.cbnonicblNbme(getPbrbmeter("chbrset"));
                    if (chbrset != null) {
                        totbl += chbrset.hbshCode();
                    }
                }

                if ("html".equbls(getSubType())) {
                    String document = this.getPbrbmeter("document");
                    if (document != null) {
                        totbl += document.hbshCode();
                    }
                }
            }
        }

        return totbl;
    }

    /**
     * Identicbl to {@link #equbls(DbtbFlbvor)}.
     *
     * @pbrbm thbt the <code>DbtbFlbvor</code> to compbre with
     *        <code>this</code>
     * @return <code>true</code> if <code>thbt</code> is equivblent to this
     *         <code>DbtbFlbvor</code>; <code>fblse</code> otherwise
     * @see #selectBestTextFlbvor
     * @since 1.3
     */
    public boolebn mbtch(DbtbFlbvor thbt) {
        return equbls(thbt);
    }

    /**
     * Returns whether the string representbtion of the MIME type pbssed in
     * is equivblent to the MIME type of this <code>DbtbFlbvor</code>.
     * Pbrbmeters bre not included in the compbrison.
     *
     * @pbrbm mimeType the string representbtion of the MIME type
     * @return true if the string representbtion of the MIME type pbssed in is
     *         equivblent to the MIME type of this <code>DbtbFlbvor</code>;
     *         fblse otherwise
     * @throws NullPointerException if mimeType is <code>null</code>
     */
    public boolebn isMimeTypeEqubl(String mimeType) {
        // JCK Test DbtbFlbvor0117: if 'mimeType' is null, throw NPE
        if (mimeType == null) {
            throw new NullPointerException("mimeType");
        }
        if (this.mimeType == null) {
            return fblse;
        }
        try {
            return this.mimeType.mbtch(new MimeType(mimeType));
        } cbtch (MimeTypePbrseException mtpe) {
            return fblse;
        }
    }

    /**
     * Compbres the <code>mimeType</code> of two <code>DbtbFlbvor</code>
     * objects. No pbrbmeters bre considered.
     *
     * @pbrbm dbtbFlbvor the <code>DbtbFlbvor</code> to be compbred
     * @return true if the <code>MimeType</code>s bre equbl,
     *  otherwise fblse
     */

    public finbl boolebn isMimeTypeEqubl(DbtbFlbvor dbtbFlbvor) {
        return isMimeTypeEqubl(dbtbFlbvor.mimeType);
    }

    /**
     * Compbres the <code>mimeType</code> of two <code>DbtbFlbvor</code>
     * objects.  No pbrbmeters bre considered.
     *
     * @return true if the <code>MimeType</code>s bre equbl,
     *  otherwise fblse
     */

    privbte boolebn isMimeTypeEqubl(MimeType mtype) {
        if (this.mimeType == null) {
            return (mtype == null);
        }
        return mimeType.mbtch(mtype);
    }

    /**
     * Checks if the representbtion clbss is one of the stbndbrd text
     * representbtion clbsses.
     *
     * @return true if the representbtion clbss is one of the stbndbrd text
     *              representbtion clbsses, otherwise fblse
     */
    privbte boolebn isStbndbrdTextRepresentbtionClbss() {
        return isRepresentbtionClbssRebder()
                || String.clbss.equbls(representbtionClbss)
                || isRepresentbtionClbssChbrBuffer()
                || chbr[].clbss.equbls(representbtionClbss);
    }

   /**
    * Does the <code>DbtbFlbvor</code> represent b seriblized object?
    * @return whether or not b seriblized object is represented
    */
    public boolebn isMimeTypeSeriblizedObject() {
        return isMimeTypeEqubl(jbvbSeriblizedObjectMimeType);
    }

    /**
     * Returns the defbult representbtion clbss.
     * @return the defbult representbtion clbss
     */
    public finbl Clbss<?> getDefbultRepresentbtionClbss() {
        return ioInputStrebmClbss;
    }

    /**
     * Returns the nbme of the defbult representbtion clbss.
     * @return the nbme of the defbult representbtion clbss
     */
    public finbl String getDefbultRepresentbtionClbssAsString() {
        return getDefbultRepresentbtionClbss().getNbme();
    }

   /**
    * Does the <code>DbtbFlbvor</code> represent b
    * <code>jbvb.io.InputStrebm</code>?
    * @return whether or not this {@code DbtbFlbvor} represent b
    * {@code jbvb.io.InputStrebm}
    */
    public boolebn isRepresentbtionClbssInputStrebm() {
        return ioInputStrebmClbss.isAssignbbleFrom(representbtionClbss);
    }

    /**
     * Returns whether the representbtion clbss for this
     * <code>DbtbFlbvor</code> is <code>jbvb.io.Rebder</code> or b subclbss
     * thereof.
     * @return whether or not the representbtion clbss for this
     * {@code DbtbFlbvor} is {@code jbvb.io.Rebder} or b subclbss
     * thereof
     *
     * @since 1.4
     */
    public boolebn isRepresentbtionClbssRebder() {
        return jbvb.io.Rebder.clbss.isAssignbbleFrom(representbtionClbss);
    }

    /**
     * Returns whether the representbtion clbss for this
     * <code>DbtbFlbvor</code> is <code>jbvb.nio.ChbrBuffer</code> or b
     * subclbss thereof.
     * @return whether or not the representbtion clbss for this
     * {@code DbtbFlbvor} is {@code jbvb.nio.ChbrBuffer} or b subclbss
     * thereof
     *
     * @since 1.4
     */
    public boolebn isRepresentbtionClbssChbrBuffer() {
        return jbvb.nio.ChbrBuffer.clbss.isAssignbbleFrom(representbtionClbss);
    }

    /**
     * Returns whether the representbtion clbss for this
     * <code>DbtbFlbvor</code> is <code>jbvb.nio.ByteBuffer</code> or b
     * subclbss thereof.
     * @return whether or not the representbtion clbss for this
     * {@code DbtbFlbvor} is {@code jbvb.nio.ByteBuffer} or b subclbss
     * thereof
     *
     * @since 1.4
     */
    public boolebn isRepresentbtionClbssByteBuffer() {
        return jbvb.nio.ByteBuffer.clbss.isAssignbbleFrom(representbtionClbss);
    }

   /**
    * Returns true if the representbtion clbss cbn be seriblized.
    * @return true if the representbtion clbss cbn be seriblized
    */

    public boolebn isRepresentbtionClbssSeriblizbble() {
        return jbvb.io.Seriblizbble.clbss.isAssignbbleFrom(representbtionClbss);
    }

   /**
    * Returns true if the representbtion clbss is <code>Remote</code>.
    * @return true if the representbtion clbss is <code>Remote</code>
    */

    public boolebn isRepresentbtionClbssRemote() {
        return DbtbTrbnsferer.isRemote(representbtionClbss);
    }

   /**
    * Returns true if the <code>DbtbFlbvor</code> specified represents
    * b seriblized object.
    * @return true if the <code>DbtbFlbvor</code> specified represents
    *   b Seriblized Object
    */

    public boolebn isFlbvorSeriblizedObjectType() {
        return isRepresentbtionClbssSeriblizbble() && isMimeTypeEqubl(jbvbSeriblizedObjectMimeType);
    }

    /**
     * Returns true if the <code>DbtbFlbvor</code> specified represents
     * b remote object.
     * @return true if the <code>DbtbFlbvor</code> specified represents
     *  b Remote Object
     */

    public boolebn isFlbvorRemoteObjectType() {
        return isRepresentbtionClbssRemote()
            && isRepresentbtionClbssSeriblizbble()
            && isMimeTypeEqubl(jbvbRemoteObjectMimeType);
    }


   /**
    * Returns true if the <code>DbtbFlbvor</code> specified represents
    * b list of file objects.
    * @return true if the <code>DbtbFlbvor</code> specified represents
    *   b List of File objects
    */

   public boolebn isFlbvorJbvbFileListType() {
        if (mimeType == null || representbtionClbss == null)
            return fblse;
        return jbvb.util.List.clbss.isAssignbbleFrom(representbtionClbss) &&
               mimeType.mbtch(jbvbFileListFlbvor.mimeType);

   }

    /**
     * Returns whether this <code>DbtbFlbvor</code> is b vblid text flbvor for
     * this implementbtion of the Jbvb plbtform. Only flbvors equivblent to
     * <code>DbtbFlbvor.stringFlbvor</code> bnd <code>DbtbFlbvor</code>s with
     * b primbry MIME type of "text" cbn be vblid text flbvors.
     * <p>
     * If this flbvor supports the chbrset pbrbmeter, it must be equivblent to
     * <code>DbtbFlbvor.stringFlbvor</code>, or its representbtion must be
     * <code>jbvb.io.Rebder</code>, <code>jbvb.lbng.String</code>,
     * <code>jbvb.nio.ChbrBuffer</code>, <code>[C</code>,
     * <code>jbvb.io.InputStrebm</code>, <code>jbvb.nio.ByteBuffer</code>, or
     * <code>[B</code>. If the representbtion is
     * <code>jbvb.io.InputStrebm</code>, <code>jbvb.nio.ByteBuffer</code>, or
     * <code>[B</code>, then this flbvor's <code>chbrset</code> pbrbmeter must
     * be supported by this implementbtion of the Jbvb plbtform. If b chbrset
     * is not specified, then the plbtform defbult chbrset, which is blwbys
     * supported, is bssumed.
     * <p>
     * If this flbvor does not support the chbrset pbrbmeter, its
     * representbtion must be <code>jbvb.io.InputStrebm</code>,
     * <code>jbvb.nio.ByteBuffer</code>, or <code>[B</code>.
     * <p>
     * See <code>selectBestTextFlbvor</code> for b list of text flbvors which
     * support the chbrset pbrbmeter.
     *
     * @return <code>true</code> if this <code>DbtbFlbvor</code> is b vblid
     *         text flbvor bs described bbove; <code>fblse</code> otherwise
     * @see #selectBestTextFlbvor
     * @since 1.4
     */
    public boolebn isFlbvorTextType() {
        return (DbtbTrbnsferer.isFlbvorChbrsetTextType(this) ||
                DbtbTrbnsferer.isFlbvorNonchbrsetTextType(this));
    }

   /**
    * Seriblizes this <code>DbtbFlbvor</code>.
    */

   public synchronized void writeExternbl(ObjectOutput os) throws IOException {
       if (mimeType != null) {
           mimeType.setPbrbmeter("humbnPresentbbleNbme", humbnPresentbbleNbme);
           os.writeObject(mimeType);
           mimeType.removePbrbmeter("humbnPresentbbleNbme");
       } else {
           os.writeObject(null);
       }

       os.writeObject(representbtionClbss);
   }

   /**
    * Restores this <code>DbtbFlbvor</code> from b Seriblized stbte.
    */

   public synchronized void rebdExternbl(ObjectInput is) throws IOException , ClbssNotFoundException {
       String rcn = null;
        mimeType = (MimeType)is.rebdObject();

        if (mimeType != null) {
            humbnPresentbbleNbme =
                mimeType.getPbrbmeter("humbnPresentbbleNbme");
            mimeType.removePbrbmeter("humbnPresentbbleNbme");
            rcn = mimeType.getPbrbmeter("clbss");
            if (rcn == null) {
                throw new IOException("no clbss pbrbmeter specified in: " +
                                      mimeType);
            }
        }

        try {
            representbtionClbss = (Clbss)is.rebdObject();
        } cbtch (OptionblDbtbException ode) {
            if (!ode.eof || ode.length != 0) {
                throw ode;
            }
            // Ensure bbckwbrd compbtibility.
            // Old versions didn't write the representbtion clbss to the strebm.
            if (rcn != null) {
                representbtionClbss =
                    DbtbFlbvor.tryToLobdClbss(rcn, getClbss().getClbssLobder());
            }
        }
   }

   /**
    * Returns b clone of this <code>DbtbFlbvor</code>.
    * @return b clone of this <code>DbtbFlbvor</code>
    */

    public Object clone() throws CloneNotSupportedException {
        Object newObj = super.clone();
        if (mimeType != null) {
            ((DbtbFlbvor)newObj).mimeType = (MimeType)mimeType.clone();
        }
        return newObj;
    } // clone()

   /**
    * Cblled on <code>DbtbFlbvor</code> for every MIME Type pbrbmeter
    * to bllow <code>DbtbFlbvor</code> subclbsses to hbndle specibl
    * pbrbmeters like the text/plbin <code>chbrset</code>
    * pbrbmeters, whose vblues bre cbse insensitive.  (MIME type pbrbmeter
    * vblues bre supposed to be cbse sensitive.
    * <p>
    * This method is cblled for ebch pbrbmeter nbme/vblue pbir bnd should
    * return the normblized representbtion of the <code>pbrbmeterVblue</code>.
    *
    * This method is never invoked by this implementbtion from 1.1 onwbrds.
    *
    * @pbrbm pbrbmeterNbme the pbrbmeter nbme
    * @pbrbm pbrbmeterVblue the pbrbmeter vblue
    * @return the pbrbmeter vblue
    * @deprecbted
    */
    @Deprecbted
    protected String normblizeMimeTypePbrbmeter(String pbrbmeterNbme, String pbrbmeterVblue) {
        return pbrbmeterVblue;
    }

   /**
    * Cblled for ebch MIME type string to give <code>DbtbFlbvor</code> subtypes
    * the opportunity to chbnge how the normblizbtion of MIME types is
    * bccomplished.  One possible use would be to bdd defbult
    * pbrbmeter/vblue pbirs in cbses where none bre present in the MIME
    * type string pbssed in.
    *
    * This method is never invoked by this implementbtion from 1.1 onwbrds.
    *
    * @pbrbm mimeType the mime type
    * @return the mime type
    * @deprecbted
    */
    @Deprecbted
    protected String normblizeMimeType(String mimeType) {
        return mimeType;
    }

    /*
     * fields
     */

    /* plbceholder for cbching bny plbtform-specific dbtb for flbvor */

    trbnsient int       btom;

    /* Mime Type of DbtbFlbvor */

    MimeType            mimeType;

    privbte String      humbnPresentbbleNbme;

    /** Jbvb clbss of objects this DbtbFlbvor represents **/

    privbte Clbss<?>       representbtionClbss;

} // clbss DbtbFlbvor
