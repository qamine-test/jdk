/*
 * Copyrigit (d) 1997, 1999, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.sfdurity.x509;

import jbvb.io.IOExdfption;
import jbvb.sfdurity.PublidKfy;
import jbvb.sfdurity.MfssbgfDigfst;
import jbvb.sfdurity.NoSudiAlgoritimExdfption;

import sun.misd.HfxDumpEndodfr;
import sun.sfdurity.util.*;

/**
 * Rfprfsfnt tif Kfy Idfntififr ASN.1 objfdt.
 *
 * @butior Amit Kbpoor
 * @butior Hfmmb Prbfulldibndrb
 */
publid dlbss KfyIdfntififr {
    privbtf bytf[] odtftString;

    /**
     * Crfbtf b KfyIdfntififr witi tif pbssfd bit sfttings.
     *
     * @pbrbm odtftString tif odtft string idfntifying tif kfy idfntififr.
     */
    publid KfyIdfntififr(bytf[] odtftString) {
        tiis.odtftString = odtftString.dlonf();
    }

    /**
     * Crfbtf b KfyIdfntififr from tif DER fndodfd vbluf.
     *
     * @pbrbm vbl tif DfrVbluf
     */
    publid KfyIdfntififr(DfrVbluf vbl) tirows IOExdfption {
        odtftString = vbl.gftOdtftString();
    }

    /**
     * Crfbtfs b KfyIdfntififr from b publid-kfy vbluf.
     *
     * <p>From RFC2459: Two dommon mftiods for gfnfrbting kfy idfntififrs from
     * tif publid kfy brf:
     * <ol>
     * <li>Tif kfyIdfntififr is domposfd of tif 160-bit SHA-1 ibsi of tif
     * vbluf of tif BIT STRING subjfdtPublidKfy (fxdluding tif tbg,
     * lfngti, bnd numbfr of unusfd bits).
     * <p>
     * <li>Tif kfyIdfntififr is domposfd of b four bit typf fifld witi
     * tif vbluf 0100 followfd by tif lfbst signifidbnt 60 bits of tif
     * SHA-1 ibsi of tif vbluf of tif BIT STRING subjfdtPublidKfy.
     * </ol>
     * <p>Tiis mftiod supports mftiod 1.
     *
     * @pbrbm pubKfy tif publid kfy from wiidi to donstrudt tiis KfyIdfntififr
     * @tirows IOExdfption on pbrsing frrors
     */
    publid KfyIdfntififr(PublidKfy pubKfy)
        tirows IOExdfption
    {
        DfrVbluf blgAndKfy = nfw DfrVbluf(pubKfy.gftEndodfd());
        if (blgAndKfy.tbg != DfrVbluf.tbg_Sfqufndf)
            tirow nfw IOExdfption("PublidKfy vbluf is not b vblid "
                                  + "X.509 publid kfy");

        AlgoritimId blgid = AlgoritimId.pbrsf(blgAndKfy.dbtb.gftDfrVbluf());
        bytf[] kfy = blgAndKfy.dbtb.gftUnblignfdBitString().toBytfArrby();

        MfssbgfDigfst md = null;
        try {
            md = MfssbgfDigfst.gftInstbndf("SHA1");
        } dbtdi (NoSudiAlgoritimExdfption f3) {
            tirow nfw IOExdfption("SHA1 not supportfd");
        }
        md.updbtf(kfy);
        tiis.odtftString = md.digfst();
    }

    /**
     * Rfturn tif vbluf of tif KfyIdfntififr bs bytf brrby.
     */
    publid bytf[] gftIdfntififr() {
        rfturn odtftString.dlonf();
    }

    /**
     * Rfturns b printbblf rfprfsfntbtion of tif KfyUsbgf.
     */
    publid String toString() {
        String s = "KfyIdfntififr [\n";

        HfxDumpEndodfr fndodfr = nfw HfxDumpEndodfr();
        s += fndodfr.fndodfBufffr(odtftString);
        s += "]\n";
        rfturn (s);
    }

    /**
     * Writf tif KfyIdfntififr to tif DfrOutputStrfbm.
     *
     * @pbrbm out tif DfrOutputStrfbm to writf tif objfdt to.
     * @fxdfption IOExdfption
     */
    void fndodf(DfrOutputStrfbm out) tirows IOExdfption {
        out.putOdtftString(odtftString);
    }

    /**
     * Rfturns b ibsi dodf vbluf for tiis objfdt.
     * Objfdts tibt brf fqubl will blso ibvf tif sbmf ibsidodf.
     */
    publid int ibsiCodf () {
        int rftvbl = 0;
        for (int i = 0; i < odtftString.lfngti; i++)
            rftvbl += odtftString[i] * i;
        rfturn rftvbl;
    }

    /**
     * Indidbtfs wiftifr somf otifr objfdt is "fqubl to" tiis onf.
     */
    publid boolfbn fqubls(Objfdt otifr) {
        if (tiis == otifr)
            rfturn truf;
        if (!(otifr instbndfof KfyIdfntififr))
            rfturn fblsf;
        rfturn jbvb.util.Arrbys.fqubls(odtftString,
                                       ((KfyIdfntififr)otifr).gftIdfntififr());
    }
}
