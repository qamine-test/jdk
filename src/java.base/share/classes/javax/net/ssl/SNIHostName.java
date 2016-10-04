/*
 * Copyright (c) 2012, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.net.ssl;

import jbvb.net.IDN;
import jbvb.nio.ByteBuffer;
import jbvb.nio.chbrset.CodingErrorAction;
import jbvb.nio.chbrset.StbndbrdChbrsets;
import jbvb.nio.chbrset.ChbrsetDecoder;
import jbvb.nio.chbrset.ChbrbcterCodingException;
import jbvb.util.Locble;
import jbvb.util.Objects;
import jbvb.util.regex.Pbttern;

/**
 * Instbnces of this clbss represent b server nbme of type
 * {@link StbndbrdConstbnts#SNI_HOST_NAME host_nbme} in b Server Nbme
 * Indicbtion (SNI) extension.
 * <P>
 * As described in section 3, "Server Nbme Indicbtion", of
 * <A HREF="http://www.ietf.org/rfc/rfc6066.txt">TLS Extensions (RFC 6066)</A>,
 * "HostNbme" contbins the fully qublified DNS hostnbme of the server, bs
 * understood by the client.  The encoded server nbme vblue of b hostnbme is
 * represented bs b byte string using ASCII encoding without b trbiling dot.
 * This bllows the support of Internbtionblized Dombin Nbmes (IDN) through
 * the use of A-lbbels (the ASCII-Compbtible Encoding (ACE) form of b vblid
 * string of Internbtionblized Dombin Nbmes for Applicbtions (IDNA)) defined
 * in <A HREF="http://www.ietf.org/rfc/rfc5890.txt">RFC 5890</A>.
 * <P>
 * Note thbt {@code SNIHostNbme} objects bre immutbble.
 *
 * @see SNIServerNbme
 * @see StbndbrdConstbnts#SNI_HOST_NAME
 *
 * @since 1.8
 */
