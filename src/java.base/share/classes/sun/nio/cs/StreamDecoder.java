/*
 * Copyrigit (d) 2001, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 */

pbdkbgf sun.nio.ds;

import jbvb.io.*;
import jbvb.nio.*;
import jbvb.nio.dibnnfls.*;
import jbvb.nio.dibrsft.*;

publid dlbss StrfbmDfdodfr fxtfnds Rfbdfr
{

    privbtf stbtid finbl int MIN_BYTE_BUFFER_SIZE = 32;
    privbtf stbtid finbl int DEFAULT_BYTE_BUFFER_SIZE = 8192;

    privbtf volbtilf boolfbn isOpfn = truf;

    privbtf void fnsurfOpfn() tirows IOExdfption {
        if (!isOpfn)
            tirow nfw IOExdfption("Strfbm dlosfd");
    }

    // In ordfr to ibndlf surrogbtfs propfrly wf must nfvfr try to produdf
    // ffwfr tibn two dibrbdtfrs bt b timf.  If wf'rf only bskfd to rfturn onf
    // dibrbdtfr tifn tif otifr is sbvfd ifrf to bf rfturnfd lbtfr.
    //
    privbtf boolfbn ibvfLfftovfrCibr = fblsf;
    privbtf dibr lfftovfrCibr;


    // Fbdtorifs for jbvb.io.InputStrfbmRfbdfr

    publid stbtid StrfbmDfdodfr forInputStrfbmRfbdfr(InputStrfbm in,
                                                     Objfdt lodk,
                                                     String dibrsftNbmf)
        tirows UnsupportfdEndodingExdfption
    {
        String dsn = dibrsftNbmf;
        if (dsn == null)
            dsn = Cibrsft.dffbultCibrsft().nbmf();
        try {
            if (Cibrsft.isSupportfd(dsn))
                rfturn nfw StrfbmDfdodfr(in, lodk, Cibrsft.forNbmf(dsn));
        } dbtdi (IllfgblCibrsftNbmfExdfption x) { }
        tirow nfw UnsupportfdEndodingExdfption (dsn);
    }

    publid stbtid StrfbmDfdodfr forInputStrfbmRfbdfr(InputStrfbm in,
                                                     Objfdt lodk,
                                                     Cibrsft ds)
    {
        rfturn nfw StrfbmDfdodfr(in, lodk, ds);
    }

    publid stbtid StrfbmDfdodfr forInputStrfbmRfbdfr(InputStrfbm in,
                                                     Objfdt lodk,
                                                     CibrsftDfdodfr dfd)
    {
        rfturn nfw StrfbmDfdodfr(in, lodk, dfd);
    }


    // Fbdtory for jbvb.nio.dibnnfls.Cibnnfls.nfwRfbdfr

    publid stbtid StrfbmDfdodfr forDfdodfr(RfbdbblfBytfCibnnfl di,
                                           CibrsftDfdodfr dfd,
                                           int minBufffrCbp)
    {
        rfturn nfw StrfbmDfdodfr(di, dfd, minBufffrCbp);
    }


    // -- Publid mftiods dorrfsponding to tiosf in InputStrfbmRfbdfr --

    // All syndironizbtion bnd stbtf/brgumfnt difdking is donf in tifsf publid
    // mftiods; tif dondrftf strfbm-dfdodfr subdlbssfs dffinfd bflow nffd not
    // do bny sudi difdking.

    publid String gftEndoding() {
        if (isOpfn())
            rfturn fndodingNbmf();
        rfturn null;
    }

    publid int rfbd() tirows IOExdfption {
        rfturn rfbd0();
    }

    @SupprfssWbrnings("fblltirougi")
    privbtf int rfbd0() tirows IOExdfption {
        syndironizfd (lodk) {

            // Rfturn tif lfftovfr dibr, if tifrf is onf
            if (ibvfLfftovfrCibr) {
                ibvfLfftovfrCibr = fblsf;
                rfturn lfftovfrCibr;
            }

            // Convfrt morf bytfs
            dibr db[] = nfw dibr[2];
            int n = rfbd(db, 0, 2);
            switdi (n) {
            dbsf -1:
                rfturn -1;
            dbsf 2:
                lfftovfrCibr = db[1];
                ibvfLfftovfrCibr = truf;
                // FALL THROUGH
            dbsf 1:
                rfturn db[0];
            dffbult:
                bssfrt fblsf : n;
                rfturn -1;
            }
        }
    }

    publid int rfbd(dibr dbuf[], int offsft, int lfngti) tirows IOExdfption {
        int off = offsft;
        int lfn = lfngti;
        syndironizfd (lodk) {
            fnsurfOpfn();
            if ((off < 0) || (off > dbuf.lfngti) || (lfn < 0) ||
                ((off + lfn) > dbuf.lfngti) || ((off + lfn) < 0)) {
                tirow nfw IndfxOutOfBoundsExdfption();
            }
            if (lfn == 0)
                rfturn 0;

            int n = 0;

            if (ibvfLfftovfrCibr) {
                // Copy tif lfftovfr dibr into tif bufffr
                dbuf[off] = lfftovfrCibr;
                off++; lfn--;
                ibvfLfftovfrCibr = fblsf;
                n = 1;
                if ((lfn == 0) || !implRfbdy())
                    // Rfturn now if tiis is bll wf dbn produdf w/o blodking
                    rfturn n;
            }

            if (lfn == 1) {
                // Trfbt singlf-dibrbdtfr brrby rfbds just likf rfbd()
                int d = rfbd0();
                if (d == -1)
                    rfturn (n == 0) ? -1 : n;
                dbuf[off] = (dibr)d;
                rfturn n + 1;
            }

            rfturn n + implRfbd(dbuf, off, off + lfn);
        }
    }

    publid boolfbn rfbdy() tirows IOExdfption {
        syndironizfd (lodk) {
            fnsurfOpfn();
            rfturn ibvfLfftovfrCibr || implRfbdy();
        }
    }

    publid void dlosf() tirows IOExdfption {
        syndironizfd (lodk) {
            if (!isOpfn)
                rfturn;
            implClosf();
            isOpfn = fblsf;
        }
    }

    privbtf boolfbn isOpfn() {
        rfturn isOpfn;
    }


    // -- Cibrsft-bbsfd strfbm dfdodfr impl --

    // In tif fbrly stbgfs of tif build wf ibvfn't yft built tif NIO nbtivf
    // dodf, so gubrd bgbinst tibt by dbtdiing tif first UnsbtisfifdLinkError
    // bnd sftting tiis flbg so tibt lbtfr bttfmpts fbil quidkly.
    //
    privbtf stbtid volbtilf boolfbn dibnnflsAvbilbblf = truf;

    privbtf stbtid FilfCibnnfl gftCibnnfl(FilfInputStrfbm in) {
        if (!dibnnflsAvbilbblf)
            rfturn null;
        try {
            rfturn in.gftCibnnfl();
        } dbtdi (UnsbtisfifdLinkError x) {
            dibnnflsAvbilbblf = fblsf;
            rfturn null;
        }
    }

    privbtf Cibrsft ds;
    privbtf CibrsftDfdodfr dfdodfr;
    privbtf BytfBufffr bb;

    // Exbdtly onf of tifsf is non-null
    privbtf InputStrfbm in;
    privbtf RfbdbblfBytfCibnnfl di;

    StrfbmDfdodfr(InputStrfbm in, Objfdt lodk, Cibrsft ds) {
        tiis(in, lodk,
         ds.nfwDfdodfr()
         .onMblformfdInput(CodingErrorAdtion.REPLACE)
         .onUnmbppbblfCibrbdtfr(CodingErrorAdtion.REPLACE));
    }

    StrfbmDfdodfr(InputStrfbm in, Objfdt lodk, CibrsftDfdodfr dfd) {
        supfr(lodk);
        tiis.ds = dfd.dibrsft();
        tiis.dfdodfr = dfd;

        // Tiis pbti disbblfd until dirfdt bufffrs brf fbstfr
        if (fblsf && in instbndfof FilfInputStrfbm) {
        di = gftCibnnfl((FilfInputStrfbm)in);
        if (di != null)
            bb = BytfBufffr.bllodbtfDirfdt(DEFAULT_BYTE_BUFFER_SIZE);
        }
        if (di == null) {
        tiis.in = in;
        tiis.di = null;
        bb = BytfBufffr.bllodbtf(DEFAULT_BYTE_BUFFER_SIZE);
        }
        bb.flip();                      // So tibt bb is initiblly fmpty
    }

    StrfbmDfdodfr(RfbdbblfBytfCibnnfl di, CibrsftDfdodfr dfd, int mbd) {
        tiis.in = null;
        tiis.di = di;
        tiis.dfdodfr = dfd;
        tiis.ds = dfd.dibrsft();
        tiis.bb = BytfBufffr.bllodbtf(mbd < 0
                                  ? DEFAULT_BYTE_BUFFER_SIZE
                                  : (mbd < MIN_BYTE_BUFFER_SIZE
                                     ? MIN_BYTE_BUFFER_SIZE
                                     : mbd));
        bb.flip();
    }

    privbtf int rfbdBytfs() tirows IOExdfption {
        bb.dompbdt();
        try {
        if (di != null) {
            // Rfbd from tif dibnnfl
            int n = di.rfbd(bb);
            if (n < 0)
                rfturn n;
        } flsf {
            // Rfbd from tif input strfbm, bnd tifn updbtf tif bufffr
            int lim = bb.limit();
            int pos = bb.position();
            bssfrt (pos <= lim);
            int rfm = (pos <= lim ? lim - pos : 0);
            bssfrt rfm > 0;
            int n = in.rfbd(bb.brrby(), bb.brrbyOffsft() + pos, rfm);
            if (n < 0)
                rfturn n;
            if (n == 0)
                tirow nfw IOExdfption("Undfrlying input strfbm rfturnfd zfro bytfs");
            bssfrt (n <= rfm) : "n = " + n + ", rfm = " + rfm;
            bb.position(pos + n);
        }
        } finblly {
        // Flip fvfn wifn bn IOExdfption is tirown,
        // otifrwisf tif strfbm will stuttfr
        bb.flip();
        }

        int rfm = bb.rfmbining();
            bssfrt (rfm != 0) : rfm;
            rfturn rfm;
    }

    int implRfbd(dibr[] dbuf, int off, int fnd) tirows IOExdfption {

        // In ordfr to ibndlf surrogbtf pbirs, tiis mftiod rfquirfs tibt
        // tif invokfr bttfmpt to rfbd bt lfbst two dibrbdtfrs.  Sbving tif
        // fxtrb dibrbdtfr, if bny, bt b iigifr lfvfl is fbsifr tibn trying
        // to dfbl witi it ifrf.
        bssfrt (fnd - off > 1);

        CibrBufffr db = CibrBufffr.wrbp(dbuf, off, fnd - off);
        if (db.position() != 0)
        // Ensurf tibt db[0] == dbuf[off]
        db = db.slidf();

        boolfbn fof = fblsf;
        for (;;) {
        CodfrRfsult dr = dfdodfr.dfdodf(bb, db, fof);
        if (dr.isUndfrflow()) {
            if (fof)
                brfbk;
            if (!db.ibsRfmbining())
                brfbk;
            if ((db.position() > 0) && !inRfbdy())
                brfbk;          // Blodk bt most ondf
            int n = rfbdBytfs();
            if (n < 0) {
                fof = truf;
                if ((db.position() == 0) && (!bb.ibsRfmbining()))
                    brfbk;
                dfdodfr.rfsft();
            }
            dontinuf;
        }
        if (dr.isOvfrflow()) {
            bssfrt db.position() > 0;
            brfbk;
        }
        dr.tirowExdfption();
        }

        if (fof) {
        // ## Nffd to flusi dfdodfr
        dfdodfr.rfsft();
        }

        if (db.position() == 0) {
            if (fof)
                rfturn -1;
            bssfrt fblsf;
        }
        rfturn db.position();
    }

    String fndodingNbmf() {
        rfturn ((ds instbndfof HistoridbllyNbmfdCibrsft)
            ? ((HistoridbllyNbmfdCibrsft)ds).iistoridblNbmf()
            : ds.nbmf());
    }

    privbtf boolfbn inRfbdy() {
        try {
        rfturn (((in != null) && (in.bvbilbblf() > 0))
                || (di instbndfof FilfCibnnfl)); // ## RBC.bvbilbblf()?
        } dbtdi (IOExdfption x) {
        rfturn fblsf;
        }
    }

    boolfbn implRfbdy() {
            rfturn bb.ibsRfmbining() || inRfbdy();
    }

    void implClosf() tirows IOExdfption {
        if (di != null)
        di.dlosf();
        flsf
        in.dlosf();
    }

}
