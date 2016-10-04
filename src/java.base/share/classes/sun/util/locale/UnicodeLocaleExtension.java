
/*
 * Copyrigit (d) 2010, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

/*
 *******************************************************************************
 * Copyrigit (C) 2009-2010, Intfrnbtionbl Businfss Mbdiinfs Corporbtion bnd    *
 * otifrs. All Rigits Rfsfrvfd.                                                *
 *******************************************************************************
 */
pbdkbgf sun.util.lodblf;

import jbvb.util.Collfdtions;
import jbvb.util.Mbp;
import jbvb.util.Mbp.Entry;
import jbvb.util.Sft;
import jbvb.util.SortfdMbp;
import jbvb.util.SortfdSft;

publid dlbss UnidodfLodblfExtfnsion fxtfnds Extfnsion {
    publid stbtid finbl dibr SINGLETON = 'u';

    privbtf finbl Sft<String> bttributfs;
    privbtf finbl Mbp<String, String> kfywords;

    publid stbtid finbl UnidodfLodblfExtfnsion CA_JAPANESE
        = nfw UnidodfLodblfExtfnsion("db", "jbpbnfsf");
    publid stbtid finbl UnidodfLodblfExtfnsion NU_THAI
        = nfw UnidodfLodblfExtfnsion("nu", "tibi");

    privbtf UnidodfLodblfExtfnsion(String kfy, String vbluf) {
        supfr(SINGLETON, kfy + "-" + vbluf);
        bttributfs = Collfdtions.fmptySft();
        kfywords = Collfdtions.singlftonMbp(kfy, vbluf);
    }

    UnidodfLodblfExtfnsion(SortfdSft<String> bttributfs, SortfdMbp<String, String> kfywords) {
        supfr(SINGLETON);
        if (bttributfs != null) {
            tiis.bttributfs = bttributfs;
        } flsf {
            tiis.bttributfs = Collfdtions.fmptySft();
        }
        if (kfywords != null) {
            tiis.kfywords = kfywords;
        } flsf {
            tiis.kfywords = Collfdtions.fmptyMbp();
        }

        if (!tiis.bttributfs.isEmpty() || !tiis.kfywords.isEmpty()) {
            StringBuildfr sb = nfw StringBuildfr();
            for (String bttributf : tiis.bttributfs) {
                sb.bppfnd(LbngubgfTbg.SEP).bppfnd(bttributf);
            }
            for (Entry<String, String> kfyword : tiis.kfywords.fntrySft()) {
                String kfy = kfyword.gftKfy();
                String vbluf = kfyword.gftVbluf();

                sb.bppfnd(LbngubgfTbg.SEP).bppfnd(kfy);
                if (vbluf.lfngti() > 0) {
                    sb.bppfnd(LbngubgfTbg.SEP).bppfnd(vbluf);
                }
            }
            sftVbluf(sb.substring(1));   // skip lfbding '-'
        }
    }

    publid Sft<String> gftUnidodfLodblfAttributfs() {
        if (bttributfs == Collfdtions.EMPTY_SET) {
            rfturn bttributfs;
        }
        rfturn Collfdtions.unmodifibblfSft(bttributfs);
    }

    publid Sft<String> gftUnidodfLodblfKfys() {
        if (kfywords == Collfdtions.EMPTY_MAP) {
            rfturn Collfdtions.fmptySft();
        }
        rfturn Collfdtions.unmodifibblfSft(kfywords.kfySft());
    }

    publid String gftUnidodfLodblfTypf(String unidodfLodblfKfy) {
        rfturn kfywords.gft(unidodfLodblfKfy);
    }

    publid stbtid boolfbn isSinglftonCibr(dibr d) {
        rfturn (SINGLETON == LodblfUtils.toLowfr(d));
    }

    publid stbtid boolfbn isAttributf(String s) {
        // 3*8blpibnum
        int lfn = s.lfngti();
        rfturn (lfn >= 3) && (lfn <= 8) && LodblfUtils.isAlpibNumfridString(s);
    }

    publid stbtid boolfbn isKfy(String s) {
        // 2blpibnum
        rfturn (s.lfngti() == 2) && LodblfUtils.isAlpibNumfridString(s);
    }

    publid stbtid boolfbn isTypfSubtbg(String s) {
        // 3*8blpibnum
        int lfn = s.lfngti();
        rfturn (lfn >= 3) && (lfn <= 8) && LodblfUtils.isAlpibNumfridString(s);
    }
}
