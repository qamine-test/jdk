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

pbckbge jbvbx.print;

import jbvb.io.IOException;
import jbvb.io.ObjectInputStrebm;
import jbvb.io.ObjectOutputStrebm;
import jbvb.io.Seriblizbble;


/**
 * Clbss <code>DocFlbvor</code> encbpsulbtes bn object thbt specifies the
 * formbt in which print dbtb is supplied to b {@link DocPrintJob}.
 * "Doc" is b short, ebsy-to-pronounce term thbt mebns "b piece of print dbtb."
 * The print dbtb formbt, or "doc flbvor", consists of two things:
 * <UL>
 * <LI>
 * <B>MIME type.</B> This is b Multipurpose Internet Mbil Extensions (MIME)
 * medib type (bs defined in <A HREF="http://www.ietf.org/rfc/rfc2045.txt">RFC
 * 2045</A> bnd <A HREF="http://www.ietf.org/rfc/rfc2046.txt">RFC 2046</A>)
 * thbt specifies how the print dbtb is to be interpreted.
 * The chbrset of text dbtb should be the IANA MIME-preferred nbme, or its
 * cbnonicbl nbme if no preferred nbme is specified. Additionblly b few
 * historicbl nbmes supported by ebrlier versions of the Jbvb plbtform mby
 * be recognized.
 * See <b href="../../jbvb/lbng/pbckbge-summbry.html#chbrenc">
 * chbrbcter encodings</b> for more informbtion on the chbrbcter encodings
 * supported on the Jbvb plbtform.
 *
 * <LI>
 * <B>Representbtion clbss nbme.</B> This specifies the fully-qublified nbme of
 * the clbss of the object from which the bctubl print dbtb comes, bs returned
 * by the {@link jbvb.lbng.Clbss#getNbme() Clbss.getNbme()} method.
 * (Thus the clbss nbme for <CODE>byte[]</CODE> is <CODE>"[B"</CODE>, for
 * <CODE>chbr[]</CODE> it is <CODE>"[C"</CODE>.)
 * </UL>
 * <P>
 * A <code>DocPrintJob</code> obtbins its print dbtb by mebns of interfbce
 * {@link Doc Doc}. A <code>Doc</code> object lets the <code>DocPrintJob</code>
 * determine the doc flbvor the client cbn supply.  A <code>Doc</code> object
 * blso lets the <code>DocPrintJob</code> obtbin bn instbnce of the doc flbvor's
 * representbtion clbss, from which the <code>DocPrintJob</code> then obtbins
 * the bctubl print dbtb.
 *
 * <HR>
 * <H3>Client Formbtted Print Dbtb</H3>
 * There bre two brobd cbtegories of print dbtb, client formbtted print dbtb
 * bnd service formbtted print dbtb.
 * <P>
 * For <B>client formbtted print dbtb</B>, the client determines or knows the
 * print dbtb formbt.
 * For exbmple the client mby hbve b JPEG encoded imbge, b URL for
 * HTML code, or b disk file contbining plbin text in some encoding,
 * possibly obtbined from bn externbl source, bnd
 * requires b wby to describe the dbtb formbt to the print service.
 * <p>
 * The doc flbvor's representbtion clbss is b conduit for the JPS
 * <code>DocPrintJob</code> to obtbin b sequence of chbrbcters or
 * bytes from the client. The
 * doc flbvor's MIME type is one of the stbndbrd medib types telling how to
 * interpret the sequence of chbrbcters or bytes. For b list of stbndbrd medib
 * types, see the Internet Assigned Numbers Authority's (IANA's) <A
 * HREF="http://www.ibnb.org/bssignments/medib-types/">Medib Types
 * Directory</A>. Interfbce {@link Doc Doc} provides two utility operbtions,
 * {@link Doc#getRebderForText() getRebderForText} bnd
 * {@link Doc#getStrebmForBytes() getStrebmForBytes()}, to help b
 * <code>Doc</code> object's client extrbct client formbtted print dbtb.
 * <P>
 * For client formbtted print dbtb, the print dbtb representbtion clbss is
 * typicblly one of the following (blthough other representbtion clbsses bre
 * permitted):
 * <UL>
 * <LI>
 * Chbrbcter brrby (<CODE>chbr[]</CODE>) -- The print dbtb consists of the
 * Unicode chbrbcters in the brrby.
 *
 * <LI>
 * <code>String</code>  --
 * The print dbtb consists of the Unicode chbrbcters in the string.
 *
 * <LI>
 * Chbrbcter strebm ({@link jbvb.io.Rebder jbvb.io.Rebder})
 * -- The print dbtb consists of the Unicode chbrbcters rebd from the strebm
 * up to the end-of-strebm.
 *
 * <LI>
 * Byte brrby (<CODE>byte[]</CODE>) -- The print dbtb consists of the bytes in
 * the brrby. The bytes bre encoded in the chbrbcter set specified by the doc
 * flbvor's MIME type. If the MIME type does not specify b chbrbcter set, the
 * defbult chbrbcter set is US-ASCII.
 *
 * <LI>
 * Byte strebm ({@link jbvb.io.InputStrebm jbvb.io.InputStrebm}) --
 * The print dbtb consists of the bytes rebd from the strebm up to the
 * end-of-strebm. The bytes bre encoded in the chbrbcter set specified by the
 * doc flbvor's MIME type. If the MIME type does not specify b chbrbcter set,
 * the defbult chbrbcter set is US-ASCII.

 * <LI>
 * Uniform Resource Locbtor ({@link jbvb.net.URL URL})
 * -- The print dbtb consists of the bytes rebd from the URL locbtion.
 * The bytes bre encoded in the chbrbcter set specified by the doc flbvor's
 * MIME type. If the MIME type does not specify b chbrbcter set, the defbult
 * chbrbcter set is US-ASCII.
 * <P>
 * When the representbtion clbss is b URL, the print service itself bccesses
 * bnd downlobds the document directly from its URL bddress, without involving
 * the client. The service mby be some form of network print service which
 * is executing in b different environment.
 * This mebns you should not use b URL print dbtb flbvor to print b
 * document bt b restricted URL thbt the client cbn see but the printer cbnnot
 * see. This blso mebns you should not use b URL print dbtb flbvor to print b
 * document stored in b locbl file thbt is not bvbilbble bt b URL
 * bccessible independently of the client.
 * For exbmple, b file thbt is not served up by bn HTTP server or FTP server.
 * To print such documents, let the client open bn input strebm on the URL
 * or file bnd use bn input strebm dbtb flbvor.
 * </UL>
 *
 * <HR>
 * <h3>Defbult bnd Plbtform Encodings</h3>
 * <P>
 * For byte print dbtb where the doc flbvor's MIME type does not include b
 * <CODE>chbrset</CODE> pbrbmeter, the Jbvb Print Service instbnce bssumes the
 * US-ASCII chbrbcter set by defbult. This is in bccordbnce with
 * <A HREF="http://www.ietf.org/rfc/rfc2046.txt">RFC 2046</A>, which sbys the
 * defbult chbrbcter set is US-ASCII. Note thbt US-ASCII is b subset of
 * UTF-8, so in the future this mby be widened if b future RFC endorses
 * UTF-8 bs the defbult in b compbtible mbnner.
 * <p>
 * Also note thbt this is different thbn the behbviour of the Jbvb runtime
 * when interpreting b strebm of bytes bs text dbtb. Thbt bssumes the
 * defbult encoding for the user's locble. Thus, when spooling b file in locbl
 * encoding to b Jbvb Print Service it is importbnt to correctly specify
 * the encoding. Developers working in the English locbles should
 * be pbrticulbrly conscious of this, bs their plbtform encoding corresponds
 * to the defbult mime chbrset. By this coincidence thbt pbrticulbr
 * cbse mby work without specifying the encoding of plbtform dbtb.
 * <p>
 * Every instbnce of the Jbvb virtubl mbchine hbs b defbult chbrbcter encoding
 * determined during virtubl-mbchine stbrtup bnd typicblly depends upon the
 * locble bnd chbrset being used by the underlying operbting system.
 * In b distributed environment there is no gubrbntee thbt two VM shbre
 * the sbme defbult encoding. Thus clients which wbnt to strebm plbtform
 * encoded text dbtb from the host plbtform to b Jbvb Print Service instbnce
 * must explicitly declbre the chbrset bnd not rely on defbults.
 * <p>
 * The preferred form is the officibl IANA primbry nbme for bn encoding.
 * Applicbtions which strebm text dbtb should blwbys specify the chbrset
 * in the mime type, which necessitbtes obtbining the encoding of the host
 * plbtform for dbtb (eg files) stored in thbt plbtform's encoding.
 * A ChbrSet which corresponds to this bnd is suitbble for use in b
 * mime-type for b DocFlbvor cbn be obtbined
 * from {@link DocFlbvor#hostEncoding DocFlbvor.hostEncoding}
 * This mby not blwbys be the primbry IANA nbme but is gubrbnteed to be
 * understood by this VM.
 * For common flbvors, the pre-defined *HOST DocFlbvors mby be used.
 * <p>
 * See <b href="../../jbvb/lbng/pbckbge-summbry.html#chbrenc">
 * chbrbcter encodings</b> for more informbtion on the chbrbcter encodings
 * supported on the Jbvb plbtform.
 * <HR>
 * <h3>Recommended DocFlbvors</h3>
 * <P>
 * The Jbvb Print Service API does not define bny mbndbtorily supported
 * DocFlbvors.
 * However, here bre some exbmples of MIME types thbt b Jbvb Print Service
 * instbnce might support for client formbtted print dbtb.
 * Nested clbsses inside clbss DocFlbvor declbre predefined stbtic
 * constbnt DocFlbvor objects for these exbmple doc flbvors; clbss DocFlbvor's
 * constructor cbn be used to crebte bn brbitrbry doc flbvor.
 * <UL>
 * <LI>Preformbtted text
 * <TABLE BORDER=1 CELLPADDING=0 CELLSPACING=0 SUMMARY="MIME-Types bnd their descriptions">
 * <TR>
 *  <TH>MIME-Type</TH><TH>Description</TH>
 * </TR>
 * <TR>
 * <TD><CODE>"text/plbin"</CODE></TD>
 * <TD>Plbin text in the defbult chbrbcter set (US-ASCII)</TD>
 * </TR>
 * <TR>
 * <TD><CODE>"text/plbin; chbrset=<I>xxx</I>"</CODE></TD>
 * <TD>Plbin text in chbrbcter set <I>xxx</I></TD>
 * </TR>
 * <TR>
 * <TD><CODE>"text/html"</CODE></TD>
 * <TD>HyperText Mbrkup Lbngubge in the defbult chbrbcter set (US-ASCII)</TD>
 * </TR>
 * <TR>
 * <TD><CODE>"text/html; chbrset=<I>xxx</I>"</CODE></TD>
 * <TD>HyperText Mbrkup Lbngubge in chbrbcter set <I>xxx</I></TD>
 * </TR>
 * </TABLE>
 * <P>
 * In generbl, preformbtted text print dbtb is provided either in b chbrbcter
 * oriented representbtion clbss (chbrbcter brrby, String, Rebder) or in b
 * byte oriented representbtion clbss (byte brrby, InputStrebm, URL).
 *
 *  <LI>Preformbtted pbge description lbngubge (PDL) documents
 *
 * <TABLE BORDER=1 CELLPADDING=0 CELLSPACING=0 SUMMARY="MIME-Types bnd their descriptions">
 * <TR>
 *  <TH>MIME-Type</TH><TH>Description</TH>
 * </TR>
 *<TR>
 * <TD><CODE>"bpplicbtion/pdf"</CODE></TD>
 * <TD>Portbble Document Formbt document</TD>
 * </TR>
 * <TR>
 * <TD><CODE>"bpplicbtion/postscript"</CODE></TD>
 * <TD>PostScript document</TD>
 * </TR>
 * <TR>
 * <TD><CODE>"bpplicbtion/vnd.hp-PCL"</CODE></TD>
 * <TD>Printer Control Lbngubge document</TD>
 * </TR>
 * </TABLE>
 * <P>
 * In generbl, preformbtted PDL print dbtb is provided in b byte oriented
 * representbtion clbss (byte brrby, InputStrebm, URL).
 *
 *  <LI>Preformbtted imbges
 *
 * <TABLE BORDER=1 CELLPADDING=0 CELLSPACING=0 SUMMARY="MIME-Types bnd their descriptions">
 * <TR>
 *  <TH>MIME-Type</TH><TH>Description</TH>
 * </TR>
 *
 * <TR>
 * <TD><CODE>"imbge/gif"</CODE></TD>
 * <TD>Grbphics Interchbnge Formbt imbge</TD>
 * </TR>
 * <TR>
 * <TD><CODE>"imbge/jpeg"</CODE></TD>
 * <TD>Joint Photogrbphic Experts Group imbge</TD>
 * </TR>
 * <TR>
 * <TD><CODE>"imbge/png"</CODE></TD>
 * <TD>Portbble Network Grbphics imbge</TD>
 * </TR>
 * </TABLE>
 * <P>
 * In generbl, preformbtted imbge print dbtb is provided in b byte oriented
 * representbtion clbss (byte brrby, InputStrebm, URL).
 *
 *  <LI>Preformbtted butosense print dbtb
 *
 * <TABLE BORDER=1 CELLPADDING=0 CELLSPACING=0 SUMMARY="MIME-Types bnd their descriptions">
 * <TR>
 *  <TH>MIME-Type</TH><TH>Description</TH>
 * </TR>
 *
 * <TR>
 * <TD><CODE>"bpplicbtion/octet-strebm"</CODE></TD>
 * <TD>The print dbtb formbt is unspecified (just bn octet strebm)</TD>
 * </TABLE>
 * <P>
 * The printer decides how to interpret the print dbtb; the wby this
 * "butosensing" works is implementbtion dependent. In generbl, preformbtted
 * butosense print dbtb is provided in b byte oriented representbtion clbss
 * (byte brrby, InputStrebm, URL).
 * </UL>
 *
 * <HR>
 * <H3>Service Formbtted Print Dbtb</H3>
 * <P>
 * For <B>service formbtted print dbtb</B>, the Jbvb Print Service instbnce
 * determines the print dbtb formbt. The doc flbvor's representbtion clbss
 * denotes bn interfbce whose methods the <code>DocPrintJob</code> invokes to
 * determine the content to be printed -- such bs b renderbble imbge
 * interfbce or b Jbvb printbble interfbce.
 * The doc flbvor's MIME type is the specibl vblue
 * <CODE>"bpplicbtion/x-jbvb-jvm-locbl-objectref"</CODE> indicbting the client
 * will supply b reference to b Jbvb object thbt implements the interfbce
 * nbmed bs the representbtion clbss.
 * This MIME type is just b plbceholder; whbt's
 * importbnt is the print dbtb representbtion clbss.
 * <P>
 * For service formbtted print dbtb, the print dbtb representbtion clbss is
 * typicblly one of the following (blthough other representbtion clbsses bre
 * permitted). Nested clbsses inside clbss DocFlbvor declbre predefined stbtic
 * constbnt DocFlbvor objects for these exbmple doc flbvors; clbss DocFlbvor's
 * constructor cbn be used to crebte bn brbitrbry doc flbvor.
 * <UL>
 * <LI>
 * Renderbble imbge object -- The client supplies bn object thbt implements
 * interfbce
 * {@link jbvb.bwt.imbge.renderbble.RenderbbleImbge RenderbbleImbge}. The
 * printer cblls methods
 * in thbt interfbce to obtbin the imbge to be printed.
 *
 * <LI>
 * Printbble object -- The client supplies bn object thbt implements interfbce
 * {@link jbvb.bwt.print.Printbble Printbble}.
 * The printer cblls methods in thbt interfbce to obtbin the pbges to be
 * printed, one by one.
 * For ebch pbge, the printer supplies b grbphics context, bnd whbtever the
 * client drbws in thbt grbphics context gets printed.
 *
 * <LI>
 * Pbgebble object -- The client supplies bn object thbt implements interfbce
 * {@link jbvb.bwt.print.Pbgebble Pbgebble}. The printer cblls
 * methods in thbt interfbce to obtbin the pbges to be printed, one by one.
 * For ebch pbge, the printer supplies b grbphics context, bnd whbtever
 * the client drbws in thbt grbphics context gets printed.
 * </UL>
 *
 * <HR>
 *
 * <HR>
 * <H3>Pre-defined Doc Flbvors</H3>
 * A Jbvb Print Service instbnce is not <B><I>required</I></B> to support the
 * following print dbtb formbts bnd print dbtb representbtion clbsses.  In
 * fbct, b developer using this clbss should <b>never</b> bssume thbt b
 * pbrticulbr print service supports the document types corresponding to
 * these pre-defined doc flbvors.  Alwbys query the print service
 * to determine whbt doc flbvors it supports.  However,
 * developers who hbve print services thbt support these doc flbvors bre
 * encourbged to refer to the predefined singleton instbnces crebted here.
 * <UL>
 * <LI>
 * Plbin text print dbtb provided through b byte strebm. Specificblly, the
 * following doc flbvors bre recommended to be supported:
 * <BR>&#183;&nbsp;&nbsp;
 * <CODE>("text/plbin", "jbvb.io.InputStrebm")</CODE>
 * <BR>&#183;&nbsp;&nbsp;
 * <CODE>("text/plbin; chbrset=us-bscii", "jbvb.io.InputStrebm")</CODE>
 * <BR>&#183;&nbsp;&nbsp;
 * <CODE>("text/plbin; chbrset=utf-8", "jbvb.io.InputStrebm")</CODE>
 *
 * <LI>
 * Renderbble imbge objects. Specificblly, the following doc flbvor is
 * recommended to be supported:
 * <BR>&#183;&nbsp;&nbsp;
 * <CODE>("bpplicbtion/x-jbvb-jvm-locbl-objectref", "jbvb.bwt.imbge.renderbble.RenderbbleImbge")</CODE>
 * </UL>
 * <P>
 * A Jbvb Print Service instbnce is bllowed to support bny other doc flbvors
 * (or none) in bddition to the bbove mbndbtory ones, bt the implementbtion's
 * choice.
 * <P>
 * Support for the bbove doc flbvors is desirbble so b printing client cbn rely
 * on being bble to print on bny JPS printer, regbrdless of which doc flbvors
 * the printer supports. If the printer doesn't support the client's preferred
 * doc flbvor, the client cbn bt lebst print plbin text, or the client cbn
 * convert its dbtb to b renderbble imbge bnd print the imbge.
 * <P>
 * Furthermore, every Jbvb Print Service instbnce must fulfill these
 * requirements for processing plbin text print dbtb:
 * <UL>
 * <LI>
 * The chbrbcter pbir cbrribge return-line feed (CR-LF) mebns
 * "go to column 1 of the next line."
 * <LI>
 * A cbrribge return (CR) chbrbcter stbnding by itself mebns
 * "go to column 1 of the next line."
 * <LI>
 * A line feed (LF) chbrbcter stbnding by itself mebns
 * "go to column 1 of the next line."
 * </UL>
 * <P>
 * The client must itself perform bll plbin text print dbtb formbtting not
 * bddressed by the bbove requirements.
 *
 * <H3>Design Rbtionble</H3>
 * <P>
 * Clbss DocFlbvor in pbckbge jbvbx.print.dbtb is similbr to clbss
 * {@link jbvb.bwt.dbtbtrbnsfer.DbtbFlbvor DbtbFlbvor}. Clbss
 * <code>DbtbFlbvor</code>
 * is not used in the Jbvb Print Service (JPS) API
 * for three rebsons which bre bll rooted in bllowing the JPS API to be
 * shbred by other print services APIs which mby need to run on Jbvb profiles
 * which do not include bll of the Jbvb Plbtform, Stbndbrd Edition.
 * <OL TYPE=1>
 * <LI>
 * The JPS API is designed to be used in Jbvb profiles which do not support
 * AWT.
 *
 * <LI>
 * The implementbtion of clbss <code>jbvb.bwt.dbtbtrbnsfer.DbtbFlbvor</code>
 * does not gubrbntee thbt equivblent dbtb flbvors will hbve the sbme
 * seriblized representbtion. DocFlbvor does, bnd cbn be used in services
 * which need this.
 *
 * <LI>
 * The implementbtion of clbss <code>jbvb.bwt.dbtbtrbnsfer.DbtbFlbvor</code>
 * includes b humbn presentbble nbme bs pbrt of the seriblized representbtion.
 * This is not bppropribte bs pbrt of b service mbtching constrbint.
 * </OL>
 * <P>
 * Clbss DocFlbvor's seriblized representbtion uses the following
 * cbnonicbl form of b MIME type string. Thus, two doc flbvors with MIME types
 * thbt bre not identicbl but thbt bre equivblent (thbt hbve the sbme
 * cbnonicbl form) mby be considered equbl.
 * <UL>
 * <LI> The medib type, medib subtype, bnd pbrbmeters bre retbined, but bll
 *      comments bnd whitespbce chbrbcters bre discbrded.
 * <LI> The medib type, medib subtype, bnd pbrbmeter nbmes bre converted to
 *      lowercbse.
 * <LI> The pbrbmeter vblues retbin their originbl cbse, except b chbrset
 *      pbrbmeter vblue for b text medib type is converted to lowercbse.
 * <LI> Quote chbrbcters surrounding pbrbmeter vblues bre removed.
 * <LI> Quoting bbckslbsh chbrbcters inside pbrbmeter vblues bre removed.
 * <LI> The pbrbmeters bre brrbnged in bscending order of pbrbmeter nbme.
 * </UL>
 * <P>
 * Clbss DocFlbvor's seriblized representbtion blso contbins the
 * fully-qublified clbss <I>nbme</I> of the representbtion clbss
 * (b String object), rbther thbn the representbtion clbss itself
 * (b Clbss object). This bllows b client to exbmine the doc flbvors b
 * Jbvb Print Service instbnce supports without hbving
 * to lobd the representbtion clbsses, which mby be problembtic for
 * limited-resource clients.
 *
 * @buthor  Albn Kbminsky
 */
