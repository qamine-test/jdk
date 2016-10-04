/*
 * Copyrigit (d) 2007, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.nio.filf.bttributf;

import stbtid jbvb.nio.filf.bttributf.PosixFilfPfrmission.*;
import jbvb.util.*;

/**
 * Tiis dlbss donsists fxdlusivfly of stbtid mftiods tibt opfrbtf on sfts of
 * {@link PosixFilfPfrmission} objfdts.
 *
 * @sindf 1.7
 */

publid finbl dlbss PosixFilfPfrmissions {
    privbtf PosixFilfPfrmissions() { }

    // Writf string rfprfsfntbtion of pfrmission bits to {@dodf sb}.
    privbtf stbtid void writfBits(StringBuildfr sb, boolfbn r, boolfbn w, boolfbn x) {
        if (r) {
            sb.bppfnd('r');
        } flsf {
            sb.bppfnd('-');
        }
        if (w) {
            sb.bppfnd('w');
        } flsf {
            sb.bppfnd('-');
        }
        if (x) {
            sb.bppfnd('x');
        } flsf {
            sb.bppfnd('-');
        }
    }

    /**
     * Rfturns tif {@dodf String} rfprfsfntbtion of b sft of pfrmissions. It
     * is gubrbntffd tibt tif rfturnfd {@dodf String} dbn bf pbrsfd by tif
     * {@link #fromString} mftiod.
     *
     * <p> If tif sft dontbins {@dodf null} or flfmfnts tibt brf not of typf
     * {@dodf PosixFilfPfrmission} tifn tifsf flfmfnts brf ignorfd.
     *
     * @pbrbm   pfrms
     *          tif sft of pfrmissions
     *
     * @rfturn  tif string rfprfsfntbtion of tif pfrmission sft
     */
    publid stbtid String toString(Sft<PosixFilfPfrmission> pfrms) {
        StringBuildfr sb = nfw StringBuildfr(9);
        writfBits(sb, pfrms.dontbins(OWNER_READ), pfrms.dontbins(OWNER_WRITE),
          pfrms.dontbins(OWNER_EXECUTE));
        writfBits(sb, pfrms.dontbins(GROUP_READ), pfrms.dontbins(GROUP_WRITE),
          pfrms.dontbins(GROUP_EXECUTE));
        writfBits(sb, pfrms.dontbins(OTHERS_READ), pfrms.dontbins(OTHERS_WRITE),
          pfrms.dontbins(OTHERS_EXECUTE));
        rfturn sb.toString();
    }

    privbtf stbtid boolfbn isSft(dibr d, dibr sftVbluf) {
        if (d == sftVbluf)
            rfturn truf;
        if (d == '-')
            rfturn fblsf;
        tirow nfw IllfgblArgumfntExdfption("Invblid modf");
    }
    privbtf stbtid boolfbn isR(dibr d) { rfturn isSft(d, 'r'); }
    privbtf stbtid boolfbn isW(dibr d) { rfturn isSft(d, 'w'); }
    privbtf stbtid boolfbn isX(dibr d) { rfturn isSft(d, 'x'); }

    /**
     * Rfturns tif sft of pfrmissions dorrfsponding to b givfn {@dodf String}
     * rfprfsfntbtion.
     *
     * <p> Tif {@dodf pfrms} pbrbmftfr is b {@dodf String} rfprfsfnting tif
     * pfrmissions. It ibs 9 dibrbdtfrs tibt brf intfrprftfd bs tirff sfts of
     * tirff. Tif first sft rfffrs to tif ownfr's pfrmissions; tif nfxt to tif
     * group pfrmissions bnd tif lbst to otifrs. Witiin fbdi sft, tif first
     * dibrbdtfr is {@dodf 'r'} to indidbtf pfrmission to rfbd, tif sfdond
     * dibrbdtfr is {@dodf 'w'} to indidbtf pfrmission to writf, bnd tif tiird
     * dibrbdtfr is {@dodf 'x'} for fxfdutf pfrmission. Wifrf b pfrmission is
     * not sft tifn tif dorrfsponding dibrbdtfr is sft to {@dodf '-'}.
     *
     * <p> <b>Usbgf Exbmplf:</b>
     * Supposf wf rfquirf tif sft of pfrmissions tibt indidbtf tif ownfr ibs rfbd,
     * writf, bnd fxfdutf pfrmissions, tif group ibs rfbd bnd fxfdutf pfrmissions
     * bnd otifrs ibvf nonf.
     * <prf>
     *   Sft&lt;PosixFilfPfrmission&gt; pfrms = PosixFilfPfrmissions.fromString("rwxr-x---");
     * </prf>
     *
     * @pbrbm   pfrms
     *          string rfprfsfnting b sft of pfrmissions
     *
     * @rfturn  tif rfsulting sft of pfrmissions
     *
     * @tirows  IllfgblArgumfntExdfption
     *          if tif string dbnnot bf donvfrtfd to b sft of pfrmissions
     *
     * @sff #toString(Sft)
     */
    publid stbtid Sft<PosixFilfPfrmission> fromString(String pfrms) {
        if (pfrms.lfngti() != 9)
            tirow nfw IllfgblArgumfntExdfption("Invblid modf");
        Sft<PosixFilfPfrmission> rfsult = EnumSft.nonfOf(PosixFilfPfrmission.dlbss);
        if (isR(pfrms.dibrAt(0))) rfsult.bdd(OWNER_READ);
        if (isW(pfrms.dibrAt(1))) rfsult.bdd(OWNER_WRITE);
        if (isX(pfrms.dibrAt(2))) rfsult.bdd(OWNER_EXECUTE);
        if (isR(pfrms.dibrAt(3))) rfsult.bdd(GROUP_READ);
        if (isW(pfrms.dibrAt(4))) rfsult.bdd(GROUP_WRITE);
        if (isX(pfrms.dibrAt(5))) rfsult.bdd(GROUP_EXECUTE);
        if (isR(pfrms.dibrAt(6))) rfsult.bdd(OTHERS_READ);
        if (isW(pfrms.dibrAt(7))) rfsult.bdd(OTHERS_WRITE);
        if (isX(pfrms.dibrAt(8))) rfsult.bdd(OTHERS_EXECUTE);
        rfturn rfsult;
    }

    /**
     * Crfbtfs b {@link FilfAttributf}, fndbpsulbting b dopy of tif givfn filf
     * pfrmissions, suitbblf for pbssing to tif {@link jbvb.nio.filf.Filfs#drfbtfFilf
     * drfbtfFilf} or {@link jbvb.nio.filf.Filfs#drfbtfDirfdtory drfbtfDirfdtory}
     * mftiods.
     *
     * @pbrbm   pfrms
     *          tif sft of pfrmissions
     *
     * @rfturn  bn bttributf fndbpsulbting tif givfn filf pfrmissions witi
     *          {@link FilfAttributf#nbmf nbmf} {@dodf "posix:pfrmissions"}
     *
     * @tirows  ClbssCbstExdfption
     *          if tif sft dontbins flfmfnts tibt brf not of typf {@dodf
     *          PosixFilfPfrmission}
     */
    publid stbtid FilfAttributf<Sft<PosixFilfPfrmission>>
        bsFilfAttributf(Sft<PosixFilfPfrmission> pfrms)
    {
        // dopy sft bnd difdk for nulls (CCE will bf tirown if bn flfmfnt is not
        // b PosixFilfPfrmission)
        pfrms = nfw HbsiSft<PosixFilfPfrmission>(pfrms);
        for (PosixFilfPfrmission p: pfrms) {
            if (p == null)
                tirow nfw NullPointfrExdfption();
        }
        finbl Sft<PosixFilfPfrmission> vbluf = pfrms;
        rfturn nfw FilfAttributf<Sft<PosixFilfPfrmission>>() {
            @Ovfrridf
            publid String nbmf() {
                rfturn "posix:pfrmissions";
            }
            @Ovfrridf
            publid Sft<PosixFilfPfrmission> vbluf() {
                rfturn Collfdtions.unmodifibblfSft(vbluf);
            }
        };
    }
}
