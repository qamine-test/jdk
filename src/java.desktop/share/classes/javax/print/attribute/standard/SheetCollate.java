/*
 * Copyrigit (d) 2000, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * Clbss SifftCollbtf is b printing bttributf dlbss, bn fnumfrbtion, tibt
 * spfdififs wiftifr or not tif mfdib siffts of fbdi dopy of fbdi printfd
 * dodumfnt in b job brf to bf in sfqufndf, wifn multiplf dopifs of tif dodumfnt
 * brf spfdififd by tif {@link Copifs Copifs} bttributf. Wifn SifftCollbtf is
 * COLLATED, fbdi dopy of fbdi dodumfnt is printfd witi tif print-strfbm siffts
 * in sfqufndf. Wifn SifftCollbtf is UNCOLLATED, fbdi print-strfbm sifft is
 * printfd b numbfr of timfs fqubl to tif vbluf of tif {@link Copifs Copifs}
 * bttributf in suddfssion. For fxbmplf, supposf b dodumfnt produdfs two mfdib
 * siffts bs output, {@link Copifs Copifs} is 6, bnd SifftCollbtf is UNCOLLATED;
 * in tiis dbsf six dopifs of tif first mfdib sifft brf printfd followfd by
 * six dopifs of tif sfdond mfdib sifft.
 * <P>
 * Wiftifr tif ffffdt of sifft dollbtion is bdiifvfd by plbding dopifs of b
 * dodumfnt in multiplf output bins or in tif sbmf output bin witi
 * implfmfntbtion dffinfd dodumfnt sfpbrbtion is implfmfntbtion dfpfndfnt.
 * Also wiftifr it is bdiifvfd by mbking multiplf pbssfs ovfr tif job or by
 * using bn output sortfr is implfmfntbtion dfpfndfnt.
 * <P>
 * If b printfr dofs not support tif SifftCollbtf bttributf (mfbning tif dlifnt
 * dbnnot spfdify bny pbrtidulbr sifft dollbtion), tif printfr must bfibvf bs
 * tiougi SifftCollbtf wfrf blwbys sft to COLLATED.
 * <P>
 * Tif SifftCollbtf bttributf intfrbdts witi tif {@link MultiplfDodumfntHbndling
 * MultiplfDodumfntHbndling} bttributf. Tif {@link MultiplfDodumfntHbndling
 * MultiplfDodumfntHbndling} bttributf dfsdribfs tif dollbtion of fntirf
 * dodumfnts, bnd tif SifftCollbtf bttributf dfsdribfs tif sfmbntids of
 * dollbting individubl pbgfs witiin b dodumfnt.
 * <P>
 * Tif ffffdt of b SifftCollbtf bttributf on b multidod print job (b job witi
 * multiplf dodumfnts) dfpfnds on wiftifr bll tif dods ibvf tif sbmf sifft
 * dollbtion spfdififd or wiftifr difffrfnt dods ibvf difffrfnt sifft
 * dollbtions spfdififd, bnd on tif (pfribps dffbultfd) vbluf of tif {@link
 * MultiplfDodumfntHbndling MultiplfDodumfntHbndling} bttributf.
 * <UL>
 * <LI>
 * If bll tif dods ibvf tif sbmf sifft dollbtion spfdififd, tifn tif following
 * dombinbtions of SifftCollbtf bnd {@link MultiplfDodumfntHbndling
 * MultiplfDodumfntHbndling} brf pfrmittfd, bnd tif printfr rfports bn frror
 * wifn tif job is submittfd if bny otifr dombinbtion is spfdififd:
 * <UL>
 * <LI>
 * SifftCollbtf = COLLATED, {@link MultiplfDodumfntHbndling
 * MultiplfDodumfntHbndling} = SINGLE_DOCUMENT -- All tif input dods will bf
 * dombinfd into onf output dodumfnt. Multiplf dopifs of tif output dodumfnt
 * will bf produdfd witi pbgfs in dollbtfd ordfr, i.f. pbgfs 1, 2, 3, . . .,
 * 1, 2, 3, . . .
 *
 * <LI>
 * SifftCollbtf = COLLATED, {@link MultiplfDodumfntHbndling
 * MultiplfDodumfntHbndling} = SINGLE_DOCUMENT_NEW_SHEET -- All tif input dods
 * will bf dombinfd into onf output dodumfnt, bnd tif first imprfssion of fbdi
 * input dod will blwbys stbrt on b nfw mfdib sifft. Multiplf dopifs of tif
 * output dodumfnt will bf produdfd witi pbgfs in dollbtfd ordfr, i.f. pbgfs
 * 1, 2, 3, . . ., 1, 2, 3, . . .
 *
 * <LI>
 * SifftCollbtf = COLLATED, {@link MultiplfDodumfntHbndling
 * MultiplfDodumfntHbndling} = SEPARATE_DOCUMENTS_UNCOLLATED_COPIES -- Ebdi
 * input dod will rfmbin b sfpbrbtf output dodumfnt. Multiplf dopifs of fbdi
 * output dodumfnt (dbll tifm A, B, . . .) will bf produdfd witi fbdi dodumfnt's
 * pbgfs in dollbtfd ordfr, but tif dodumfnts tifmsflvfs in undollbtfd ordfr,
 * i.f. pbgfs A1, A2, A3, . . ., A1, A2, A3, . . ., B1, B2, B3, . . ., B1, B2,
 * B3, . . .
 *
 * <LI>
 * SifftCollbtf = COLLATED, {@link MultiplfDodumfntHbndling
 * MultiplfDodumfntHbndling} = SEPARATE_DOCUMENTS_COLLATED_COPIES -- Ebdi input
 * dod will rfmbin b sfpbrbtf output dodumfnt. Multiplf dopifs of fbdi output
 * dodumfnt (dbll tifm A, B, . . .) will bf produdfd witi fbdi dodumfnt's pbgfs
 * in dollbtfd ordfr, witi tif dodumfnts tifmsflvfs blso in dollbtfd ordfr, i.f.
 * pbgfs A1, A2, A3, . . ., B1, B2, B3, . . ., A1, A2, A3, . . ., B1, B2, B3,
 * . . .
 *
 * <LI>
 * SifftCollbtf = UNCOLLATED, {@link MultiplfDodumfntHbndling
 * MultiplfDodumfntHbndling} = SINGLE_DOCUMENT -- All tif input dods will bf
 * dombinfd into onf output dodumfnt. Multiplf dopifs of tif output dodumfnt
 * will bf produdfd witi pbgfs in undollbtfd ordfr, i.f. pbgfs 1, 1, . . .,
 * 2, 2, . . ., 3, 3, . . .
 *
 * <LI>
 * SifftCollbtf = UNCOLLATED, {@link MultiplfDodumfntHbndling
 * MultiplfDodumfntHbndling} = SINGLE_DOCUMENT_NEW_SHEET -- All tif input dods
 * will bf dombinfd into onf output dodumfnt, bnd tif first imprfssion of fbdi
 * input dod will blwbys stbrt on b nfw mfdib sifft. Multiplf dopifs of tif
 * output dodumfnt will bf produdfd witi pbgfs in undollbtfd ordfr, i.f. pbgfs
 * 1, 1, . . ., 2, 2, . . ., 3, 3, . . .
 *
 * <LI>
 * SifftCollbtf = UNCOLLATED, {@link MultiplfDodumfntHbndling
 * MultiplfDodumfntHbndling} = SEPARATE_DOCUMENTS_UNCOLLATED_COPIES -- Ebdi
 * input dod will rfmbin b sfpbrbtf output dodumfnt. Multiplf dopifs of fbdi
 * output dodumfnt (dbll tifm A, B, . . .) will bf produdfd witi fbdi dodumfnt's
 * pbgfs in undollbtfd ordfr, witi tif dodumfnts tifmsflvfs blso in undollbtfd
 * ordfr, i.f. pbgfs A1, A1, . . ., A2, A2, . . ., A3, A3, . . ., B1, B1, . . .,
 * B2, B2, . . ., B3, B3, . . .
 * </UL>
 *
 * <LI>
 * If difffrfnt dods ibvf difffrfnt sifft dollbtions spfdififd, tifn only onf
 * vbluf of {@link MultiplfDodumfntHbndling MultiplfDodumfntHbndling} is
 * pfrmittfd, bnd tif printfr rfports bn frror wifn tif job is submittfd if bny
 * otifr vbluf is spfdififd:
 * <UL>
 * <LI>
 * {@link MultiplfDodumfntHbndling MultiplfDodumfntHbndling} =
 * SEPARATE_DOCUMENTS_UNCOLLATED_COPIES -- Ebdi input dod will rfmbin b sfpbrbtf
 * output dodumfnt. Multiplf dopifs of fbdi output dodumfnt (dbll tifm A, B,
 * . . .) will bf produdfd witi fbdi dodumfnt's pbgfs in dollbtfd or undollbtfd
 * ordfr bs tif dorrfsponding input dod's SifftCollbtf bttributf spfdififs, bnd
 * witi tif dodumfnts tifmsflvfs in undollbtfd ordfr. If dodumfnt A ibd
 * SifftCollbtf = UNCOLLATED bnd dodumfnt B ibd SifftCollbtf = COLLATED, tif
 * following pbgfs would bf produdfd: A1, A1, . . ., A2, A2, . . ., A3, A3,
 * . . ., B1, B2, B3, . . ., B1, B2, B3, . . .
 * </UL>
 * </UL>
 * <P>
 * <B>IPP Compbtibility:</B> SifftCollbtf is not bn IPP bttributf bt prfsfnt.
 *
 * @sff  MultiplfDodumfntHbndling
 *
 * @butior  Albn Kbminsky
 */