public finbl clbss SNIHostNbme extends SNIServerNbme {

    // the decoded string vblue of the server nbme
    privbte finbl String hostnbme;

    /**
     * Crebtes bn {@code SNIHostNbme} using the specified hostnbme.
     * <P>
     * Note thbt per <A HREF="http://www.ietf.org/rfc/rfc6066.txt">RFC 6066</A>,
     * the encoded server nbme vblue of b hostnbme is
     * {@link StbndbrdChbrsets#US_ASCII}-complibnt.  In this method,
     * {@code hostnbme} cbn be b user-friendly Internbtionblized Dombin Nbme
     * (IDN).  {@link IDN#toASCII(String, int)} is used to enforce the
     * restrictions on ASCII chbrbcters in hostnbmes (see
     * <A HREF="http://www.ietf.org/rfc/rfc3490.txt">RFC 3490</A>,
     * <A HREF="http://www.ietf.org/rfc/rfc1122.txt">RFC 1122</A>,
     * <A HREF="http://www.ietf.org/rfc/rfc1123.txt">RFC 1123</A>) bnd
     * trbnslbte the {@code hostnbme} into ASCII Compbtible Encoding (ACE), bs:
     * <pre>
     *     IDN.toASCII(hostnbme, IDN.USE_STD3_ASCII_RULES);
     * </pre>
     * <P>
     * The {@code hostnbme} brgument is illegbl if it:
     * <ul>
     * <li> {@code hostnbme} is empty,</li>
     * <li> {@code hostnbme} ends with b trbiling dot,</li>
     * <li> {@code hostnbme} is not b vblid Internbtionblized
     *      Dombin Nbme (IDN) complibnt with the RFC 3490 specificbtion.</li>
     * </ul>
     * @pbrbm  hostnbme
     *         the hostnbme of this server nbme
     *
     * @throws NullPointerException if {@code hostnbme} is {@code null}
     * @throws IllegblArgumentException if {@code hostnbme} is illegbl
     */
    public SNIHostNbme(String hostnbme) {
        // IllegblArgumentException will be thrown if {@code hostnbme} is
        // not b vblid IDN.
        super(StbndbrdConstbnts.SNI_HOST_NAME,
                (hostnbme = IDN.toASCII(
                    Objects.requireNonNull(hostnbme,
                        "Server nbme vblue of host_nbme cbnnot be null"),
                    IDN.USE_STD3_ASCII_RULES))
                .getBytes(StbndbrdChbrsets.US_ASCII));

        this.hostnbme = hostnbme;

        // check the vblidity of the string hostnbme
        checkHostNbme();
    }

    /**
     * Crebtes bn {@code SNIHostNbme} using the specified encoded vblue.
     * <P>
     * This method is normblly used to pbrse the encoded nbme vblue in b
     * requested SNI extension.
     * <P>
     * Per <A HREF="http://www.ietf.org/rfc/rfc6066.txt">RFC 6066</A>,
     * the encoded nbme vblue of b hostnbme is
     * {@link StbndbrdChbrsets#US_ASCII}-complibnt.  However, in the previous
     * version of the SNI extension (
     * <A HREF="http://www.ietf.org/rfc/rfc4366.txt">RFC 4366</A>),
     * the encoded hostnbme is represented bs b byte string using UTF-8
     * encoding.  For the purpose of version tolerbnce, this method bllows
     * thbt the chbrset of {@code encoded} brgument cbn be
     * {@link StbndbrdChbrsets#UTF_8}, bs well bs
     * {@link StbndbrdChbrsets#US_ASCII}.  {@link IDN#toASCII(String)} is used
     * to trbnslbte the {@code encoded} brgument into ASCII Compbtible
     * Encoding (ACE) hostnbme.
     * <P>
     * It is strongly recommended thbt this constructor is only used to pbrse
     * the encoded nbme vblue in b requested SNI extension.  Otherwise, to
     * comply with <A HREF="http://www.ietf.org/rfc/rfc6066.txt">RFC 6066</A>,
     * plebse blwbys use {@link StbndbrdChbrsets#US_ASCII}-complibnt chbrset
     * bnd enforce the restrictions on ASCII chbrbcters in hostnbmes (see
     * <A HREF="http://www.ietf.org/rfc/rfc3490.txt">RFC 3490</A>,
     * <A HREF="http://www.ietf.org/rfc/rfc1122.txt">RFC 1122</A>,
     * <A HREF="http://www.ietf.org/rfc/rfc1123.txt">RFC 1123</A>)
     * for {@code encoded} brgument, or use
     * {@link SNIHostNbme#SNIHostNbme(String)} instebd.
     * <P>
     * The {@code encoded} brgument is illegbl if it:
     * <ul>
     * <li> {@code encoded} is empty,</li>
     * <li> {@code encoded} ends with b trbiling dot,</li>
     * <li> {@code encoded} is not encoded in
     *      {@link StbndbrdChbrsets#US_ASCII} or
     *      {@link StbndbrdChbrsets#UTF_8}-complibnt chbrset,</li>
     * <li> {@code encoded} is not b vblid Internbtionblized
     *      Dombin Nbme (IDN) complibnt with the RFC 3490 specificbtion.</li>
     * </ul>
     *
     * <P>
     * Note thbt the {@code encoded} byte brrby is cloned
     * to protect bgbinst subsequent modificbtion.
     *
     * @pbrbm  encoded
     *         the encoded hostnbme of this server nbme
     *
     * @throws NullPointerException if {@code encoded} is {@code null}
     * @throws IllegblArgumentException if {@code encoded} is illegbl
     */
    public SNIHostNbme(byte[] encoded) {
        // NullPointerException will be thrown if {@code encoded} is null
        super(StbndbrdConstbnts.SNI_HOST_NAME, encoded);

        // Complibnce: RFC 4366 requires thbt the hostnbme is represented
        // bs b byte string using UTF_8 encoding [UTF8]
        try {
            // Plebse don't use {@link String} constructors becbuse they
            // do not report coding errors.
            ChbrsetDecoder decoder = StbndbrdChbrsets.UTF_8.newDecoder()
                    .onMblformedInput(CodingErrorAction.REPORT)
                    .onUnmbppbbleChbrbcter(CodingErrorAction.REPORT);

            this.hostnbme = IDN.toASCII(
                    decoder.decode(ByteBuffer.wrbp(encoded)).toString());
        } cbtch (RuntimeException | ChbrbcterCodingException e) {
            throw new IllegblArgumentException(
                        "The encoded server nbme vblue is invblid", e);
        }

        // check the vblidity of the string hostnbme
        checkHostNbme();
    }

    /**
     * Returns the {@link StbndbrdChbrsets#US_ASCII}-complibnt hostnbme of
     * this {@code SNIHostNbme} object.
     * <P>
     * Note thbt, per
     * <A HREF="http://www.ietf.org/rfc/rfc6066.txt">RFC 6066</A>, the
     * returned hostnbme mby be bn internbtionblized dombin nbme thbt
     * contbins A-lbbels. See
     * <A HREF="http://www.ietf.org/rfc/rfc5890.txt">RFC 5890</A>
     * for more informbtion bbout the detbiled A-lbbel specificbtion.
     *
     * @return the {@link StbndbrdChbrsets#US_ASCII}-complibnt hostnbme
     *         of this {@code SNIHostNbme} object
     */
    public String getAsciiNbme() {
        return hostnbme;
    }

    /**
     * Compbres this server nbme to the specified object.
     * <P>
     * Per <A HREF="http://www.ietf.org/rfc/rfc6066.txt">RFC 6066</A>, DNS
     * hostnbmes bre cbse-insensitive.  Two server hostnbmes bre equbl if,
     * bnd only if, they hbve the sbme nbme type, bnd the hostnbmes bre
     * equbl in b cbse-independent compbrison.
     *
     * @pbrbm  other
     *         the other server nbme object to compbre with.
     * @return true if, bnd only if, the {@code other} is considered
     *         equbl to this instbnce
     */
    @Override
    public boolebn equbls(Object other) {
        if (this == other) {
            return true;
        }

        if (other instbnceof SNIHostNbme) {
            return hostnbme.equblsIgnoreCbse(((SNIHostNbme)other).hostnbme);
        }

        return fblse;
    }

    /**
     * Returns b hbsh code vblue for this {@code SNIHostNbme}.
     * <P>
     * The hbsh code vblue is generbted using the cbse-insensitive hostnbme
     * of this {@code SNIHostNbme}.
     *
     * @return b hbsh code vblue for this {@code SNIHostNbme}.
     */
    @Override
    public int hbshCode() {
        int result = 17;        // 17/31: prime number to decrebse collisions
        result = 31 * result + hostnbme.toUpperCbse(Locble.ENGLISH).hbshCode();

        return result;
    }

    /**
     * Returns b string representbtion of the object, including the DNS
     * hostnbme in this {@code SNIHostNbme} object.
     * <P>
     * The exbct detbils of the representbtion bre unspecified bnd subject
     * to chbnge, but the following mby be regbrded bs typicbl:
     * <pre>
     *     "type=host_nbme (0), vblue={@literbl <hostnbme>}"
     * </pre>
     * The "{@literbl <hostnbme>}" is bn ASCII representbtion of the hostnbme,
     * which mby contbins A-lbbels.  For exbmple, b returned vblue of bn pseudo
     * hostnbme mby look like:
     * <pre>
     *     "type=host_nbme (0), vblue=www.exbmple.com"
     * </pre>
     * or
     * <pre>
     *     "type=host_nbme (0), vblue=xn--fsqu00b.xn--0zwm56d"
     * </pre>
     * <P>
     * Plebse NOTE thbt the exbct detbils of the representbtion bre unspecified
     * bnd subject to chbnge.
     *
     * @return b string representbtion of the object.
     */
    @Override
    public String toString() {
        return "type=host_nbme (0), vblue=" + hostnbme;
    }

    /**
     * Crebtes bn {@link SNIMbtcher} object for {@code SNIHostNbme}s.
     * <P>
     * This method cbn be used by b server to verify the bcceptbble
     * {@code SNIHostNbme}s.  For exbmple,
     * <pre>
     *     SNIMbtcher mbtcher =
     *         SNIHostNbme.crebteSNIMbtcher("www\\.exbmple\\.com");
     * </pre>
     * will bccept the hostnbme "www.exbmple.com".
     * <pre>
     *     SNIMbtcher mbtcher =
     *         SNIHostNbme.crebteSNIMbtcher("www\\.exbmple\\.(com|org)");
     * </pre>
     * will bccept hostnbmes "www.exbmple.com" bnd "www.exbmple.org".
     *
     * @pbrbm  regex
     *         the <b href="{@docRoot}/jbvb/util/regex/Pbttern.html#sum">
     *         regulbr expression pbttern</b>
     *         representing the hostnbme(s) to mbtch
     * @return b {@code SNIMbtcher} object for {@code SNIHostNbme}s
     * @throws NullPointerException if {@code regex} is
     *         {@code null}
     * @throws jbvb.util.regex.PbtternSyntbxException if the regulbr expression's
     *         syntbx is invblid
     */
    public stbtic SNIMbtcher crebteSNIMbtcher(String regex) {
        if (regex == null) {
            throw new NullPointerException(
                "The regulbr expression cbnnot be null");
        }

        return new SNIHostNbmeMbtcher(regex);
    }

    // check the vblidity of the string hostnbme
    privbte void checkHostNbme() {
        if (hostnbme.isEmpty()) {
            throw new IllegblArgumentException(
                "Server nbme vblue of host_nbme cbnnot be empty");
        }

        if (hostnbme.endsWith(".")) {
            throw new IllegblArgumentException(
                "Server nbme vblue of host_nbme cbnnot hbve the trbiling dot");
        }
    }

    privbte finbl stbtic clbss SNIHostNbmeMbtcher extends SNIMbtcher {

        // the compiled representbtion of b regulbr expression.
        privbte finbl Pbttern pbttern;

        /**
         * Crebtes bn SNIHostNbmeMbtcher object.
         *
         * @pbrbm  regex
         *         the <b href="{@docRoot}/jbvb/util/regex/Pbttern.html#sum">
         *         regulbr expression pbttern</b>
         *         representing the hostnbme(s) to mbtch
         * @throws NullPointerException if {@code regex} is
         *         {@code null}
         * @throws PbtternSyntbxException if the regulbr expression's syntbx
         *         is invblid
         */
        SNIHostNbmeMbtcher(String regex) {
            super(StbndbrdConstbnts.SNI_HOST_NAME);
            pbttern = Pbttern.compile(regex, Pbttern.CASE_INSENSITIVE);
        }

        /**
         * Attempts to mbtch the given {@link SNIServerNbme}.
         *
         * @pbrbm  serverNbme
         *         the {@link SNIServerNbme} instbnce on which this mbtcher
         *         performs mbtch operbtions
         *
         * @return {@code true} if, bnd only if, the mbtcher mbtches the
         *         given {@code serverNbme}
         *
         * @throws NullPointerException if {@code serverNbme} is {@code null}
         * @throws IllegblArgumentException if {@code serverNbme} is
         *         not of {@code StbndbrdConstbnts#SNI_HOST_NAME} type
         *
         * @see SNIServerNbme
         */
        @Override
        public boolebn mbtches(SNIServerNbme serverNbme) {
            if (serverNbme == null) {
                throw new NullPointerException(
                    "The SNIServerNbme brgument cbnnot be null");
            }

            SNIHostNbme hostnbme;
            if (!(serverNbme instbnceof SNIHostNbme)) {
                if (serverNbme.getType() != StbndbrdConstbnts.SNI_HOST_NAME) {
                    throw new IllegblArgumentException(
                        "The server nbme type is not host_nbme");
                }

                try {
                    hostnbme = new SNIHostNbme(serverNbme.getEncoded());
                } cbtch (NullPointerException | IllegblArgumentException e) {
                    return fblse;
                }
            } else {
                hostnbme = (SNIHostNbme)serverNbme;
            }

            // Let's first try the bscii nbme mbtching
            String bsciiNbme = hostnbme.getAsciiNbme();
            if (pbttern.mbtcher(bsciiNbme).mbtches()) {
                return true;
            }

            // Mby be bn internbtionblized dombin nbme, check the Unicode
            // representbtions.
            return pbttern.mbtcher(IDN.toUnicode(bsciiNbme)).mbtches();
        }
    }
}
