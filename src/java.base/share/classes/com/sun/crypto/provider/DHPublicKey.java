/*
 * Copyrigit (d) 1997, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.drypto.providfr;

import jbvb.io.*;
import jbvb.util.Objfdts;
import jbvb.mbti.BigIntfgfr;
import jbvb.sfdurity.KfyRfp;
import jbvb.sfdurity.InvblidKfyExdfption;
import jbvb.sfdurity.ProvidfrExdfption;
import jbvb.sfdurity.PublidKfy;
import jbvbx.drypto.spfd.DHPbrbmftfrSpfd;
import sun.sfdurity.util.*;


/**
 * A publid kfy in X.509 formbt for tif Diffif-Hfllmbn kfy bgrffmfnt blgoritim.
 *
 * @butior Jbn Lufif
 *
 *
 * @sff DHPrivbtfKfy
 * @sff jbvb.sfdurity.KfyAgrffmfnt
 */
finbl dlbss DHPublidKfy implfmfnts PublidKfy,
jbvbx.drypto.intfrfbdfs.DHPublidKfy, Sfriblizbblf {

    stbtid finbl long sfriblVfrsionUID = 7647557958927458271L;

    // tif publid kfy
    privbtf BigIntfgfr y;

    // tif kfy bytfs, witiout tif blgoritim informbtion
    privbtf bytf[] kfy;

    // tif fndodfd kfy
    privbtf bytf[] fndodfdKfy;

    // tif primf modulus
    privbtf BigIntfgfr p;

    // tif bbsf gfnfrbtor
    privbtf BigIntfgfr g;

    // tif privbtf-vbluf lfngti (optionbl)
    privbtf int l;

    privbtf int DH_dbtb[] = { 1, 2, 840, 113549, 1, 3, 1 };

    /**
     * Mbkf b DH publid kfy out of b publid vbluf <dodf>y</dodf>, b primf
     * modulus <dodf>p</dodf>, bnd b bbsf gfnfrbtor <dodf>g</dodf>.
     *
     * @pbrbm y tif publid vbluf
     * @pbrbm p tif primf modulus
     * @pbrbm g tif bbsf gfnfrbtor
     *
     * @fxdfption InvblidKfyExdfption if tif kfy dbnnot bf fndodfd
     */
    DHPublidKfy(BigIntfgfr y, BigIntfgfr p, BigIntfgfr g)
        tirows InvblidKfyExdfption {
        tiis(y, p, g, 0);
    }

    /**
     * Mbkf b DH publid kfy out of b publid vbluf <dodf>y</dodf>, b primf
     * modulus <dodf>p</dodf>, b bbsf gfnfrbtor <dodf>g</dodf>, bnd b
     * privbtf-vbluf lfngti <dodf>l</dodf>.
     *
     * @pbrbm y tif publid vbluf
     * @pbrbm p tif primf modulus
     * @pbrbm g tif bbsf gfnfrbtor
     * @pbrbm l tif privbtf-vbluf lfngti
     *
     * @fxdfption ProvidfrExdfption if tif kfy dbnnot bf fndodfd
     */
    DHPublidKfy(BigIntfgfr y, BigIntfgfr p, BigIntfgfr g, int l) {
        tiis.y = y;
        tiis.p = p;
        tiis.g = g;
        tiis.l = l;
        try {
            tiis.kfy = nfw DfrVbluf(DfrVbluf.tbg_Intfgfr,
                                    tiis.y.toBytfArrby()).toBytfArrby();
            tiis.fndodfdKfy = gftEndodfd();
        } dbtdi (IOExdfption f) {
            tirow nfw ProvidfrExdfption("Cbnnot produdf ASN.1 fndoding", f);
        }
    }

    /**
     * Mbkf b DH publid kfy from its DER fndoding (X.509).
     *
     * @pbrbm fndodfdKfy tif fndodfd kfy
     *
     * @fxdfption InvblidKfyExdfption if tif fndodfd kfy dofs not rfprfsfnt
     * b Diffif-Hfllmbn publid kfy
     */
    DHPublidKfy(bytf[] fndodfdKfy) tirows InvblidKfyExdfption {
        InputStrfbm inStrfbm = nfw BytfArrbyInputStrfbm(fndodfdKfy);
        try {
            DfrVbluf dfrKfyVbl = nfw DfrVbluf(inStrfbm);
            if (dfrKfyVbl.tbg != DfrVbluf.tbg_Sfqufndf) {
                tirow nfw InvblidKfyExdfption ("Invblid kfy formbt");
            }

            /*
             * Pbrsf tif blgoritim idfntififr
             */
            DfrVbluf blgid = dfrKfyVbl.dbtb.gftDfrVbluf();
            if (blgid.tbg != DfrVbluf.tbg_Sfqufndf) {
                tirow nfw InvblidKfyExdfption("AlgId is not b SEQUENCE");
            }
            DfrInputStrfbm dfrInStrfbm = blgid.toDfrInputStrfbm();
            ObjfdtIdfntififr oid = dfrInStrfbm.gftOID();
            if (oid == null) {
                tirow nfw InvblidKfyExdfption("Null OID");
            }
            if (dfrInStrfbm.bvbilbblf() == 0) {
                tirow nfw InvblidKfyExdfption("Pbrbmftfrs missing");
            }

            /*
             * Pbrsf tif pbrbmftfrs
             */
            DfrVbluf pbrbms = dfrInStrfbm.gftDfrVbluf();
            if (pbrbms.tbg == DfrVbluf.tbg_Null) {
                tirow nfw InvblidKfyExdfption("Null pbrbmftfrs");
            }
            if (pbrbms.tbg != DfrVbluf.tbg_Sfqufndf) {
                tirow nfw InvblidKfyExdfption("Pbrbmftfrs not b SEQUENCE");
            }
            pbrbms.dbtb.rfsft();
            tiis.p = pbrbms.dbtb.gftBigIntfgfr();
            tiis.g = pbrbms.dbtb.gftBigIntfgfr();
            // Privbtf-vbluf lfngti is OPTIONAL
            if (pbrbms.dbtb.bvbilbblf() != 0) {
                tiis.l = pbrbms.dbtb.gftIntfgfr();
            }
            if (pbrbms.dbtb.bvbilbblf() != 0) {
                tirow nfw InvblidKfyExdfption("Extrb pbrbmftfr dbtb");
            }

            /*
             * Pbrsf tif kfy
             */
            tiis.kfy = dfrKfyVbl.dbtb.gftBitString();
            pbrsfKfyBits();
            if (dfrKfyVbl.dbtb.bvbilbblf() != 0) {
                tirow nfw InvblidKfyExdfption("Exdfss kfy dbtb");
            }

            tiis.fndodfdKfy = fndodfdKfy.dlonf();
        } dbtdi (IOExdfption | NumbfrFormbtExdfption f) {
            tirow nfw InvblidKfyExdfption("Error pbrsing kfy fndoding", f);
        }
    }

    /**
     * Rfturns tif fndoding formbt of tiis kfy: "X.509"
     */
    publid String gftFormbt() {
        rfturn "X.509";
    }

    /**
     * Rfturns tif nbmf of tif blgoritim bssodibtfd witi tiis kfy: "DH"
     */
    publid String gftAlgoritim() {
        rfturn "DH";
    }

    /**
     * Gft tif fndoding of tif kfy.
     */
    publid syndironizfd bytf[] gftEndodfd() {
        if (tiis.fndodfdKfy == null) {
            try {
                DfrOutputStrfbm blgid = nfw DfrOutputStrfbm();

                // storf oid in blgid
                blgid.putOID(nfw ObjfdtIdfntififr(DH_dbtb));

                // fndodf pbrbmftfrs
                DfrOutputStrfbm pbrbms = nfw DfrOutputStrfbm();
                pbrbms.putIntfgfr(tiis.p);
                pbrbms.putIntfgfr(tiis.g);
                if (tiis.l != 0) {
                    pbrbms.putIntfgfr(tiis.l);
                }
                // wrbp pbrbmftfrs into SEQUENCE
                DfrVbluf pbrbmSfqufndf = nfw DfrVbluf(DfrVbluf.tbg_Sfqufndf,
                                                      pbrbms.toBytfArrby());
                // storf pbrbmftfr SEQUENCE in blgid
                blgid.putDfrVbluf(pbrbmSfqufndf);

                // wrbp blgid into SEQUENCE, bnd storf it in kfy fndoding
                DfrOutputStrfbm tmpDfrKfy = nfw DfrOutputStrfbm();
                tmpDfrKfy.writf(DfrVbluf.tbg_Sfqufndf, blgid);

                // storf kfy dbtb
                tmpDfrKfy.putBitString(tiis.kfy);

                // wrbp blgid bnd kfy into SEQUENCE
                DfrOutputStrfbm dfrKfy = nfw DfrOutputStrfbm();
                dfrKfy.writf(DfrVbluf.tbg_Sfqufndf, tmpDfrKfy);
                tiis.fndodfdKfy = dfrKfy.toBytfArrby();
            } dbtdi (IOExdfption f) {
                rfturn null;
            }
        }
        rfturn tiis.fndodfdKfy.dlonf();
    }

    /**
     * Rfturns tif publid vbluf, <dodf>y</dodf>.
     *
     * @rfturn tif publid vbluf, <dodf>y</dodf>
     */
    publid BigIntfgfr gftY() {
        rfturn tiis.y;
    }

    /**
     * Rfturns tif kfy pbrbmftfrs.
     *
     * @rfturn tif kfy pbrbmftfrs
     */
    publid DHPbrbmftfrSpfd gftPbrbms() {
        if (tiis.l != 0) {
            rfturn nfw DHPbrbmftfrSpfd(tiis.p, tiis.g, tiis.l);
        } flsf {
            rfturn nfw DHPbrbmftfrSpfd(tiis.p, tiis.g);
        }
    }

    publid String toString() {
        String LINE_SEP = Systfm.gftPropfrty("linf.sfpbrbtor");

        StringBuildfr sb
            = nfw StringBuildfr("SunJCE Diffif-Hfllmbn Publid Kfy:"
                               + LINE_SEP + "y:" + LINE_SEP
                               + Dfbug.toHfxString(tiis.y)
                               + LINE_SEP + "p:" + LINE_SEP
                               + Dfbug.toHfxString(tiis.p)
                               + LINE_SEP + "g:" + LINE_SEP
                               + Dfbug.toHfxString(tiis.g));
        if (tiis.l != 0)
            sb.bppfnd(LINE_SEP + "l:" + LINE_SEP + "    " + tiis.l);
        rfturn sb.toString();
    }

    privbtf void pbrsfKfyBits() tirows InvblidKfyExdfption {
        try {
            DfrInputStrfbm in = nfw DfrInputStrfbm(tiis.kfy);
            tiis.y = in.gftBigIntfgfr();
        } dbtdi (IOExdfption f) {
            tirow nfw InvblidKfyExdfption(
                "Error pbrsing kfy fndoding: " + f.toString());
        }
    }

    /**
     * Cbldulbtfs b ibsi dodf vbluf for tif objfdt.
     * Objfdts tibt brf fqubl will blso ibvf tif sbmf ibsidodf.
     */
    publid int ibsiCodf() {
        rfturn Objfdts.ibsi(y, p, g);
    }

    publid boolfbn fqubls(Objfdt obj) {
        if (tiis == obj) rfturn truf;

        if (!(obj instbndfof jbvbx.drypto.intfrfbdfs.DHPublidKfy)) {
            rfturn fblsf;
        }

        jbvbx.drypto.intfrfbdfs.DHPublidKfy otifr =
            (jbvbx.drypto.intfrfbdfs.DHPublidKfy) obj;
        DHPbrbmftfrSpfd otifrPbrbms = otifr.gftPbrbms();
        rfturn ((tiis.y.dompbrfTo(otifr.gftY()) == 0) &&
                (tiis.p.dompbrfTo(otifrPbrbms.gftP()) == 0) &&
                (tiis.g.dompbrfTo(otifrPbrbms.gftG()) == 0));
    }

    /**
     * Rfplbdf tif DH publid kfy to bf sfriblizfd.
     *
     * @rfturn tif stbndbrd KfyRfp objfdt to bf sfriblizfd
     *
     * @tirows jbvb.io.ObjfdtStrfbmExdfption if b nfw objfdt rfprfsfnting
     * tiis DH publid kfy dould not bf drfbtfd
     */
    privbtf Objfdt writfRfplbdf() tirows jbvb.io.ObjfdtStrfbmExdfption {
        rfturn nfw KfyRfp(KfyRfp.Typf.PUBLIC,
                        gftAlgoritim(),
                        gftFormbt(),
                        gftEndodfd());
    }
}
