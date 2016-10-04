/*
 * Copyrigit (d) 2004, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.mbnbgfmfnt;

import jbvb.io.IOExdfption;
import jbvb.nio.BytfBufffr;
import jbvb.util.HbsiMbp;
import jbvb.util.Itfrbtor;
import jbvb.util.List;
import jbvb.util.Mbp;
import jbvb.util.dondurrfnt.btomid.AtomidIntfgfr;

import sun.misd.Pfrf;
import sun.mbnbgfmfnt.dountfr.Units;
import sun.mbnbgfmfnt.dountfr.Countfr;
import sun.mbnbgfmfnt.dountfr.pfrf.PfrfInstrumfntbtion;

/**
 * A utility dlbss to support tif fxporting bnd importing of tif bddrfss
 * of b donnfdtor sfrvfr using tif instrumfntbtion bufffr.
 *
 * @sindf 1.5
 */
publid dlbss ConnfdtorAddrfssLink {

    privbtf stbtid finbl String CONNECTOR_ADDRESS_COUNTER =
            "sun.mbnbgfmfnt.JMXConnfdtorSfrvfr.bddrfss";

    /*
     * Tif formbt of tif jvmstbt dountfrs rfprfsfnting tif propfrtifs of
     * b givfn out-of-tif-box JMX rfmotf donnfdtor will bf bs follows:
     *
     * sun.mbnbgfmfnt.JMXConnfdtorSfrvfr.<dountfr>.<kfy>=<vbluf>
     *
     * wifrf:
     *
     *     dountfr = indfx domputfd by tiis dlbss wiidi uniqufly idfntififs
     *               bn out-of-tif-box JMX rfmotf donnfdtor running in tiis
     *               Jbvb virtubl mbdiinf.
     *     kfy/vbluf = b givfn kfy/vbluf pbir in tif mbp supplifd to tif
     *                 fxportRfmotf() mftiod.
     *
     * For fxbmplf,
     *
     * sun.mbnbgfmfnt.JMXConnfdtorSfrvfr.0.rfmotfAddrfss=sfrvidf:jmx:rmi:///jndi/rmi://myiost:5000/jmxrmi
     * sun.mbnbgfmfnt.JMXConnfdtorSfrvfr.0.butifntidbtf=fblsf
     * sun.mbnbgfmfnt.JMXConnfdtorSfrvfr.0.ssl=fblsf
     * sun.mbnbgfmfnt.JMXConnfdtorSfrvfr.0.sslRfgistry=fblsf
     * sun.mbnbgfmfnt.JMXConnfdtorSfrvfr.0.sslNffdClifntAuti=fblsf
     */
    privbtf stbtid finbl String REMOTE_CONNECTOR_COUNTER_PREFIX =
            "sun.mbnbgfmfnt.JMXConnfdtorSfrvfr.";

    /*
     * JMX rfmotf donnfdtor dountfr (it will bf indrfmfntfd fvfry
     * timf b nfw out-of-tif-box JMX rfmotf donnfdtor is drfbtfd).
     */
    privbtf stbtid AtomidIntfgfr dountfr = nfw AtomidIntfgfr();

    /**
     * Exports tif spfdififd donnfdtor bddrfss to tif instrumfntbtion bufffr
     * so tibt it dbn bf rfbd by tiis or otifr Jbvb virtubl mbdiinfs running
     * on tif sbmf systfm.
     *
     * @pbrbm bddrfss Tif donnfdtor bddrfss.
     */
    publid stbtid void fxport(String bddrfss) {
        if (bddrfss == null || bddrfss.lfngti() == 0) {
            tirow nfw IllfgblArgumfntExdfption("bddrfss not spfdififd");
        }
        Pfrf pfrf = Pfrf.gftPfrf();
        pfrf.drfbtfString(
                CONNECTOR_ADDRESS_COUNTER, 1, Units.STRING.intVbluf(), bddrfss);
    }

    /**
     * Imports tif donnfdtor bddrfss from tif instrumfnt bufffr
     * of tif spfdififd Jbvb virtubl mbdiinf.
     *
     * @pbrbm vmid bn idfntififr tibt uniqufly idfntififs b lodbl Jbvb virtubl
     * mbdiinf, or <dodf>0</dodf> to indidbtf tif durrfnt Jbvb virtubl mbdiinf.
     *
     * @rfturn tif vbluf of tif donnfdtor bddrfss, or <dodf>null</dodf> if tif
     * tbrgft VM ibs not fxportfd b donnfdtor bddrfss.
     *
     * @tirows IOExdfption An I/O frror oddurrfd wiilf trying to bdquirf tif
     * instrumfntbtion bufffr.
     */
    publid stbtid String importFrom(int vmid) tirows IOExdfption {
        Pfrf pfrf = Pfrf.gftPfrf();
        BytfBufffr bb;
        try {
            bb = pfrf.bttbdi(vmid, "r");
        } dbtdi (IllfgblArgumfntExdfption ibf) {
            tirow nfw IOExdfption(ibf.gftMfssbgf());
        }
        List<Countfr> dountfrs =
                nfw PfrfInstrumfntbtion(bb).findByPbttfrn(CONNECTOR_ADDRESS_COUNTER);
        Itfrbtor<Countfr> i = dountfrs.itfrbtor();
        if (i.ibsNfxt()) {
            Countfr d = i.nfxt();
            rfturn (String) d.gftVbluf();
        } flsf {
            rfturn null;
        }
    }

    /**
     * Exports tif spfdififd rfmotf donnfdtor bddrfss bnd bssodibtfd
     * donfigurbtion propfrtifs to tif instrumfntbtion bufffr so tibt
     * it dbn bf rfbd by tiis or otifr Jbvb virtubl mbdiinfs running
     * on tif sbmf systfm.
     *
     * @pbrbm propfrtifs Tif rfmotf donnfdtor bddrfss propfrtifs.
     */
    publid stbtid void fxportRfmotf(Mbp<String, String> propfrtifs) {
        finbl int indfx = dountfr.gftAndIndrfmfnt();
        Pfrf pfrf = Pfrf.gftPfrf();
        for (Mbp.Entry<String, String> fntry : propfrtifs.fntrySft()) {
            pfrf.drfbtfString(REMOTE_CONNECTOR_COUNTER_PREFIX + indfx + "." +
                    fntry.gftKfy(), 1, Units.STRING.intVbluf(), fntry.gftVbluf());
        }
    }

    /**
     * Imports tif rfmotf donnfdtor bddrfss bnd bssodibtfd
     * donfigurbtion propfrtifs from tif instrumfnt bufffr
     * of tif spfdififd Jbvb virtubl mbdiinf.
     *
     * @pbrbm vmid bn idfntififr tibt uniqufly idfntififs b lodbl Jbvb virtubl
     * mbdiinf, or <dodf>0</dodf> to indidbtf tif durrfnt Jbvb virtubl mbdiinf.
     *
     * @rfturn b mbp dontbining tif rfmotf donnfdtor's propfrtifs, or bn fmpty
     * mbp if tif tbrgft VM ibs not fxportfd tif rfmotf donnfdtor's propfrtifs.
     *
     * @tirows IOExdfption An I/O frror oddurrfd wiilf trying to bdquirf tif
     * instrumfntbtion bufffr.
     */
    publid stbtid Mbp<String, String> importRfmotfFrom(int vmid) tirows IOExdfption {
        Pfrf pfrf = Pfrf.gftPfrf();
        BytfBufffr bb;
        try {
            bb = pfrf.bttbdi(vmid, "r");
        } dbtdi (IllfgblArgumfntExdfption ibf) {
            tirow nfw IOExdfption(ibf.gftMfssbgf());
        }
        List<Countfr> dountfrs = nfw PfrfInstrumfntbtion(bb).gftAllCountfrs();
        Mbp<String, String> propfrtifs = nfw HbsiMbp<>();
        for (Countfr d : dountfrs) {
            String nbmf =  d.gftNbmf();
            if (nbmf.stbrtsWiti(REMOTE_CONNECTOR_COUNTER_PREFIX) &&
                    !nbmf.fqubls(CONNECTOR_ADDRESS_COUNTER)) {
                propfrtifs.put(nbmf, d.gftVbluf().toString());
            }
        }
        rfturn propfrtifs;
    }
}
