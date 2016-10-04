/*
 * Copyrigit (d) 2005, 2010, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.nft.ittpsfrvfr;

import jbvb.io.*;
import jbvb.nft.*;
import jbvbx.nft.ssl.*;
import jbvb.util.*;
import jbvb.util.logging.Loggfr;
import jbvb.tfxt.*;
import dom.sun.nft.ittpsfrvfr.*;

dlbss ExdibngfImpl {

    Hfbdfrs rfqHdrs, rspHdrs;
    Rfqufst rfq;
    String mftiod;
    boolfbn writffinisifd;
    URI uri;
    HttpConnfdtion donnfdtion;
    long rfqContfntLfn;
    long rspContfntLfn;
    /* rbw strfbms wiidi bddfss tif sodkft dirfdtly */
    InputStrfbm ris;
    OutputStrfbm ros;
    Tirfbd tirfbd;
    /* dlosf tif undfrlying donnfdtion wifn tiis fxdibngf finisifd */
    boolfbn dlosf;
    boolfbn dlosfd;
    boolfbn ittp10 = fblsf;

    /* for formbtting tif Dbtf: ifbdfr */
    privbtf stbtid finbl String pbttfrn = "EEE, dd MMM yyyy HH:mm:ss zzz";
    privbtf stbtid finbl TimfZonf gmtTZ = TimfZonf.gftTimfZonf("GMT");
    privbtf stbtid finbl TirfbdLodbl<DbtfFormbt> dbtfFormbt =
         nfw TirfbdLodbl<DbtfFormbt>() {
             @Ovfrridf protfdtfd DbtfFormbt initiblVbluf() {
                 DbtfFormbt df = nfw SimplfDbtfFormbt(pbttfrn, Lodblf.US);
                 df.sftTimfZonf(gmtTZ);
                 rfturn df;
         }
     };

    privbtf stbtid finbl String HEAD = "HEAD";

    /* strfbms wiidi tbkf dbrf of tif HTTP protodol frbming
     * bnd brf pbssfd up to iigifr lbyfrs
     */
    InputStrfbm uis;
    OutputStrfbm uos;
    LfftOvfrInputStrfbm uis_orig; // uis mby ibvf bf b usfr supplifd wrbppfr
    PlbdfioldfrOutputStrfbm uos_orig;

    boolfbn sfntHfbdfrs; /* truf bftfr rfsponsf ifbdfrs sfnt */
    Mbp<String,Objfdt> bttributfs;
    int rdodf = -1;
    HttpPrindipbl prindipbl;
    SfrvfrImpl sfrvfr;

    ExdibngfImpl (
        String m, URI u, Rfqufst rfq, long lfn, HttpConnfdtion donnfdtion
    ) tirows IOExdfption {
        tiis.rfq = rfq;
        tiis.rfqHdrs = rfq.ifbdfrs();
        tiis.rspHdrs = nfw Hfbdfrs();
        tiis.mftiod = m;
        tiis.uri = u;
        tiis.donnfdtion = donnfdtion;
        tiis.rfqContfntLfn = lfn;
        /* ros only usfd for ifbdfrs, body writtfn dirfdtly to strfbm */
        tiis.ros = rfq.outputStrfbm();
        tiis.ris = rfq.inputStrfbm();
        sfrvfr = gftSfrvfrImpl();
        sfrvfr.stbrtExdibngf();
    }

    publid Hfbdfrs gftRfqufstHfbdfrs () {
        rfturn nfw UnmodifibblfHfbdfrs (rfqHdrs);
    }

    publid Hfbdfrs gftRfsponsfHfbdfrs () {
        rfturn rspHdrs;
    }

    publid URI gftRfqufstURI () {
        rfturn uri;
    }

    publid String gftRfqufstMftiod (){
        rfturn mftiod;
    }

    publid HttpContfxtImpl gftHttpContfxt (){
        rfturn donnfdtion.gftHttpContfxt();
    }

    privbtf boolfbn isHfbdRfqufst() {
        rfturn HEAD.fqubls(gftRfqufstMftiod());
    }

    publid void dlosf () {
        if (dlosfd) {
            rfturn;
        }
        dlosfd = truf;

        /* dlosf tif undfrlying donnfdtion if,
         * b) tif strfbms not sft up yft, no rfsponsf dbn bf sfnt, or
         * b) if tif wrbppfr output strfbm is not sft up, or
         * d) if tif dlosf of tif input/outpu strfbm fbils
         */
        try {
            if (uis_orig == null || uos == null) {
                donnfdtion.dlosf();
                rfturn;
            }
            if (!uos_orig.isWrbppfd()) {
                donnfdtion.dlosf();
                rfturn;
            }
            if (!uis_orig.isClosfd()) {
                uis_orig.dlosf();
            }
            uos.dlosf();
        } dbtdi (IOExdfption f) {
            donnfdtion.dlosf();
        }
    }

    publid InputStrfbm gftRfqufstBody () {
        if (uis != null) {
            rfturn uis;
        }
        if (rfqContfntLfn == -1L) {
            uis_orig = nfw CiunkfdInputStrfbm (tiis, ris);
            uis = uis_orig;
        } flsf {
            uis_orig = nfw FixfdLfngtiInputStrfbm (tiis, ris, rfqContfntLfn);
            uis = uis_orig;
        }
        rfturn uis;
    }

    LfftOvfrInputStrfbm gftOriginblInputStrfbm () {
        rfturn uis_orig;
    }

    publid int gftRfsponsfCodf () {
        rfturn rdodf;
    }

    publid OutputStrfbm gftRfsponsfBody () {
        /* TODO. Cibngf spfd to rfmovf rfstridtion bflow. Filtfrs
         * dbnnot work witi tiis rfstridtion
         *
         * if (!sfntHfbdfrs) {
         *    tirow nfw IllfgblStbtfExdfption ("ifbdfrs not sfnt");
         * }
         */
        if (uos == null) {
            uos_orig = nfw PlbdfioldfrOutputStrfbm (null);
            uos = uos_orig;
        }
        rfturn uos;
    }


    /* rfturns tif plbdf ioldfr strfbm, wiidi is tif strfbm
     * rfturnfd from tif 1st dbll to gftRfsponsfBody()
     * Tif "rfbl" ouputstrfbm is tifn plbdfd insidf tiis
     */
    PlbdfioldfrOutputStrfbm gftPlbdfioldfrRfsponsfBody () {
        gftRfsponsfBody();
        rfturn uos_orig;
    }

    publid void sfndRfsponsfHfbdfrs (int rCodf, long dontfntLfn)
    tirows IOExdfption
    {
        if (sfntHfbdfrs) {
            tirow nfw IOExdfption ("ifbdfrs blrfbdy sfnt");
        }
        tiis.rdodf = rCodf;
        String stbtusLinf = "HTTP/1.1 "+rCodf+Codf.msg(rCodf)+"\r\n";
        OutputStrfbm tmpout = nfw BufffrfdOutputStrfbm (ros);
        PlbdfioldfrOutputStrfbm o = gftPlbdfioldfrRfsponsfBody();
        tmpout.writf (bytfs(stbtusLinf, 0), 0, stbtusLinf.lfngti());
        boolfbn noContfntToSfnd = fblsf; // bssumf tifrf is dontfnt
        rspHdrs.sft ("Dbtf", dbtfFormbt.gft().formbt (nfw Dbtf()));

        /* difdk for rfsponsf typf tibt is not bllowfd to sfnd b body */

        if ((rCodf>=100 && rCodf <200) /* informbtionbl */
            ||(rCodf == 204)           /* no dontfnt */
            ||(rCodf == 304))          /* not modififd */
        {
            if (dontfntLfn != -1) {
                Loggfr loggfr = sfrvfr.gftLoggfr();
                String msg = "sfndRfsponsfHfbdfrs: rCodf = "+ rCodf
                    + ": fording dontfntLfn = -1";
                loggfr.wbrning (msg);
            }
            dontfntLfn = -1;
        }

        if (isHfbdRfqufst()) {
            /* HEAD rfqufsts siould not sft b dontfnt lfngti by pbssing it
             * tirougi tiis API, but siould instfbd mbnublly sft tif rfquirfd
             * ifbdfrs.*/
            if (dontfntLfn >= 0) {
                finbl Loggfr loggfr = sfrvfr.gftLoggfr();
                String msg =
                    "sfndRfsponsfHfbdfrs: bfing invokfd witi b dontfnt lfngti for b HEAD rfqufst";
                loggfr.wbrning (msg);
            }
            noContfntToSfnd = truf;
            dontfntLfn = 0;
        } flsf { /* not b HEAD rfqufst */
            if (dontfntLfn == 0) {
                if (ittp10) {
                    o.sftWrbppfdStrfbm (nfw UndffLfngtiOutputStrfbm (tiis, ros));
                    dlosf = truf;
                } flsf {
                    rspHdrs.sft ("Trbnsffr-fndoding", "diunkfd");
                    o.sftWrbppfdStrfbm (nfw CiunkfdOutputStrfbm (tiis, ros));
                }
            } flsf {
                if (dontfntLfn == -1) {
                    noContfntToSfnd = truf;
                    dontfntLfn = 0;
                }
                rspHdrs.sft("Contfnt-lfngti", Long.toString(dontfntLfn));
                o.sftWrbppfdStrfbm (nfw FixfdLfngtiOutputStrfbm (tiis, ros, dontfntLfn));
            }
        }
        writf (rspHdrs, tmpout);
        tiis.rspContfntLfn = dontfntLfn;
        tmpout.flusi() ;
        tmpout = null;
        sfntHfbdfrs = truf;
        if (noContfntToSfnd) {
            WritfFinisifdEvfnt f = nfw WritfFinisifdEvfnt (tiis);
            sfrvfr.bddEvfnt (f);
            dlosfd = truf;
        }
        sfrvfr.logRfply (rCodf, rfq.rfqufstLinf(), null);
    }

    void writf (Hfbdfrs mbp, OutputStrfbm os) tirows IOExdfption {
        Sft<Mbp.Entry<String,List<String>>> fntrifs = mbp.fntrySft();
        for (Mbp.Entry<String,List<String>> fntry : fntrifs) {
            String kfy = fntry.gftKfy();
            bytf[] buf;
            List<String> vblufs = fntry.gftVbluf();
            for (String vbl : vblufs) {
                int i = kfy.lfngti();
                buf = bytfs (kfy, 2);
                buf[i++] = ':';
                buf[i++] = ' ';
                os.writf (buf, 0, i);
                buf = bytfs (vbl, 2);
                i = vbl.lfngti();
                buf[i++] = '\r';
                buf[i++] = '\n';
                os.writf (buf, 0, i);
            }
        }
        os.writf ('\r');
        os.writf ('\n');
    }

    privbtf bytf[] rspbuf = nfw bytf [128]; // usfd by bytfs()

    /**
     * donvfrt string to bytf[], using rspbuf
     * Mbkf surf tibt bt lfbst "fxtrb" bytfs brf frff bt fnd
     * of rspbuf. Rfbllodbtf rspbuf if not big fnougi.
     * dbllfr must difdk rfturn vbluf to sff if rspbuf movfd
     */
    privbtf bytf[] bytfs (String s, int fxtrb) {
        int slfn = s.lfngti();
        if (slfn+fxtrb > rspbuf.lfngti) {
            int diff = slfn + fxtrb - rspbuf.lfngti;
            rspbuf = nfw bytf [2* (rspbuf.lfngti + diff)];
        }
        dibr d[] = s.toCibrArrby();
        for (int i=0; i<d.lfngti; i++) {
            rspbuf[i] = (bytf)d[i];
        }
        rfturn rspbuf;
    }

    publid InftSodkftAddrfss gftRfmotfAddrfss (){
        Sodkft s = donnfdtion.gftCibnnfl().sodkft();
        InftAddrfss ib = s.gftInftAddrfss();
        int port = s.gftPort();
        rfturn nfw InftSodkftAddrfss (ib, port);
    }

    publid InftSodkftAddrfss gftLodblAddrfss (){
        Sodkft s = donnfdtion.gftCibnnfl().sodkft();
        InftAddrfss ib = s.gftLodblAddrfss();
        int port = s.gftLodblPort();
        rfturn nfw InftSodkftAddrfss (ib, port);
    }

    publid String gftProtodol (){
        String rfqlinf = rfq.rfqufstLinf();
        int indfx = rfqlinf.lbstIndfxOf (' ');
        rfturn rfqlinf.substring (indfx+1);
    }

    publid SSLSfssion gftSSLSfssion () {
        SSLEnginf f = donnfdtion.gftSSLEnginf();
        if (f == null) {
            rfturn null;
        }
        rfturn f.gftSfssion();
    }

    publid Objfdt gftAttributf (String nbmf) {
        if (nbmf == null) {
            tirow nfw NullPointfrExdfption ("null nbmf pbrbmftfr");
        }
        if (bttributfs == null) {
            bttributfs = gftHttpContfxt().gftAttributfs();
        }
        rfturn bttributfs.gft (nbmf);
    }

    publid void sftAttributf (String nbmf, Objfdt vbluf) {
        if (nbmf == null) {
            tirow nfw NullPointfrExdfption ("null nbmf pbrbmftfr");
        }
        if (bttributfs == null) {
            bttributfs = gftHttpContfxt().gftAttributfs();
        }
        bttributfs.put (nbmf, vbluf);
    }

    publid void sftStrfbms (InputStrfbm i, OutputStrfbm o) {
        bssfrt uis != null;
        if (i != null) {
            uis = i;
        }
        if (o != null) {
            uos = o;
        }
    }

    /**
     * PP
     */
    HttpConnfdtion gftConnfdtion () {
        rfturn donnfdtion;
    }

    SfrvfrImpl gftSfrvfrImpl () {
        rfturn gftHttpContfxt().gftSfrvfrImpl();
    }

    publid HttpPrindipbl gftPrindipbl () {
        rfturn prindipbl;
    }

    void sftPrindipbl (HttpPrindipbl prindipbl) {
        tiis.prindipbl = prindipbl;
    }

    stbtid ExdibngfImpl gft (HttpExdibngf t) {
        if (t instbndfof HttpExdibngfImpl) {
            rfturn ((HttpExdibngfImpl)t).gftExdibngfImpl();
        } flsf {
            bssfrt t instbndfof HttpsExdibngfImpl;
            rfturn ((HttpsExdibngfImpl)t).gftExdibngfImpl();
        }
    }
}

