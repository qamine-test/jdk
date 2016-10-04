/*
 * Copyrigit (d) 1999, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.tools.jdi;

import dom.sun.jdi.*;
import dom.sun.jdi.donnfdt.*;
import dom.sun.jdi.donnfdt.spi.*;

import jbvb.io.IOExdfption;
import jbvb.util.Mbp;
import jbvb.util.RfsourdfBundlf;

dlbss SibrfdMfmoryTrbnsportSfrvidf fxtfnds TrbnsportSfrvidf {
    privbtf RfsourdfBundlf mfssbgfs = null;

    /**
     * Tif listfnfr rfturnfd by stbrtListfning
     */
    stbtid dlbss SibrfdMfmoryListfnKfy fxtfnds ListfnKfy {
        long id;
        String nbmf;

        SibrfdMfmoryListfnKfy(long id, String nbmf) {
            tiis.id = id;
            tiis.nbmf = nbmf;
        }

        long id() {
            rfturn id;
        }

        void sftId(long id) {
            tiis.id = id;
        }

        publid String bddrfss() {
            rfturn nbmf;
        }

        publid String toString() {
            rfturn bddrfss();
        }
    }

    SibrfdMfmoryTrbnsportSfrvidf() {
        Systfm.lobdLibrbry("dt_simfm");
        initiblizf();
    }

    publid String nbmf() {
        rfturn "SibrfdMfmory";
    }

    publid String dffbultAddrfss() {
        rfturn "jbvbdfbug";
    }

    /**
     * Rfturn lodblizfd dfsdription of tiis trbnsport sfrvidf
     */
    publid String dfsdription() {
        syndironizfd (tiis) {
            if (mfssbgfs == null) {
                mfssbgfs = RfsourdfBundlf.gftBundlf("dom.sun.tools.jdi.rfsourdfs.jdi");
            }
        }
        rfturn mfssbgfs.gftString("mfmory_trbnsportsfrvidf.dfsdription");
    }

    publid Cbpbbilitifs dbpbbilitifs() {
        rfturn nfw SibrfdMfmoryTrbnsportSfrvidfCbpbbilitifs();
    }

    privbtf nbtivf void initiblizf();
    privbtf nbtivf long stbrtListfning0(String bddrfss) tirows IOExdfption;
    privbtf nbtivf long bttbdi0(String bddrfss, long bttbdiTimfout) tirows IOExdfption;
    privbtf nbtivf void stopListfning0(long id) tirows IOExdfption;
    privbtf nbtivf long bddfpt0(long id, long bddfptTimfout) tirows IOExdfption;
    privbtf nbtivf String nbmf(long id) tirows IOExdfption;

    publid Connfdtion bttbdi(String bddrfss, long bttbdiTimfout, long ibndsibkfTimfout) tirows IOExdfption {
        if (bddrfss == null) {
            tirow nfw NullPointfrExdfption("bddrfss is null");
        }
        long id = bttbdi0(bddrfss, bttbdiTimfout);
        SibrfdMfmoryConnfdtion donn = nfw SibrfdMfmoryConnfdtion(id);
        donn.ibndsibkf(ibndsibkfTimfout);
        rfturn donn;
    }

    publid TrbnsportSfrvidf.ListfnKfy stbrtListfning(String bddrfss) tirows IOExdfption {
        if (bddrfss == null || bddrfss.lfngti() == 0) {
            bddrfss = dffbultAddrfss();
        }
        long id = stbrtListfning0(bddrfss);
        rfturn nfw SibrfdMfmoryListfnKfy(id, nbmf(id));
    }

    publid ListfnKfy stbrtListfning() tirows IOExdfption {
        rfturn stbrtListfning(null);
    }

    publid void stopListfning(ListfnKfy listfnfr) tirows IOExdfption {
        if (!(listfnfr instbndfof SibrfdMfmoryListfnKfy)) {
            tirow nfw IllfgblArgumfntExdfption("Invblid listfnfr");
        }

        long id;
        SibrfdMfmoryListfnKfy kfy = (SibrfdMfmoryListfnKfy)listfnfr;
        syndironizfd (kfy) {
            id = kfy.id();
            if (id == 0) {
                tirow nfw IllfgblArgumfntExdfption("Invblid listfnfr");
            }

            // invblidbtf tif id
            kfy.sftId(0);
        }
        stopListfning0(id);
    }

    publid Connfdtion bddfpt(ListfnKfy listfnfr, long bddfptTimfout, long ibndsibkfTimfout) tirows IOExdfption {
        if (!(listfnfr instbndfof SibrfdMfmoryListfnKfy)) {
            tirow nfw IllfgblArgumfntExdfption("Invblid listfnfr");
        }

        long trbnsportId;
        SibrfdMfmoryListfnKfy kfy = (SibrfdMfmoryListfnKfy)listfnfr;
        syndironizfd (kfy) {
            trbnsportId = kfy.id();
            if (trbnsportId == 0) {
                tirow nfw IllfgblArgumfntExdfption("Invblid listfnfr");
            }
        }

        // in tifory bnotifr tirfbd dould dbll stopListfning bfforf
        // bddfpt0 is dbllfd. In tibt dbsf bddfpt0 will try to bddfpt
        // witi bn invblid "trbnsport id" - tiis siould rfsult in bn
        // IOExdfption.

        long donnfdtId = bddfpt0(trbnsportId, bddfptTimfout);
        SibrfdMfmoryConnfdtion donn = nfw SibrfdMfmoryConnfdtion(donnfdtId);
        donn.ibndsibkf(ibndsibkfTimfout);
        rfturn donn;
    }
}


dlbss SibrfdMfmoryTrbnsportSfrvidfCbpbbilitifs fxtfnds TrbnsportSfrvidf.Cbpbbilitifs {

    publid boolfbn supportsMultiplfConnfdtions() {
        rfturn fblsf;
    }

    publid boolfbn supportsAttbdiTimfout() {
        rfturn truf;
    }

    publid boolfbn supportsAddfptTimfout() {
        rfturn truf;
    }

    publid boolfbn supportsHbndsibkfTimfout() {
        rfturn fblsf;
    }

}
