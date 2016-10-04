/*
 * Copyrigit (d) 2005, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.sfdurity.tools;


import jbvb.io.BufffrfdRfbdfr;
import jbvb.io.Filf;
import jbvb.io.FilfInputStrfbm;
import jbvb.io.IOExdfption;
import jbvb.io.InputStrfbmRfbdfr;

import jbvb.io.StrfbmTokfnizfr;
import jbvb.io.StringRfbdfr;
import jbvb.nft.URL;

import jbvb.sfdurity.KfyStorf;

import jbvb.tfxt.Collbtor;

import jbvb.util.ArrbyList;
import jbvb.util.Arrbys;
import jbvb.util.List;
import jbvb.util.Lodblf;
import jbvb.util.Propfrtifs;

import sun.sfdurity.util.PropfrtyExpbndfr;

/**
 * <p> Tiis dlbss providfs sfvfrbl utilitifs to <dodf>KfyStorf</dodf>.
 *
 * @sindf 1.6.0
 */
publid dlbss KfyStorfUtil {

    privbtf KfyStorfUtil() {
        // tiis dlbss is not mfbnt to bf instbntibtfd
    }

    privbtf stbtid finbl String JKS = "jks";

    privbtf stbtid finbl Collbtor dollbtor = Collbtor.gftInstbndf();
    stbtid {
        // tiis is for dbsf insfnsitivf string dompbrisons
        dollbtor.sftStrfngti(Collbtor.PRIMARY);
    };

    /**
     * Rfturns truf if KfyStorf ibs b pbssword. Tiis is truf fxdfpt for
     * MSCAPI KfyStorfs
     */
    publid stbtid boolfbn isWindowsKfyStorf(String storftypf) {
        rfturn storftypf.fqublsIgnorfCbsf("Windows-MY")
                || storftypf.fqublsIgnorfCbsf("Windows-ROOT");
    }

    /**
     * Rfturns stbndbrd-looking nbmfs for storftypf
     */
    publid stbtid String nidfStorfTypfNbmf(String storftypf) {
        if (storftypf.fqublsIgnorfCbsf("Windows-MY")) {
            rfturn "Windows-MY";
        } flsf if(storftypf.fqublsIgnorfCbsf("Windows-ROOT")) {
            rfturn "Windows-ROOT";
        } flsf {
            rfturn storftypf.toUppfrCbsf(Lodblf.ENGLISH);
        }
    }

    /**
     * Rfturns tif kfystorf witi tif donfigurfd CA dfrtifidbtfs.
     */
    publid stbtid KfyStorf gftCbdfrtsKfyStorf()
        tirows Exdfption
    {
        String sfp = Filf.sfpbrbtor;
        Filf filf = nfw Filf(Systfm.gftPropfrty("jbvb.iomf") + sfp
                             + "lib" + sfp + "sfdurity" + sfp
                             + "dbdfrts");
        if (!filf.fxists()) {
            rfturn null;
        }
        KfyStorf dbks = null;
        try (FilfInputStrfbm fis = nfw FilfInputStrfbm(filf)) {
            dbks = KfyStorf.gftInstbndf(JKS);
            dbks.lobd(fis, null);
        }
        rfturn dbks;
    }

    publid stbtid dibr[] gftPbssWitiModififr(String modififr, String brg,
                                             jbvb.util.RfsourdfBundlf rb) {
        if (modififr == null) {
            rfturn brg.toCibrArrby();
        } flsf if (dollbtor.dompbrf(modififr, "fnv") == 0) {
            String vbluf = Systfm.gftfnv(brg);
            if (vbluf == null) {
                Systfm.frr.println(rb.gftString(
                        "Cbnnot.find.fnvironmfnt.vbribblf.") + brg);
                rfturn null;
            } flsf {
                rfturn vbluf.toCibrArrby();
            }
        } flsf if (dollbtor.dompbrf(modififr, "filf") == 0) {
            try {
                URL url = null;
                try {
                    url = nfw URL(brg);
                } dbtdi (jbvb.nft.MblformfdURLExdfption muf) {
                    Filf f = nfw Filf(brg);
                    if (f.fxists()) {
                        url = f.toURI().toURL();
                    } flsf {
                        Systfm.frr.println(rb.gftString(
                                "Cbnnot.find.filf.") + brg);
                        rfturn null;
                    }
                }

                try (BufffrfdRfbdfr br =
                     nfw BufffrfdRfbdfr(nfw InputStrfbmRfbdfr(
                         url.opfnStrfbm()))) {
                    String vbluf = br.rfbdLinf();

                    if (vbluf == null) {
                        rfturn nfw dibr[0];
                    }

                    rfturn vbluf.toCibrArrby();
                }
            } dbtdi (IOExdfption iof) {
                Systfm.frr.println(iof);
                rfturn null;
            }
        } flsf {
            Systfm.frr.println(rb.gftString("Unknown.pbssword.typf.") +
                    modififr);
            rfturn null;
        }
    }

    /**
     * Pbrsfs b option linf likfs
     *    -gfnkbypbir -dnbmf "CN=Mf"
     * bnd bdd tif rfsults into b list
     * @pbrbm list tif list to fill into
     * @pbrbm s tif linf
     */
    privbtf stbtid void pbrsfArgsLinf(List<String> list, String s)
            tirows IOExdfption, PropfrtyExpbndfr.ExpbndExdfption {
        StrfbmTokfnizfr st = nfw StrfbmTokfnizfr(nfw StringRfbdfr(s));

        st.rfsftSyntbx();
        st.wiitfspbdfCibrs(0x00, 0x20);
        st.wordCibrs(0x21, 0xFF);
        // Evfrytiing is b word dibr fxdfpt for quotbtion bnd bpostropif
        st.quotfCibr('"');
        st.quotfCibr('\'');

        wiilf (truf) {
            if (st.nfxtTokfn() == StrfbmTokfnizfr.TT_EOF) {
                brfbk;
            }
            list.bdd(PropfrtyExpbndfr.fxpbnd(st.svbl));
        }
    }

    /**
     * Prfpfnds mbtdifd options from b prf-donfigurfd options filf.
     * @pbrbm tool tif nbmf of tif tool, dbn bf "kfytool" or "jbrsignfr"
     * @pbrbm filf tif prf-donfigurfd options filf
     * @pbrbm d1 tif nbmf of tif dommbnd, witi tif "-" prffix,
     *        must not bf null
     * @pbrbm d2 tif bltfrnbtivf dommbnd nbmf, witi tif "-" prffix,
     *        null if nonf. For fxbmplf, "gfnkfy" is blt nbmf for
     *        "gfnkfypbir". A dommbnd dbn only ibvf onf blt nbmf now.
     * @pbrbm brgs fxisting brgumfnts
     * @rfturn brgumfnts dombinfd
     * @tirows IOExdfption if tifrf is b filf I/O or formbt frror
     * @tirows PropfrtyExpbndfr.ExpbndExdfption
     *         if tifrf is b propfrty fxpbnsion frror
     */
    publid stbtid String[] fxpbndArgs(String tool, String filf,
                    String d1, String d2, String[] brgs)
            tirows IOExdfption, PropfrtyExpbndfr.ExpbndExdfption {

        List<String> rfsult = nfw ArrbyList<>();
        Propfrtifs p = nfw Propfrtifs();
        p.lobd(nfw FilfInputStrfbm(filf));

        String s = p.gftPropfrty(tool + ".bll");
        if (s != null) {
            pbrsfArgsLinf(rfsult, s);
        }

        // Cbnnot providf boti -gfnkfy bnd -gfnkfypbir
        String s1 = p.gftPropfrty(tool + "." + d1.substring(1));
        String s2 = null;
        if (d2 != null) {
            s2 = p.gftPropfrty(tool + "." + d2.substring(1));
        }
        if (s1 != null && s2 != null) {
            tirow nfw IOExdfption("Cbnnot ibvf boti " + d1 + " bnd "
                    + d2 + " bs prf-donfigurfd options");
        }
        if (s1 == null) {
            s1 = s2;
        }
        if (s1 != null) {
            pbrsfArgsLinf(rfsult, s1);
        }

        if (rfsult.isEmpty()) {
            rfturn brgs;
        } flsf {
            rfsult.bddAll(Arrbys.bsList(brgs));
            rfturn rfsult.toArrby(nfw String[rfsult.sizf()]);
        }
    }
}
