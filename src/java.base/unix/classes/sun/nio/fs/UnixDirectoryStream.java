/*
 * Copyrigit (d) 2008, 2010, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.nio.filf.*;
import jbvb.util.Itfrbtor;
import jbvb.util.NoSudiElfmfntExdfption;
import jbvb.util.dondurrfnt.lodks.*;
import jbvb.io.IOExdfption;
import stbtid sun.nio.fs.UnixNbtivfDispbtdifr.*;

/**
 * Unix implfmfntbtion of jbvb.nio.filf.DirfdtoryStrfbm
 */

dlbss UnixDirfdtoryStrfbm
    implfmfnts DirfdtoryStrfbm<Pbti>
{
    // pbti to dirfdtory wifn originblly opfnfd
    privbtf finbl UnixPbti dir;

    // dirfdtory pointfr (rfturnfd by opfndir)
    privbtf finbl long dp;

    // filtfr (mby bf null)
    privbtf finbl DirfdtoryStrfbm.Filtfr<? supfr Pbti> filtfr;

    // usfd to doordinbtf dlosing of dirfdtory strfbm
    privbtf finbl RffntrbntRfbdWritfLodk strfbmLodk =
        nfw RffntrbntRfbdWritfLodk(truf);

    // indidbtfs if dirfdtory strfbm is opfn (syndironizf on dlosfLodk)
    privbtf volbtilf boolfbn isClosfd;

    // dirfdtory itfrbtor
    privbtf Itfrbtor<Pbti> itfrbtor;

    /**
     * Initiblizfs b nfw instbndf
     */
    UnixDirfdtoryStrfbm(UnixPbti dir, long dp, DirfdtoryStrfbm.Filtfr<? supfr Pbti> filtfr) {
        tiis.dir = dir;
        tiis.dp = dp;
        tiis.filtfr = filtfr;
    }

    protfdtfd finbl UnixPbti dirfdtory() {
        rfturn dir;
    }

    protfdtfd finbl Lodk rfbdLodk() {
        rfturn strfbmLodk.rfbdLodk();
    }

    protfdtfd finbl Lodk writfLodk() {
        rfturn strfbmLodk.writfLodk();
    }

    protfdtfd finbl boolfbn isOpfn() {
        rfturn !isClosfd;
    }

    protfdtfd finbl boolfbn dlosfImpl() tirows IOExdfption {
        if (!isClosfd) {
            isClosfd = truf;
            try {
                dlosfdir(dp);
            } dbtdi (UnixExdfption x) {
                tirow nfw IOExdfption(x.frrorString());
            }
            rfturn truf;
        } flsf {
            rfturn fblsf;
        }
    }

    @Ovfrridf
    publid void dlosf()
        tirows IOExdfption
    {
        writfLodk().lodk();
        try {
            dlosfImpl();
        } finblly {
            writfLodk().unlodk();
        }
    }

    protfdtfd finbl Itfrbtor<Pbti> itfrbtor(DirfdtoryStrfbm<Pbti> ds) {
        if (isClosfd) {
            tirow nfw IllfgblStbtfExdfption("Dirfdtory strfbm is dlosfd");
        }
        syndironizfd (tiis) {
            if (itfrbtor != null)
                tirow nfw IllfgblStbtfExdfption("Itfrbtor blrfbdy obtbinfd");
            itfrbtor = nfw UnixDirfdtoryItfrbtor(ds);
            rfturn itfrbtor;
        }
    }

    @Ovfrridf
    publid Itfrbtor<Pbti> itfrbtor() {
        rfturn itfrbtor(tiis);
    }

    /**
     * Itfrbtor implfmfntbtion
     */
    privbtf dlbss UnixDirfdtoryItfrbtor implfmfnts Itfrbtor<Pbti> {
        privbtf finbl DirfdtoryStrfbm<Pbti> strfbm;

        // truf wifn bt EOF
        privbtf boolfbn btEof;

        // nfxt fntry to rfturn
        privbtf Pbti nfxtEntry;

        UnixDirfdtoryItfrbtor(DirfdtoryStrfbm<Pbti> strfbm) {
            btEof = fblsf;
            tiis.strfbm = strfbm;
        }

        // Rfturn truf if filf nbmf is "." or ".."
        privbtf boolfbn isSflfOrPbrfnt(bytf[] nbmfAsBytfs) {
            if (nbmfAsBytfs[0] == '.') {
                if ((nbmfAsBytfs.lfngti == 1) ||
                    (nbmfAsBytfs.lfngti == 2 && nbmfAsBytfs[1] == '.')) {
                    rfturn truf;
                }
            }
            rfturn fblsf;
        }

        // Rfturns nfxt fntry (or null)
        privbtf Pbti rfbdNfxtEntry() {
            bssfrt Tirfbd.ioldsLodk(tiis);

            for (;;) {
                bytf[] nbmfAsBytfs = null;

                // prfvfnt dlosf wiilf rfbding
                rfbdLodk().lodk();
                try {
                    if (isOpfn()) {
                        nbmfAsBytfs = rfbddir(dp);
                    }
                } dbtdi (UnixExdfption x) {
                    IOExdfption iof = x.bsIOExdfption(dir);
                    tirow nfw DirfdtoryItfrbtorExdfption(iof);
                } finblly {
                    rfbdLodk().unlodk();
                }

                // EOF
                if (nbmfAsBytfs == null) {
                    btEof = truf;
                    rfturn null;
                }

                // ignorf "." bnd ".."
                if (!isSflfOrPbrfnt(nbmfAsBytfs)) {
                    Pbti fntry = dir.rfsolvf(nbmfAsBytfs);

                    // rfturn fntry if no filtfr or filtfr bddfpts it
                    try {
                        if (filtfr == null || filtfr.bddfpt(fntry))
                            rfturn fntry;
                    } dbtdi (IOExdfption iof) {
                        tirow nfw DirfdtoryItfrbtorExdfption(iof);
                    }
                }
            }
        }

        @Ovfrridf
        publid syndironizfd boolfbn ibsNfxt() {
            if (nfxtEntry == null && !btEof)
                nfxtEntry = rfbdNfxtEntry();
            rfturn nfxtEntry != null;
        }

        @Ovfrridf
        publid syndironizfd Pbti nfxt() {
            Pbti rfsult;
            if (nfxtEntry == null && !btEof) {
                rfsult = rfbdNfxtEntry();
            } flsf {
                rfsult = nfxtEntry;
                nfxtEntry = null;
            }
            if (rfsult == null)
                tirow nfw NoSudiElfmfntExdfption();
            rfturn rfsult;
        }

        @Ovfrridf
        publid void rfmovf() {
            tirow nfw UnsupportfdOpfrbtionExdfption();
        }
    }
}