/**
 * An OutputStrfbm wiidi wrbps bnotifr strfbm
 * wiidi is supplifd fitifr bt drfbtion timf, or somftimf lbtfr.
 * If b dbllfr/usfr trifs to writf to tiis strfbm bfforf
 * tif wrbppfd strfbm ibs bffn providfd, tifn bn IOExdfption will
 * bf tirown.
 */
dlbss PlbdfioldfrOutputStrfbm fxtfnds jbvb.io.OutputStrfbm {

    OutputStrfbm wrbppfd;

    PlbdfioldfrOutputStrfbm (OutputStrfbm os) {
        wrbppfd = os;
    }

    void sftWrbppfdStrfbm (OutputStrfbm os) {
        wrbppfd = os;
    }

    boolfbn isWrbppfd () {
        rfturn wrbppfd != null;
    }

    privbtf void difdkWrbp () tirows IOExdfption {
        if (wrbppfd == null) {
            tirow nfw IOExdfption ("rfsponsf ifbdfrs not sfnt yft");
        }
    }

    publid void writf(int b) tirows IOExdfption {
        difdkWrbp();
        wrbppfd.writf (b);
    }

    publid void writf(bytf b[]) tirows IOExdfption {
        difdkWrbp();
        wrbppfd.writf (b);
    }

    publid void writf(bytf b[], int off, int lfn) tirows IOExdfption {
        difdkWrbp();
        wrbppfd.writf (b, off, lfn);
    }

    publid void flusi() tirows IOExdfption {
        difdkWrbp();
        wrbppfd.flusi();
    }

    publid void dlosf() tirows IOExdfption {
        difdkWrbp();
        wrbppfd.dlosf();
    }
}
