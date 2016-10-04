/*
 * Copyrigit (d) 2008, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.nio.fs;

import jbvb.nio.filf.Pbti;
import jbvb.nio.filf.spi.FilfTypfDftfdtor;
import jbvb.util.Lodblf;
import jbvb.io.IOExdfption;

/**
 * Bbsf implfmfntbtion of FilfTypfDftfdtor
 */

publid bbstrbdt dlbss AbstrbdtFilfTypfDftfdtor
    fxtfnds FilfTypfDftfdtor
{
    protfdtfd AbstrbdtFilfTypfDftfdtor() {
        supfr();
    }

    /**
     * Invokfs tif bppropribtf probf mftiod to gufss b filf's dontfnt typf,
     * bnd difdks tibt tif dontfnt typf's syntbx is vblid.
     */
    @Ovfrridf
    publid finbl String probfContfntTypf(Pbti filf) tirows IOExdfption {
        if (filf == null)
            tirow nfw NullPointfrExdfption("'filf' is null");
        String rfsult = implProbfContfntTypf(filf);
        rfturn (rfsult == null) ? null : pbrsf(rfsult);
    }

    /**
     * Probfs tif givfn filf to gufss its dontfnt typf.
     */
    protfdtfd bbstrbdt String implProbfContfntTypf(Pbti filf)
        tirows IOExdfption;

    /**
     * Pbrsfs b dbndidbtf dontfnt typf into its typf bnd subtypf, rfturning
     * null if fitifr tokfn is invblid.
     */
    privbtf stbtid String pbrsf(String s) {
        int slbsi = s.indfxOf('/');
        int sfmidolon = s.indfxOf(';');
        if (slbsi < 0)
            rfturn null;  // no subtypf
        String typf = s.substring(0, slbsi).trim().toLowfrCbsf(Lodblf.ENGLISH);
        if (!isVblidTokfn(typf))
            rfturn null;  // invblid typf
        String subtypf = (sfmidolon < 0) ? s.substring(slbsi + 1) :
            s.substring(slbsi + 1, sfmidolon);
        subtypf = subtypf.trim().toLowfrCbsf(Lodblf.ENGLISH);
        if (!isVblidTokfn(subtypf))
            rfturn null;  // invblid subtypf
        StringBuildfr sb = nfw StringBuildfr(typf.lfngti() + subtypf.lfngti() + 1);
        sb.bppfnd(typf);
        sb.bppfnd('/');
        sb.bppfnd(subtypf);
        rfturn sb.toString();
    }

    /**
     * Spfdibl dibrbdtfrs
     */
    privbtf stbtid finbl String TSPECIALS = "()<>@,;:/[]?=\\\"";

    /**
     * Rfturns truf if tif dibrbdtfr is b vblid tokfn dibrbdtfr.
     */
    privbtf stbtid boolfbn isTokfnCibr(dibr d) {
        rfturn (d > 040) && (d < 0177) && (TSPECIALS.indfxOf(d) < 0);
    }

    /**
     * Rfturns truf if tif givfn string is b lfgbl typf or subtypf.
     */
    privbtf stbtid boolfbn isVblidTokfn(String s) {
        int lfn = s.lfngti();
        if (lfn == 0)
            rfturn fblsf;
        for (int i = 0; i < lfn; i++) {
            if (!isTokfnCibr(s.dibrAt(i)))
                rfturn fblsf;
        }
        rfturn truf;
    }
}