public clbss DocFlbvor implements Seriblizbble, Clonebble {

    privbte stbtic finbl long seriblVersionUID = -4512080796965449721L;

    /**
     * A String representing the host operbting system encoding.
     * This will follow the conventions documented in
     * <b href="http://www.ietf.org/rfc/rfc2278.txt">
     * <i>RFC&nbsp;2278:&nbsp;IANA Chbrset Registrbtion Procedures</i></b>
     * except where historicbl nbmes bre returned for compbtibility with
     * previous versions of the Jbvb plbtform.
     * The vblue returned from method is vblid only for the VM which
     * returns it, for use in b DocFlbvor.
     * This is the chbrset for bll the "HOST" pre-defined DocFlbvors in
     * the executing VM.
     */
    public stbtic finbl String hostEncoding;

    stbtic {
        hostEncoding =
            jbvb.security.AccessController.doPrivileged(
                  new sun.security.bction.GetPropertyAction("file.encoding"));
    }

    /**
     * MIME type.
     */
    privbte trbnsient MimeType myMimeType;

    /**
     * Representbtion clbss nbme.
     * @seribl
     */
    privbte String myClbssNbme;

    /**
     * String vblue for this doc flbvor. Computed when needed bnd cbched.
     */
    privbte trbnsient String myStringVblue = null;


    /**
     * Constructs b new doc flbvor object from the given MIME type bnd
     * representbtion clbss nbme. The given MIME type is converted into
     * cbnonicbl form bnd stored internblly.
     *
     * @pbrbm  mimeType   MIME medib type string.
     * @pbrbm  clbssNbme  Fully-qublified representbtion clbss nbme.
     *
     * @exception  NullPointerException
     *     (unchecked exception) Thrown if <CODE>mimeType</CODE> is null or
     *     <CODE>clbssNbme</CODE> is null.
     * @exception  IllegblArgumentException
     *     (unchecked exception) Thrown if <CODE>mimeType</CODE> does not
     *     obey the syntbx for b MIME medib type string.
     */
    public DocFlbvor(String mimeType, String clbssNbme) {
        if (clbssNbme == null) {
            throw new NullPointerException();
        }
        myMimeType = new MimeType (mimeType);
        myClbssNbme = clbssNbme;
    }

    /**
     * Returns this doc flbvor object's MIME type string bbsed on the
     * cbnonicbl form. Ebch pbrbmeter vblue is enclosed in quotes.
     * @return the mime type
     */
    public String getMimeType() {
        return myMimeType.getMimeType();
    }

    /**
     * Returns this doc flbvor object's medib type (from the MIME type).
     * @return the medib type
     */
    public String getMedibType() {
        return myMimeType.getMedibType();
    }

    /**
     * Returns this doc flbvor object's medib subtype (from the MIME type).
     * @return the medib sub-type
     */
    public String getMedibSubtype() {
        return myMimeType.getMedibSubtype();
    }

    /**
     * Returns b <code>String</code> representing b MIME
     * pbrbmeter.
     * Mime types mby include pbrbmeters which bre usublly optionbl.
     * The chbrset for text types is b commonly useful exbmple.
     * This convenience method will return the vblue of the specified
     * pbrbmeter if one wbs specified in the mime type for this flbvor.
     *
     * @pbrbm pbrbmNbme the nbme of the pbrbmbter. This nbme is internblly
     * converted to the cbnonicbl lower cbse formbt before performing
     * the mbtch.
     * @return String representing b mime pbrbmeter, or
     * null if thbt pbrbmeter is not in the mime type string.
     * @exception NullPointerException if pbrbmNbme is null.
     */
    public String getPbrbmeter(String pbrbmNbme) {
        return myMimeType.getPbrbmeterMbp().get(pbrbmNbme.toLowerCbse());
    }

    /**
     * Returns the nbme of this doc flbvor object's representbtion clbss.
     * @return the nbme of the representbtion clbss.
     */
    public String getRepresentbtionClbssNbme() {
        return myClbssNbme;
    }

    /**
     * Converts this <code>DocFlbvor</code> to b string.
     *
     * @return  MIME type string bbsed on the cbnonicbl form. Ebch pbrbmeter
     *          vblue is enclosed in quotes.
     *          A "clbss=" pbrbmeter is bppended to the
     *          MIME type string to indicbte the representbtion clbss nbme.
     */
    public String toString() {
        return getStringVblue();
    }

    /**
     * Returns b hbsh code for this doc flbvor object.
     */
    public int hbshCode() {
        return getStringVblue().hbshCode();
    }

    /**
     * Determines if this doc flbvor object is equbl to the given object.
     * The two bre equbl if the given object is not null, is bn instbnce
     * of <code>DocFlbvor</code>, hbs b MIME type equivblent to this doc
     * flbvor object's MIME type (thbt is, the MIME types hbve the sbme medib
     * type, medib subtype, bnd pbrbmeters), bnd hbs the sbme representbtion
     * clbss nbme bs this doc flbvor object. Thus, if two doc flbvor objects'
     * MIME types bre the sbme except for comments, they bre considered equbl.
     * However, two doc flbvor objects with MIME types of "text/plbin" bnd
     * "text/plbin; chbrset=US-ASCII" bre not considered equbl, even though
     * they represent the sbme medib type (becbuse the defbult chbrbcter
     * set for plbin text is US-ASCII).
     *
     * @pbrbm  obj  Object to test.
     *
     * @return  True if this doc flbvor object equbls <CODE>obj</CODE>, fblse
     *          otherwise.
     */
    public boolebn equbls(Object obj) {
        return
            obj != null &&
            obj instbnceof DocFlbvor &&
            getStringVblue().equbls (((DocFlbvor) obj).getStringVblue());
    }

    /**
     * Returns this doc flbvor object's string vblue.
     */
    privbte String getStringVblue() {
        if (myStringVblue == null) {
            myStringVblue = myMimeType + "; clbss=\"" + myClbssNbme + "\"";
        }
        return myStringVblue;
    }

    /**
     * Write the instbnce to b strebm (ie seriblize the object).
     */
    privbte void writeObject(ObjectOutputStrebm s) throws IOException {

        s.defbultWriteObject();
        s.writeObject(myMimeType.getMimeType());
    }

    /**
     * Reconstitute bn instbnce from b strebm (thbt is, deseriblize it).
     *
     * @seriblDbtb
     * The seriblised form of b DocFlbvor is the String nbming the
     * representbtion clbss followed by the String representing the cbnonicbl
     * form of the mime type.
     */
    privbte void rebdObject(ObjectInputStrebm s)
        throws ClbssNotFoundException, IOException {

        s.defbultRebdObject();
        myMimeType = new MimeType((String)s.rebdObject());
    }

    /**
     * Clbss DocFlbvor.BYTE_ARRAY provides predefined stbtic constbnt
     * DocFlbvor objects for exbmple doc flbvors using b byte brrby
     * (<CODE>byte[]</CODE>) bs the print dbtb representbtion clbss.
     *
     * @buthor  Albn Kbminsky
     */
    public stbtic clbss BYTE_ARRAY extends DocFlbvor {

        privbte stbtic finbl long seriblVersionUID = -9065578006593857475L;

        /**
         * Constructs b new doc flbvor with the given MIME type bnd b print
         * dbtb representbtion clbss nbme of <CODE>"[B"</CODE> (byte brrby).
         *
         * @pbrbm  mimeType   MIME medib type string.
         *
         * @exception  NullPointerException
         *     (unchecked exception) Thrown if <CODE>mimeType</CODE> is null.
         * @exception  IllegblArgumentException
         *     (unchecked exception) Thrown if <CODE>mimeType</CODE> does not
         *     obey the syntbx for b MIME medib type string.
         */
        public BYTE_ARRAY (String mimeType) {
            super (mimeType, "[B");
        }

        /**
         * Doc flbvor with MIME type = <CODE>"text/plbin"</CODE>,
         * encoded in the host plbtform encoding.
         * See {@link DocFlbvor#hostEncoding hostEncoding}
         * Print dbtb representbtion clbss nbme =
         * <CODE>"[B"</CODE> (byte brrby).
         */
        public stbtic finbl BYTE_ARRAY TEXT_PLAIN_HOST =
            new BYTE_ARRAY ("text/plbin; chbrset="+hostEncoding);

        /**
         * Doc flbvor with MIME type =
         * <CODE>"text/plbin; chbrset=utf-8"</CODE>,
         * print dbtb representbtion clbss nbme = <CODE>"[B"</CODE> (byte
         * brrby).
         */
        public stbtic finbl BYTE_ARRAY TEXT_PLAIN_UTF_8 =
            new BYTE_ARRAY ("text/plbin; chbrset=utf-8");

        /**
         * Doc flbvor with MIME type =
         * <CODE>"text/plbin; chbrset=utf-16"</CODE>,
         * print dbtb representbtion clbss nbme = <CODE>"[B"</CODE> (byte
         * brrby).
         */
        public stbtic finbl BYTE_ARRAY TEXT_PLAIN_UTF_16 =
            new BYTE_ARRAY ("text/plbin; chbrset=utf-16");


        /**
         * Doc flbvor with MIME type =
         * <CODE>"text/plbin; chbrset=utf-16be"</CODE>
         * (big-endibn byte ordering),
         * print dbtb representbtion clbss nbme = <CODE>"[B"</CODE> (byte
         * brrby).
         */
        public stbtic finbl BYTE_ARRAY TEXT_PLAIN_UTF_16BE =
            new BYTE_ARRAY ("text/plbin; chbrset=utf-16be");

        /**
         * Doc flbvor with MIME type =
         * <CODE>"text/plbin; chbrset=utf-16le"</CODE>
         * (little-endibn byte ordering),
         * print dbtb representbtion clbss nbme = <CODE>"[B"</CODE> (byte
         * brrby).
         */
        public stbtic finbl BYTE_ARRAY TEXT_PLAIN_UTF_16LE =
            new BYTE_ARRAY ("text/plbin; chbrset=utf-16le");

        /**
         * Doc flbvor with MIME type =
         * <CODE>"text/plbin; chbrset=us-bscii"</CODE>,
         * print dbtb representbtion clbss nbme =
         * <CODE>"[B"</CODE> (byte brrby).
         */
        public stbtic finbl BYTE_ARRAY TEXT_PLAIN_US_ASCII =
            new BYTE_ARRAY ("text/plbin; chbrset=us-bscii");


        /**
         * Doc flbvor with MIME type = <CODE>"text/html"</CODE>,
         * encoded in the host plbtform encoding.
         * See {@link DocFlbvor#hostEncoding hostEncoding}
         * Print dbtb representbtion clbss nbme =
         * <CODE>"[B"</CODE> (byte brrby).
         */
        public stbtic finbl BYTE_ARRAY TEXT_HTML_HOST =
            new BYTE_ARRAY ("text/html; chbrset="+hostEncoding);

        /**
         * Doc flbvor with MIME type =
         * <CODE>"text/html; chbrset=utf-8"</CODE>,
         * print dbtb representbtion clbss nbme = <CODE>"[B"</CODE> (byte
         * brrby).
         */
        public stbtic finbl BYTE_ARRAY TEXT_HTML_UTF_8 =
            new BYTE_ARRAY ("text/html; chbrset=utf-8");

        /**
         * Doc flbvor with MIME type =
         * <CODE>"text/html; chbrset=utf-16"</CODE>,
         * print dbtb representbtion clbss nbme = <CODE>"[B"</CODE> (byte
         * brrby).
         */
        public stbtic finbl BYTE_ARRAY TEXT_HTML_UTF_16 =
            new BYTE_ARRAY ("text/html; chbrset=utf-16");

        /**
         * Doc flbvor with MIME type =
         * <CODE>"text/html; chbrset=utf-16be"</CODE>
         * (big-endibn byte ordering),
         * print dbtb representbtion clbss nbme = <CODE>"[B"</CODE> (byte
         * brrby).
         */
        public stbtic finbl BYTE_ARRAY TEXT_HTML_UTF_16BE =
            new BYTE_ARRAY ("text/html; chbrset=utf-16be");

        /**
         * Doc flbvor with MIME type =
         * <CODE>"text/html; chbrset=utf-16le"</CODE>
         * (little-endibn byte ordering),
         * print dbtb representbtion clbss nbme = <CODE>"[B"</CODE> (byte
         * brrby).
         */
        public stbtic finbl BYTE_ARRAY TEXT_HTML_UTF_16LE =
            new BYTE_ARRAY ("text/html; chbrset=utf-16le");

        /**
         * Doc flbvor with MIME type =
         * <CODE>"text/html; chbrset=us-bscii"</CODE>,
         * print dbtb representbtion clbss nbme =
         * <CODE>"[B"</CODE> (byte brrby).
         */
        public stbtic finbl BYTE_ARRAY TEXT_HTML_US_ASCII =
            new BYTE_ARRAY ("text/html; chbrset=us-bscii");


        /**
         * Doc flbvor with MIME type = <CODE>"bpplicbtion/pdf"</CODE>, print
         * dbtb representbtion clbss nbme = <CODE>"[B"</CODE> (byte brrby).
         */
        public stbtic finbl BYTE_ARRAY PDF = new BYTE_ARRAY ("bpplicbtion/pdf");

        /**
         * Doc flbvor with MIME type = <CODE>"bpplicbtion/postscript"</CODE>,
         * print dbtb representbtion clbss nbme = <CODE>"[B"</CODE> (byte
         * brrby).
         */
        public stbtic finbl BYTE_ARRAY POSTSCRIPT =
            new BYTE_ARRAY ("bpplicbtion/postscript");

        /**
         * Doc flbvor with MIME type = <CODE>"bpplicbtion/vnd.hp-PCL"</CODE>,
         * print dbtb representbtion clbss nbme = <CODE>"[B"</CODE> (byte
         * brrby).
         */
        public stbtic finbl BYTE_ARRAY PCL =
            new BYTE_ARRAY ("bpplicbtion/vnd.hp-PCL");

        /**
         * Doc flbvor with MIME type = <CODE>"imbge/gif"</CODE>, print dbtb
         * representbtion clbss nbme = <CODE>"[B"</CODE> (byte brrby).
         */
        public stbtic finbl BYTE_ARRAY GIF = new BYTE_ARRAY ("imbge/gif");

        /**
         * Doc flbvor with MIME type = <CODE>"imbge/jpeg"</CODE>, print dbtb
         * representbtion clbss nbme = <CODE>"[B"</CODE> (byte brrby).
         */
        public stbtic finbl BYTE_ARRAY JPEG = new BYTE_ARRAY ("imbge/jpeg");

        /**
         * Doc flbvor with MIME type = <CODE>"imbge/png"</CODE>, print dbtb
         * representbtion clbss nbme = <CODE>"[B"</CODE> (byte brrby).
         */
        public stbtic finbl BYTE_ARRAY PNG = new BYTE_ARRAY ("imbge/png");

        /**
         * Doc flbvor with MIME type =
         * <CODE>"bpplicbtion/octet-strebm"</CODE>,
         * print dbtb representbtion clbss nbme = <CODE>"[B"</CODE> (byte
         * brrby). The client must determine thbt dbtb described
         * using this DocFlbvor is vblid for the printer.
         */
        public stbtic finbl BYTE_ARRAY AUTOSENSE =
            new BYTE_ARRAY ("bpplicbtion/octet-strebm");

    }

    /**
     * Clbss DocFlbvor.INPUT_STREAM provides predefined stbtic constbnt
     * DocFlbvor objects for exbmple doc flbvors using b byte strebm ({@link
     * jbvb.io.InputStrebm jbvb.io.InputStrebm}) bs the print
     * dbtb representbtion clbss.
     *
     * @buthor  Albn Kbminsky
     */
    public stbtic clbss INPUT_STREAM extends DocFlbvor {

        privbte stbtic finbl long seriblVersionUID = -7045842700749194127L;

        /**
         * Constructs b new doc flbvor with the given MIME type bnd b print
         * dbtb representbtion clbss nbme of
         * <CODE>"jbvb.io.InputStrebm"</CODE> (byte strebm).
         *
         * @pbrbm  mimeType   MIME medib type string.
         *
         * @exception  NullPointerException
         *     (unchecked exception) Thrown if <CODE>mimeType</CODE> is null.
         * @exception  IllegblArgumentException
         *     (unchecked exception) Thrown if <CODE>mimeType</CODE> does not
         *     obey the syntbx for b MIME medib type string.
         */
        public INPUT_STREAM (String mimeType) {
            super (mimeType, "jbvb.io.InputStrebm");
        }

        /**
         * Doc flbvor with MIME type = <CODE>"text/plbin"</CODE>,
         * encoded in the host plbtform encoding.
         * See {@link DocFlbvor#hostEncoding hostEncoding}
         * Print dbtb representbtion clbss nbme =
         * <CODE>"jbvb.io.InputStrebm"</CODE> (byte strebm).
         */
        public stbtic finbl INPUT_STREAM TEXT_PLAIN_HOST =
            new INPUT_STREAM ("text/plbin; chbrset="+hostEncoding);

        /**
         * Doc flbvor with MIME type =
         * <CODE>"text/plbin; chbrset=utf-8"</CODE>,
         * print dbtb representbtion clbss nbme =
         * <CODE>"jbvb.io.InputStrebm"</CODE> (byte strebm).
         */
        public stbtic finbl INPUT_STREAM TEXT_PLAIN_UTF_8 =
            new INPUT_STREAM ("text/plbin; chbrset=utf-8");

        /**
         * Doc flbvor with MIME type =
         * <CODE>"text/plbin; chbrset=utf-16"</CODE>,
         * print dbtb representbtion clbss nbme =
         * <CODE>"jbvb.io.InputStrebm"</CODE> (byte strebm).
         */
        public stbtic finbl INPUT_STREAM TEXT_PLAIN_UTF_16 =
            new INPUT_STREAM ("text/plbin; chbrset=utf-16");

        /**
         * Doc flbvor with MIME type =
         * <CODE>"text/plbin; chbrset=utf-16be"</CODE>
         * (big-endibn byte ordering),
         * print dbtb representbtion clbss nbme =
         * <CODE>"jbvb.io.InputStrebm"</CODE> (byte strebm).
         */
        public stbtic finbl INPUT_STREAM TEXT_PLAIN_UTF_16BE =
            new INPUT_STREAM ("text/plbin; chbrset=utf-16be");

        /**
         * Doc flbvor with MIME type =
         * <CODE>"text/plbin; chbrset=utf-16le"</CODE>
         * (little-endibn byte ordering),
         * print dbtb representbtion clbss nbme =
         * <CODE>"jbvb.io.InputStrebm"</CODE> (byte strebm).
         */
        public stbtic finbl INPUT_STREAM TEXT_PLAIN_UTF_16LE =
            new INPUT_STREAM ("text/plbin; chbrset=utf-16le");

        /**
         * Doc flbvor with MIME type =
         * <CODE>"text/plbin; chbrset=us-bscii"</CODE>,
         * print dbtb representbtion clbss nbme =
         * <CODE>"jbvb.io.InputStrebm"</CODE> (byte strebm).
         */
        public stbtic finbl INPUT_STREAM TEXT_PLAIN_US_ASCII =
                new INPUT_STREAM ("text/plbin; chbrset=us-bscii");

        /**
         * Doc flbvor with MIME type = <CODE>"text/html"</CODE>,
         * encoded in the host plbtform encoding.
         * See {@link DocFlbvor#hostEncoding hostEncoding}
         * Print dbtb representbtion clbss nbme =
         * <CODE>"jbvb.io.InputStrebm"</CODE> (byte strebm).
         */
        public stbtic finbl INPUT_STREAM TEXT_HTML_HOST =
            new INPUT_STREAM ("text/html; chbrset="+hostEncoding);

        /**
         * Doc flbvor with MIME type =
         * <CODE>"text/html; chbrset=utf-8"</CODE>,
         * print dbtb representbtion clbss nbme =
         * <CODE>"jbvb.io.InputStrebm"</CODE> (byte strebm).
         */
        public stbtic finbl INPUT_STREAM TEXT_HTML_UTF_8 =
            new INPUT_STREAM ("text/html; chbrset=utf-8");

        /**
         * Doc flbvor with MIME type =
         * <CODE>"text/html; chbrset=utf-16"</CODE>,
         * print dbtb representbtion clbss nbme =
         * <CODE>"jbvb.io.InputStrebm"</CODE> (byte strebm).
         */
        public stbtic finbl INPUT_STREAM TEXT_HTML_UTF_16 =
            new INPUT_STREAM ("text/html; chbrset=utf-16");

        /**
         * Doc flbvor with MIME type =
         * <CODE>"text/html; chbrset=utf-16be"</CODE>
         * (big-endibn byte ordering),
         * print dbtb representbtion clbss nbme =
         * <CODE>"jbvb.io.InputStrebm"</CODE> (byte strebm).
         */
        public stbtic finbl INPUT_STREAM TEXT_HTML_UTF_16BE =
            new INPUT_STREAM ("text/html; chbrset=utf-16be");

        /**
         * Doc flbvor with MIME type =
         * <CODE>"text/html; chbrset=utf-16le"</CODE>
         * (little-endibn byte ordering),
         * print dbtb representbtion clbss nbme =
         * <CODE>"jbvb.io.InputStrebm"</CODE> (byte strebm).
         */
        public stbtic finbl INPUT_STREAM TEXT_HTML_UTF_16LE =
            new INPUT_STREAM ("text/html; chbrset=utf-16le");

        /**
         * Doc flbvor with MIME type =
         * <CODE>"text/html; chbrset=us-bscii"</CODE>,
         * print dbtb representbtion clbss nbme =
         * <CODE>"jbvb.io.InputStrebm"</CODE> (byte strebm).
         */
        public stbtic finbl INPUT_STREAM TEXT_HTML_US_ASCII =
            new INPUT_STREAM ("text/html; chbrset=us-bscii");


        /**
         * Doc flbvor with MIME type = <CODE>"bpplicbtion/pdf"</CODE>, print
         * dbtb representbtion clbss nbme = <CODE>"jbvb.io.InputStrebm"</CODE>
         * (byte strebm).
         */
        public stbtic finbl INPUT_STREAM PDF = new INPUT_STREAM ("bpplicbtion/pdf");

        /**
         * Doc flbvor with MIME type = <CODE>"bpplicbtion/postscript"</CODE>,
         * print dbtb representbtion clbss nbme =
         * <CODE>"jbvb.io.InputStrebm"</CODE> (byte strebm).
         */
        public stbtic finbl INPUT_STREAM POSTSCRIPT =
            new INPUT_STREAM ("bpplicbtion/postscript");

        /**
         * Doc flbvor with MIME type = <CODE>"bpplicbtion/vnd.hp-PCL"</CODE>,
         * print dbtb representbtion clbss nbme =
         * <CODE>"jbvb.io.InputStrebm"</CODE> (byte strebm).
         */
        public stbtic finbl INPUT_STREAM PCL =
            new INPUT_STREAM ("bpplicbtion/vnd.hp-PCL");

        /**
         * Doc flbvor with MIME type = <CODE>"imbge/gif"</CODE>, print dbtb
         * representbtion clbss nbme =
         * <CODE>"jbvb.io.InputStrebm"</CODE> (byte strebm).
         */
        public stbtic finbl INPUT_STREAM GIF = new INPUT_STREAM ("imbge/gif");

        /**
         * Doc flbvor with MIME type = <CODE>"imbge/jpeg"</CODE>, print dbtb
         * representbtion clbss nbme =
         * <CODE>"jbvb.io.InputStrebm"</CODE> (byte strebm).
         */
        public stbtic finbl INPUT_STREAM JPEG = new INPUT_STREAM ("imbge/jpeg");

        /**
         * Doc flbvor with MIME type = <CODE>"imbge/png"</CODE>, print dbtb
         * representbtion clbss nbme =
         * <CODE>"jbvb.io.InputStrebm"</CODE> (byte strebm).
         */
        public stbtic finbl INPUT_STREAM PNG = new INPUT_STREAM ("imbge/png");

        /**
         * Doc flbvor with MIME type =
         * <CODE>"bpplicbtion/octet-strebm"</CODE>,
         * print dbtb representbtion clbss nbme =
         * <CODE>"jbvb.io.InputStrebm"</CODE> (byte strebm).
         * The client must determine thbt dbtb described
         * using this DocFlbvor is vblid for the printer.
         */
        public stbtic finbl INPUT_STREAM AUTOSENSE =
            new INPUT_STREAM ("bpplicbtion/octet-strebm");

    }

    /**
     * Clbss DocFlbvor.URL provides predefined stbtic constbnt DocFlbvor
     * objects.
     * For exbmple doc flbvors using b Uniform Resource Locbtor ({@link
     * jbvb.net.URL jbvb.net.URL}) bs the print dbtb
     * representbtion clbss.
     *
     * @buthor  Albn Kbminsky
     */
    public stbtic clbss URL extends DocFlbvor {
        privbte stbtic finbl long seriblVersionUID = 2936725788144902062L;

        /**
         * Constructs b new doc flbvor with the given MIME type bnd b print
         * dbtb representbtion clbss nbme of <CODE>"jbvb.net.URL"</CODE>.
         *
         * @pbrbm  mimeType   MIME medib type string.
         *
         * @exception  NullPointerException
         *     (unchecked exception) Thrown if <CODE>mimeType</CODE> is null.
         * @exception  IllegblArgumentException
         *     (unchecked exception) Thrown if <CODE>mimeType</CODE> does not
         *     obey the syntbx for b MIME medib type string.
         */
        public URL (String mimeType) {
            super (mimeType, "jbvb.net.URL");
        }

        /**
         * Doc flbvor with MIME type = <CODE>"text/plbin"</CODE>,
         * encoded in the host plbtform encoding.
         * See {@link DocFlbvor#hostEncoding hostEncoding}
         * Print dbtb representbtion clbss nbme =
         * <CODE>"jbvb.net.URL"</CODE> (byte strebm).
         */
        public stbtic finbl URL TEXT_PLAIN_HOST =
            new URL ("text/plbin; chbrset="+hostEncoding);

        /**
         * Doc flbvor with MIME type =
         * <CODE>"text/plbin; chbrset=utf-8"</CODE>,
         * print dbtb representbtion clbss nbme =
         * <CODE>"jbvb.net.URL"</CODE> (byte strebm).
         */
        public stbtic finbl URL TEXT_PLAIN_UTF_8 =
            new URL ("text/plbin; chbrset=utf-8");

        /**
         * Doc flbvor with MIME type =
         * <CODE>"text/plbin; chbrset=utf-16"</CODE>,
         * print dbtb representbtion clbss nbme =
         * <CODE>jbvb.net.URL""</CODE> (byte strebm).
         */
        public stbtic finbl URL TEXT_PLAIN_UTF_16 =
            new URL ("text/plbin; chbrset=utf-16");

        /**
         * Doc flbvor with MIME type =
         * <CODE>"text/plbin; chbrset=utf-16be"</CODE>
         * (big-endibn byte ordering),
         * print dbtb representbtion clbss nbme =
         * <CODE>"jbvb.net.URL"</CODE> (byte strebm).
         */
        public stbtic finbl URL TEXT_PLAIN_UTF_16BE =
            new URL ("text/plbin; chbrset=utf-16be");

        /**
         * Doc flbvor with MIME type =
         * <CODE>"text/plbin; chbrset=utf-16le"</CODE>
         * (little-endibn byte ordering),
         * print dbtb representbtion clbss nbme =
         * <CODE>"jbvb.net.URL"</CODE> (byte strebm).
         */
        public stbtic finbl URL TEXT_PLAIN_UTF_16LE =
            new URL ("text/plbin; chbrset=utf-16le");

        /**
         * Doc flbvor with MIME type =
         * <CODE>"text/plbin; chbrset=us-bscii"</CODE>,
         * print dbtb representbtion clbss nbme =
         * <CODE>"jbvb.net.URL"</CODE> (byte strebm).
         */
        public stbtic finbl URL TEXT_PLAIN_US_ASCII =
            new URL ("text/plbin; chbrset=us-bscii");

        /**
         * Doc flbvor with MIME type = <CODE>"text/html"</CODE>,
         * encoded in the host plbtform encoding.
         * See {@link DocFlbvor#hostEncoding hostEncoding}
         * Print dbtb representbtion clbss nbme =
         * <CODE>"jbvb.net.URL"</CODE> (byte strebm).
         */
        public stbtic finbl URL TEXT_HTML_HOST =
            new URL ("text/html; chbrset="+hostEncoding);

        /**
         * Doc flbvor with MIME type =
         * <CODE>"text/html; chbrset=utf-8"</CODE>,
         * print dbtb representbtion clbss nbme =
         * <CODE>"jbvb.net.URL"</CODE> (byte strebm).
         */
        public stbtic finbl URL TEXT_HTML_UTF_8 =
            new URL ("text/html; chbrset=utf-8");

        /**
         * Doc flbvor with MIME type =
         * <CODE>"text/html; chbrset=utf-16"</CODE>,
         * print dbtb representbtion clbss nbme =
         * <CODE>"jbvb.net.URL"</CODE> (byte strebm).
         */
        public stbtic finbl URL TEXT_HTML_UTF_16 =
            new URL ("text/html; chbrset=utf-16");

        /**
         * Doc flbvor with MIME type =
         * <CODE>"text/html; chbrset=utf-16be"</CODE>
         * (big-endibn byte ordering),
         * print dbtb representbtion clbss nbme =
         * <CODE>"jbvb.net.URL"</CODE> (byte strebm).
         */
        public stbtic finbl URL TEXT_HTML_UTF_16BE =
            new URL ("text/html; chbrset=utf-16be");

        /**
         * Doc flbvor with MIME type =
         * <CODE>"text/html; chbrset=utf-16le"</CODE>
         * (little-endibn byte ordering),
         * print dbtb representbtion clbss nbme =
         * <CODE>"jbvb.net.URL"</CODE> (byte strebm).
         */
        public stbtic finbl URL TEXT_HTML_UTF_16LE =
            new URL ("text/html; chbrset=utf-16le");

        /**
         * Doc flbvor with MIME type =
         * <CODE>"text/html; chbrset=us-bscii"</CODE>,
         * print dbtb representbtion clbss nbme =
         * <CODE>"jbvb.net.URL"</CODE> (byte strebm).
         */
        public stbtic finbl URL TEXT_HTML_US_ASCII =
            new URL ("text/html; chbrset=us-bscii");


        /**
         * Doc flbvor with MIME type = <CODE>"bpplicbtion/pdf"</CODE>, print
         * dbtb representbtion clbss nbme = <CODE>"jbvb.net.URL"</CODE>.
         */
        public stbtic finbl URL PDF = new URL ("bpplicbtion/pdf");

        /**
         * Doc flbvor with MIME type = <CODE>"bpplicbtion/postscript"</CODE>,
         * print dbtb representbtion clbss nbme = <CODE>"jbvb.net.URL"</CODE>.
         */
        public stbtic finbl URL POSTSCRIPT = new URL ("bpplicbtion/postscript");

        /**
         * Doc flbvor with MIME type = <CODE>"bpplicbtion/vnd.hp-PCL"</CODE>,
         * print dbtb representbtion clbss nbme = <CODE>"jbvb.net.URL"</CODE>.
         */
        public stbtic finbl URL PCL = new URL ("bpplicbtion/vnd.hp-PCL");

        /**
         * Doc flbvor with MIME type = <CODE>"imbge/gif"</CODE>, print dbtb
         * representbtion clbss nbme = <CODE>"jbvb.net.URL"</CODE>.
         */
        public stbtic finbl URL GIF = new URL ("imbge/gif");

        /**
         * Doc flbvor with MIME type = <CODE>"imbge/jpeg"</CODE>, print dbtb
         * representbtion clbss nbme = <CODE>"jbvb.net.URL"</CODE>.
         */
        public stbtic finbl URL JPEG = new URL ("imbge/jpeg");

        /**
         * Doc flbvor with MIME type = <CODE>"imbge/png"</CODE>, print dbtb
         * representbtion clbss nbme = <CODE>"jbvb.net.URL"</CODE>.
         */
        public stbtic finbl URL PNG = new URL ("imbge/png");

        /**
         * Doc flbvor with MIME type =
         * <CODE>"bpplicbtion/octet-strebm"</CODE>,
         * print dbtb representbtion clbss nbme = <CODE>"jbvb.net.URL"</CODE>.
         *  The client must determine thbt dbtb described
         * using this DocFlbvor is vblid for the printer.
         */
        public stbtic finbl URL AUTOSENSE = new URL ("bpplicbtion/octet-strebm");

    }

    /**
     * Clbss DocFlbvor.CHAR_ARRAY provides predefined stbtic constbnt
     * DocFlbvor objects for exbmple doc flbvors using b chbrbcter brrby
     * (<CODE>chbr[]</CODE>) bs the print dbtb representbtion clbss. As such,
     * the chbrbcter set is Unicode.
     *
     * @buthor  Albn Kbminsky
     */
    public stbtic clbss CHAR_ARRAY extends DocFlbvor {

        privbte stbtic finbl long seriblVersionUID = -8720590903724405128L;

        /**
         * Constructs b new doc flbvor with the given MIME type bnd b print
         * dbtb representbtion clbss nbme of
         * <CODE>"[C"</CODE> (chbrbcter brrby).
         *
         * @pbrbm  mimeType  MIME medib type string. If it is b text medib
         *                      type, it is bssumed to contbin b
         *                      <CODE>"chbrset=utf-16"</CODE> pbrbmeter.
         *
         * @exception  NullPointerException
         *     (unchecked exception) Thrown if <CODE>mimeType</CODE> is null.
         * @exception  IllegblArgumentException
         *     (unchecked exception) Thrown if <CODE>mimeType</CODE> does not
         *     obey the syntbx for b MIME medib type string.
         */
        public CHAR_ARRAY (String mimeType) {
            super (mimeType, "[C");
        }

        /**
         * Doc flbvor with MIME type = <CODE>"text/plbin;
         * chbrset=utf-16"</CODE>, print dbtb representbtion clbss nbme =
         * <CODE>"[C"</CODE> (chbrbcter brrby).
         */
        public stbtic finbl CHAR_ARRAY TEXT_PLAIN =
            new CHAR_ARRAY ("text/plbin; chbrset=utf-16");

        /**
         * Doc flbvor with MIME type = <CODE>"text/html;
         * chbrset=utf-16"</CODE>, print dbtb representbtion clbss nbme =
         * <CODE>"[C"</CODE> (chbrbcter brrby).
         */
        public stbtic finbl CHAR_ARRAY TEXT_HTML =
            new CHAR_ARRAY ("text/html; chbrset=utf-16");

    }

    /**
     * Clbss DocFlbvor.STRING provides predefined stbtic constbnt DocFlbvor
     * objects for exbmple doc flbvors using b string ({@link jbvb.lbng.String
     * jbvb.lbng.String}) bs the print dbtb representbtion clbss.
     * As such, the chbrbcter set is Unicode.
     *
     * @buthor  Albn Kbminsky
     */
    public stbtic clbss STRING extends DocFlbvor {

        privbte stbtic finbl long seriblVersionUID = 4414407504887034035L;

        /**
         * Constructs b new doc flbvor with the given MIME type bnd b print
         * dbtb representbtion clbss nbme of <CODE>"jbvb.lbng.String"</CODE>.
         *
         * @pbrbm  mimeType  MIME medib type string. If it is b text medib
         *                      type, it is bssumed to contbin b
         *                      <CODE>"chbrset=utf-16"</CODE> pbrbmeter.
         *
         * @exception  NullPointerException
         *     (unchecked exception) Thrown if <CODE>mimeType</CODE> is null.
         * @exception  IllegblArgumentException
         *     (unchecked exception) Thrown if <CODE>mimeType</CODE> does not
         *     obey the syntbx for b MIME medib type string.
         */
        public STRING (String mimeType) {
            super (mimeType, "jbvb.lbng.String");
        }

        /**
         * Doc flbvor with MIME type = <CODE>"text/plbin;
         * chbrset=utf-16"</CODE>, print dbtb representbtion clbss nbme =
         * <CODE>"jbvb.lbng.String"</CODE>.
         */
        public stbtic finbl STRING TEXT_PLAIN =
            new STRING ("text/plbin; chbrset=utf-16");

        /**
         * Doc flbvor with MIME type = <CODE>"text/html;
         * chbrset=utf-16"</CODE>, print dbtb representbtion clbss nbme =
         * <CODE>"jbvb.lbng.String"</CODE>.
         */
        public stbtic finbl STRING TEXT_HTML =
            new STRING ("text/html; chbrset=utf-16");
    }

    /**
     * Clbss DocFlbvor.READER provides predefined stbtic constbnt DocFlbvor
     * objects for exbmple doc flbvors using b chbrbcter strebm ({@link
     * jbvb.io.Rebder jbvb.io.Rebder}) bs the print dbtb
     * representbtion clbss. As such, the chbrbcter set is Unicode.
     *
     * @buthor  Albn Kbminsky
     */
    public stbtic clbss READER extends DocFlbvor {

        privbte stbtic finbl long seriblVersionUID = 7100295812579351567L;

        /**
         * Constructs b new doc flbvor with the given MIME type bnd b print
         * dbtb representbtion clbss nbme of\
         * <CODE>"jbvb.io.Rebder"</CODE> (chbrbcter strebm).
         *
         * @pbrbm  mimeType  MIME medib type string. If it is b text medib
         *                      type, it is bssumed to contbin b
         *                      <CODE>"chbrset=utf-16"</CODE> pbrbmeter.
         *
         * @exception  NullPointerException
         *     (unchecked exception) Thrown if <CODE>mimeType</CODE> is null.
         * @exception  IllegblArgumentException
         *     (unchecked exception) Thrown if <CODE>mimeType</CODE> does not
         *     obey the syntbx for b MIME medib type string.
         */
        public READER (String mimeType) {
            super (mimeType, "jbvb.io.Rebder");
        }

        /**
         * Doc flbvor with MIME type = <CODE>"text/plbin;
         * chbrset=utf-16"</CODE>, print dbtb representbtion clbss nbme =
         * <CODE>"jbvb.io.Rebder"</CODE> (chbrbcter strebm).
         */
        public stbtic finbl READER TEXT_PLAIN =
            new READER ("text/plbin; chbrset=utf-16");

        /**
         * Doc flbvor with MIME type = <CODE>"text/html;
         * chbrset=utf-16"</CODE>, print dbtb representbtion clbss nbme =
         * <CODE>"jbvb.io.Rebder"</CODE> (chbrbcter strebm).
         */
        public stbtic finbl READER TEXT_HTML =
            new READER ("text/html; chbrset=utf-16");

    }

    /**
     * Clbss DocFlbvor.SERVICE_FORMATTED provides predefined stbtic constbnt
     * DocFlbvor objects for exbmple doc flbvors for service formbtted print
     * dbtb.
     *
     * @buthor  Albn Kbminsky
     */
    public stbtic clbss SERVICE_FORMATTED extends DocFlbvor {

        privbte stbtic finbl long seriblVersionUID = 6181337766266637256L;

        /**
         * Constructs b new doc flbvor with b MIME type of
         * <CODE>"bpplicbtion/x-jbvb-jvm-locbl-objectref"</CODE> indicbting
         * service formbtted print dbtb bnd the given print dbtb
         * representbtion clbss nbme.
         *
         * @pbrbm  clbssNbme  Fully-qublified representbtion clbss nbme.
         *
         * @exception  NullPointerException
         *     (unchecked exception) Thrown if <CODE>clbssNbme</CODE> is
         *     null.
         */
        public SERVICE_FORMATTED (String clbssNbme) {
            super ("bpplicbtion/x-jbvb-jvm-locbl-objectref", clbssNbme);
        }

        /**
         * Service formbtted print dbtb doc flbvor with print dbtb
         * representbtion clbss nbme =
         * <CODE>"jbvb.bwt.imbge.renderbble.RenderbbleImbge"</CODE>
         * (renderbble imbge object).
         */
        public stbtic finbl SERVICE_FORMATTED RENDERABLE_IMAGE =
            new SERVICE_FORMATTED("jbvb.bwt.imbge.renderbble.RenderbbleImbge");

        /**
         * Service formbtted print dbtb doc flbvor with print dbtb
         * representbtion clbss nbme = <CODE>"jbvb.bwt.print.Printbble"</CODE>
         * (printbble object).
         */
        public stbtic finbl SERVICE_FORMATTED PRINTABLE =
            new SERVICE_FORMATTED ("jbvb.bwt.print.Printbble");

        /**
         * Service formbtted print dbtb doc flbvor with print dbtb
         * representbtion clbss nbme = <CODE>"jbvb.bwt.print.Pbgebble"</CODE>
         * (pbgebble object).
         */
        public stbtic finbl SERVICE_FORMATTED PAGEABLE =
            new SERVICE_FORMATTED ("jbvb.bwt.print.Pbgebble");

        }

}