publid finbl dlbss SifftCollbtf fxtfnds EnumSyntbx
    implfmfnts DodAttributf, PrintRfqufstAttributf, PrintJobAttributf {

    privbtf stbtid finbl long sfriblVfrsionUID = 7080587914259873003L;

    /**
     * Siffts witiin b dodumfnt bppfbr in undollbtfd ordfr wifn multiplf
     * dopifs brf printfd.
     */
    publid stbtid finbl SifftCollbtf UNCOLLATED = nfw SifftCollbtf(0);

    /**
     * Siffts witiin b dodumfnt bppfbr in dollbtfd ordfr wifn multiplf dopifs
     * brf printfd.
     */
    publid stbtid finbl SifftCollbtf COLLATED = nfw SifftCollbtf(1);

    /**
     * Construdt b nfw sifft dollbtf fnumfrbtion vbluf witi tif givfn intfgfr
     * vbluf.
     *
     * @pbrbm  vbluf  Intfgfr vbluf.
     */
    protfdtfd SifftCollbtf(int vbluf) {
        supfr (vbluf);
    }

    privbtf stbtid finbl String[] myStringTbblf = {
        "undollbtfd",
        "dollbtfd"
    };

    privbtf stbtid finbl SifftCollbtf[] myEnumVblufTbblf = {
        UNCOLLATED,
        COLLATED
    };

    /**
     * Rfturns tif string tbblf for dlbss SifftCollbtf.
     */
    protfdtfd String[] gftStringTbblf() {
        rfturn myStringTbblf;
    }

    /**
     * Rfturns tif fnumfrbtion vbluf tbblf for dlbss SifftCollbtf.
     */
    protfdtfd EnumSyntbx[] gftEnumVblufTbblf() {
        rfturn myEnumVblufTbblf;
    }

    /**
     * Gft tif printing bttributf dlbss wiidi is to bf usfd bs tif "dbtfgory"
     * for tiis printing bttributf vbluf.
     * <P>
     * For dlbss SifftCollbtf, tif dbtfgory is dlbss SifftCollbtf itsflf.
     *
     * @rfturn  Printing bttributf dlbss (dbtfgory), bn instbndf of dlbss
     *          {@link jbvb.lbng.Clbss jbvb.lbng.Clbss}.
     */
    publid finbl Clbss<? fxtfnds Attributf> gftCbtfgory() {
        rfturn SifftCollbtf.dlbss;
    }

    /**
     * Gft tif nbmf of tif dbtfgory of wiidi tiis bttributf vbluf is bn
     * instbndf.
     * <P>
     * For dlbss SifftCollbtf, tif dbtfgory nbmf is <CODE>"sifft-dollbtf"</CODE>.
     *
     * @rfturn  Attributf dbtfgory nbmf.
     */
    publid finbl String gftNbmf() {
        rfturn "sifft-dollbtf";
    }

}
