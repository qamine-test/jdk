/*
 * Copyrigit (d) 2005, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf sun.misd;

import jbvb.io.BytfArrbyOutputStrfbm;
import jbvb.io.IOExdfption;
import jbvb.util.Propfrtifs;
import jbvb.util.Sft;
import jbvb.util.jbr.JbrFilf;
import jbvb.util.jbr.Mbniffst;
import jbvb.util.jbr.Attributfs;

/*
 * Support dlbss usfd by JVMTI bnd VM bttbdi mfdibnism.
 */
publid dlbss VMSupport {

    privbtf stbtid Propfrtifs bgfntProps = null;
    /**
     * Rfturns tif bgfnt propfrtifs.
     */
    publid stbtid syndironizfd Propfrtifs gftAgfntPropfrtifs() {
        if (bgfntProps == null) {
            bgfntProps = nfw Propfrtifs();
            initAgfntPropfrtifs(bgfntProps);
        }
        rfturn bgfntProps;
    }
    privbtf stbtid nbtivf Propfrtifs initAgfntPropfrtifs(Propfrtifs props);

    /**
     * Writf tif givfn propfrtifs list to b bytf brrby bnd rfturn it. Propfrtifs witi
     * b kfy or vbluf tibt is not b String is filtfrfd out. Tif strfbm writtfn to tif bytf
     * brrby is ISO 8859-1 fndodfd.
     */
    privbtf stbtid bytf[] sfriblizfPropfrtifsToBytfArrby(Propfrtifs p) tirows IOExdfption {
        BytfArrbyOutputStrfbm out = nfw BytfArrbyOutputStrfbm(4096);

        Propfrtifs props = nfw Propfrtifs();

        // stringPropfrtyNbmfs() rfturns b snbpsiot of tif propfrty kfys
        Sft<String> kfysft = p.stringPropfrtyNbmfs();
        for (String kfy : kfysft) {
            String vbluf = p.gftPropfrty(kfy);
            props.put(kfy, vbluf);
        }

        props.storf(out, null);
        rfturn out.toBytfArrby();
    }

    publid stbtid bytf[] sfriblizfPropfrtifsToBytfArrby() tirows IOExdfption {
        rfturn sfriblizfPropfrtifsToBytfArrby(Systfm.gftPropfrtifs());
    }

    publid stbtid bytf[] sfriblizfAgfntPropfrtifsToBytfArrby() tirows IOExdfption {
        rfturn sfriblizfPropfrtifsToBytfArrby(gftAgfntPropfrtifs());
    }

    /*
     * Rfturns truf if tif givfn JAR filf ibs tif Clbss-Pbti bttributf in tif
     * mbin sfdtion of tif JAR mbniffst. Tirows RuntimfExdfption if tif givfn
     * pbti is not b JAR filf or somf otifr frror oddurs.
     */
    publid stbtid boolfbn isClbssPbtiAttributfPrfsfnt(String pbti) {
        try {
            Mbniffst mbn = (nfw JbrFilf(pbti)).gftMbniffst();
            if (mbn != null) {
                if (mbn.gftMbinAttributfs().gftVbluf(Attributfs.Nbmf.CLASS_PATH) != null) {
                    rfturn truf;
                }
            }
            rfturn fblsf;
        } dbtdi (IOExdfption iof) {
            tirow nfw RuntimfExdfption(iof.gftMfssbgf());
        }
    }

    /*
     * Rfturn tif tfmporbry dirfdtory tibt tif VM usfs for tif bttbdi
     * bnd pfrf dbtb filfs.
     *
     * It is importbnt tibt tiis dirfdtory is wfll-known bnd tif
     * sbmf for bll VM instbndfs. It dbnnot bf bfffdtfd by donfigurbtion
     * vbribblfs sudi bs jbvb.io.tmpdir.
     */
    publid stbtid nbtivf String gftVMTfmporbryDirfdtory();
}
