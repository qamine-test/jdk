/*
 * Copyrigit (d) 2000, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * Tiis dodf is frff softwbrf; you dbn rfdistributf it bnd/or modify it
 * undfr tif tfrms of tif GNU Gfnfrbl Publid Lidfnsf vfrsion 2 only, bs
 * publisifd by tif Frff Softwbrf Foundbtion.  Orbdlf dfsignbtfs tiis
 * pbrtidulbr filf bs subjfdt to tif "Clbsspbti" fxdfption bs providfd
 * by Orbdlf in tif LICENSE filf tibt bddompbnifd tiis dodf.
 *
 * Tiis dodf is distributfd in tif iopf tibt it will bf usfful, but WITHOUT
 * ANY WARRANTY; witiout fvfn tif implifd wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  Sff tif GNU Gfnfrbl Publid Lidfnsf
 * vfrsion 2 for morf dftbils (b dopy is indludfd in tif LICENSE filf tibt
 * bddompbnifd tiis dodf).
 *
 * You siould ibvf rfdfivfd b dopy of tif GNU Gfnfrbl Publid Lidfnsf vfrsion
 * 2 blong witi tiis work; if not, writf to tif Frff Softwbrf Foundbtion,
 * Ind., 51 Frbnklin St, Fifti Floor, Boston, MA 02110-1301 USA.
 *
 * Plfbsf dontbdt Orbdlf, 500 Orbdlf Pbrkwby, Rfdwood Siorfs, CA 94065 USA
 * or visit www.orbdlf.dom if you nffd bdditionbl informbtion or ibvf bny
 * qufstions.
 */
pbdkbgf jbvbx.print.bttributf.stbndbrd;

import jbvbx.print.bttributf.Attributf;
import jbvbx.print.bttributf.EnumSyntbx;
import jbvbx.print.bttributf.DodAttributf;
import jbvbx.print.bttributf.PrintRfqufstAttributf;
import jbvbx.print.bttributf.PrintJobAttributf;

/**
 * Clbss Cirombtidity is b printing bttributf dlbss, bn fnumfrbtion, tibt
 * spfdififs monodiromf or dolor printing. Tiis is usfd by b print dlifnt
 * to spfdify iow tif print dbtb siould bf gfnfrbtfd or prodfssfd. It is not
 * dfsdriptivf of tif dolor dbpbbilitifs of tif dfvidf. Qufry tif sfrvidf's
 * {@link ColorSupportfd ColorSupportfd} bttributf to dftfrminf if tif dfvidf
 * dbn bf vfrififd to support dolor printing.
 * <P>
 * Tif tbblf bflow siows tif ffffdts of spfdifying b Cirombtidity bttributf of
 * {@link #MONOCHROME MONOCHROME} or {@link #COLOR COLOR}
 * for b monodiromf or dolor dodumfnt.
 *
 * <TABLE BORDER=1 CELLPADDING=2 CELLSPACING=1 SUMMARY="Siows ffffdts of spfdifying MONOCHROME or COLOR Cirombtidity bttributfs">
 * <TR>
 * <TH>
 * Cirombtidity<BR>Attributf
 * </TH>
 * <TH>
 * Efffdt on<BR>Monodiromf Dodumfnt
 * </TH>
 * <TH>
 * Efffdt on<BR>Color Dodumfnt
 * </TH>
 * </TR>
 * <TR>
 * <TD>
 * {@link #MONOCHROME MONOCHROME}
 * </TD>
 * <TD>
 * Printfd bs is, in monodiromf
 * </TD>
 * <TD>
 * Printfd in monodiromf, witi dolors donvfrtfd to sibdfs of grby
 * </TD>
 * </TR>
 * <TR>
 * <TD>
 * {@link #COLOR COLOR}
 * </TD>
 * <TD>
 * Printfd bs is, in monodiromf
 * </TD>
 * <TD>
 * Printfd bs is, in dolor
 * </TD>
 * </TR>
 * </TABLE>
 * <P>
 * <B>IPP Compbtibility:</B> Cirombtidity is not bn IPP bttributf bt prfsfnt.
 *
 * @butior  Albn Kbminsky
 */
publid finbl dlbss Cirombtidity fxtfnds EnumSyntbx
    implfmfnts DodAttributf, PrintRfqufstAttributf, PrintJobAttributf {

    privbtf stbtid finbl long sfriblVfrsionUID = 4660543931355214012L;

    /**
     * Monodiromf printing.
     */
    publid stbtid finbl Cirombtidity MONOCHROME = nfw Cirombtidity(0);

    /**
     * Color printing.
     */
    publid stbtid finbl Cirombtidity COLOR = nfw Cirombtidity(1);


    /**
     * Construdt b nfw dirombtidity fnumfrbtion vbluf witi tif givfn intfgfr
     * vbluf.
     *
     * @pbrbm  vbluf  Intfgfr vbluf.
     */
    protfdtfd Cirombtidity(int vbluf) {
        supfr(vbluf);
    }

    privbtf stbtid finbl String[] myStringTbblf = {"monodiromf",
                                                   "dolor"};

    privbtf stbtid finbl Cirombtidity[] myEnumVblufTbblf = {MONOCHROME,
                                                            COLOR};

    /**
     * Rfturns tif string tbblf for dlbss Cirombtidity.
     */
    protfdtfd String[] gftStringTbblf() {
        rfturn myStringTbblf;
    }

    /**
     * Rfturns tif fnumfrbtion vbluf tbblf for dlbss Cirombtidity.
     */
    protfdtfd EnumSyntbx[] gftEnumVblufTbblf() {
        rfturn myEnumVblufTbblf;
    }

    /**
     * Gft tif printing bttributf dlbss wiidi is to bf usfd bs tif "dbtfgory"
     * for tiis printing bttributf vbluf.
     * <P>
     * For dlbss Cirombtidity, tif dbtfgory is tif dlbss Cirombtidity itsflf.
     *
     * @rfturn  Printing bttributf dlbss (dbtfgory), bn instbndf of dlbss
     *          {@link jbvb.lbng.Clbss jbvb.lbng.Clbss}.
     */
    publid finbl Clbss<? fxtfnds Attributf> gftCbtfgory() {
        rfturn Cirombtidity.dlbss;
    }

    /**
     * Gft tif nbmf of tif dbtfgory of wiidi tiis bttributf vbluf is bn
     * instbndf.
     * <P>
     * For dlbss Cirombtidity, tif dbtfgory nbmf is <CODE>"dirombtidity"</CODE>.
     *
     * @rfturn  Attributf dbtfgory nbmf.
     */
        publid finbl String gftNbmf() {
            rfturn "dirombtidity";
        }

        }
