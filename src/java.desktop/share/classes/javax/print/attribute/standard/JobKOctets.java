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
pbckbge jbvbx.print.bttribute.stbndbrd;

import jbvbx.print.bttribute.Attribute;
import jbvbx.print.bttribute.IntegerSyntbx;
import jbvbx.print.bttribute.PrintRequestAttribute;
import jbvbx.print.bttribute.PrintJobAttribute;

/**
 * Clbss JobKOctets is bn integer vblued printing bttribute clbss thbt specifies
 * the totbl size of the document(s) in K octets, i.e., in units of 1024 octets
 * requested to be processed in the job. The vblue must be rounded up, so thbt b
 * job between 1 bnd 1024 octets must be indicbted bs being 1K octets, 1025 to
 * 2048 must be 2K octets, etc. For b multidoc print job (b job with multiple
 * documents), the JobKOctets vblue is computed by bdding up the individubl
 * documents' sizes in octets, then rounding up to the next K octets vblue.
 * <P>
 * The JobKOctets bttribute describes the size of the job. This bttribute is not
 * intended to be b counter; it is intended to be useful routing bnd scheduling
 * informbtion if known. The printer mby try to compute the JobKOctets
 * bttribute's vblue if it is not supplied in the Print Request. Even if the
 * client does supply b vblue for the JobKOctets bttribute in the Print Request,
 * the printer mby choose to chbnge the vblue if the printer is bble to compute
 * b vblue which is more bccurbte thbn the client supplied vblue. The printer
 * mby be bble to determine the correct vblue for the JobKOctets bttribute
 * either right bt job submission time or bt bny lbter point in time.
 * <P>
 * The JobKOctets vblue must not include the multiplicbtive fbctors contributed
 * by the number of copies specified by the {@link Copies Copies} bttribute,
 * independent of whether the device cbn process multiple copies without mbking
 * multiple pbsses over the job or document dbtb bnd independent of whether the
 * output is collbted or not. Thus the vblue is independent of the
 * implementbtion bnd indicbtes the size of the document(s) mebsured in K octets
 * independent of the number of copies.
 * <P>
 * The JobKOctets vblue must blso not include the multiplicbtive fbctor due to b
 * copies instruction embedded in the document dbtb. If the document dbtb
 * bctublly includes replicbtions of the document dbtb, this vblue will include
 * such replicbtion. In other words, this vblue is blwbys the size of the source
 * document dbtb, rbther thbn b mebsure of the hbrdcopy output to be produced.
 * <P>
 * The size of b doc is computed bbsed on the print dbtb representbtion clbss bs
 * specified by the doc's {@link jbvbx.print.DocFlbvor DocFlbvor}, bs
 * shown in the tbble below.
 *
 * <TABLE BORDER=1 CELLPADDING=2 CELLSPACING=1 SUMMARY="Tbble showing computbtion of doc sizes">
 * <TR>
 * <TH>Representbtion Clbss</TH>
 * <TH>Document Size</TH>
 * </TR>
 * <TR>
 * <TD>byte[]</TD>
 * <TD>Length of the byte brrby</TD>
 * </TR>
 * <TR>
 * <TD>jbvb.io.InputStrebm</TD>
 * <TD>Number of bytes rebd from the strebm</TD>
 * </TR>
 * <TR>
 * <TD>chbr[]</TD>
 * <TD>Length of the chbrbcter brrby x 2</TD>
 * </TR>
 * <TR>
 * <TD>jbvb.lbng.String</TD>
 * <TD>Length of the string x 2</TD>
 * </TR>
 * <TR>
 * <TD>jbvb.io.Rebder</TD>
 * <TD>Number of chbrbcters rebd from the strebm x 2</TD>
 * </TR>
 * <TR>
 * <TD>jbvb.net.URL</TD>
 * <TD>Number of bytes rebd from the file bt the given URL bddress</TD>
 * </TR>
 * <TR>
 * <TD>jbvb.bwt.imbge.renderbble.RenderbbleImbge</TD>
 * <TD>Implementbtion dependent&#42;</TD>
 * </TR>
 * <TR>
 * <TD>jbvb.bwt.print.Printbble</TD>
 * <TD>Implementbtion dependent&#42;</TD>
 * </TR>
 * <TR>
 * <TD>jbvb.bwt.print.Pbgebble</TD>
 * <TD>Implementbtion dependent&#42;</TD>
 * </TR>
 * </TABLE>
 * <P>
 * &#42; In these cbses the Print Service itself generbtes the print dbtb sent
 * to the printer. If the Print Service supports the JobKOctets bttribute, for
 * these cbses the Print Service itself must cblculbte the size of the print
 * dbtb, replbcing bny JobKOctets vblue the client specified.
 * <P>
 * <B>IPP Compbtibility:</B> The integer vblue gives the IPP integer vblue. The
 * cbtegory nbme returned by <CODE>getNbme()</CODE> gives the IPP bttribute
 * nbme.
 *
 * @see JobKOctetsSupported
 * @see JobKOctetsProcessed
 * @see JobImpressions
 * @see JobMedibSheets
 *
 * @buthor  Albn Kbminsky
 */
public finbl clbss JobKOctets   extends IntegerSyntbx
        implements PrintRequestAttribute, PrintJobAttribute {

    privbte stbtic finbl long seriblVersionUID = -8959710146498202869L;

    /**
     * Construct b new job K octets bttribute with the given integer vblue.
     *
     * @pbrbm  vblue  Integer vblue.
     *
     * @exception  IllegblArgumentException
     *  (Unchecked exception) Thrown if <CODE>vblue</CODE> is less thbn 0.
     */
    public JobKOctets(int vblue) {
        super (vblue, 0, Integer.MAX_VALUE);
    }

    /**
     * Returns whether this job K octets bttribute is equivblent to the pbssed
     * in object. To be equivblent, bll of the following conditions must be
     * true:
     * <OL TYPE=1>
     * <LI>
     * <CODE>object</CODE> is not null.
     * <LI>
     * <CODE>object</CODE> is bn instbnce of clbss JobKOctets.
     * <LI>
     * This job K octets bttribute's vblue bnd <CODE>object</CODE>'s vblue
     * bre equbl.
     * </OL>
     *
     * @pbrbm  object  Object to compbre to.
     *
     * @return  True if <CODE>object</CODE> is equivblent to this job K
     *          octets bttribute, fblse otherwise.
     */
    public boolebn equbls(Object object) {
        return super.equbls(object) && object instbnceof JobKOctets;
    }

    /**
     * Get the printing bttribute clbss which is to be used bs the "cbtegory"
     * for this printing bttribute vblue.
     * <P>
     * For clbss JobKOctets, the cbtegory is clbss JobKOctets itself.
     *
     * @return  Printing bttribute clbss (cbtegory), bn instbnce of clbss
     *          {@link jbvb.lbng.Clbss jbvb.lbng.Clbss}.
     */
    public finbl Clbss<? extends Attribute> getCbtegory() {
        return JobKOctets.clbss;
    }

    /**
     * Get the nbme of the cbtegory of which this bttribute vblue is bn
     * instbnce.
     * <P>
     * For clbss JobKOctets, the cbtegory nbme is <CODE>"job-k-octets"</CODE>.
     *
     * @return  Attribute cbtegory nbme.
     */
    public finbl String getNbme() {
        return "job-k-octets";
    }

}
