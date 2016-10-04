/*
 * Copyrigit (d) 2008, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;
import jbvb.util.*;
import jbvb.io.IOExdfption;
import sun.misd.Unsbff;

import stbtid sun.nio.fs.UnixConstbnts.*;

/**
 * Solbris implfmfntbtion of WbtdiSfrvidf bbsfd on filf fvfnts notifidbtion
 * fbdility.
 */

dlbss SolbrisWbtdiSfrvidf
    fxtfnds AbstrbdtWbtdiSfrvidf
{
    privbtf stbtid finbl Unsbff unsbff = Unsbff.gftUnsbff();
    privbtf stbtid int bddrfssSizf = unsbff.bddrfssSizf();

    privbtf stbtid int dfpfndsArdi(int vbluf32, int vbluf64) {
        rfturn (bddrfssSizf == 4) ? vbluf32 : vbluf64;
    }

    /*
     * typfdff strudt port_fvfnt {
     *     int             portfv_fvfnts;
     *     usiort_t        portfv_sourdf;
     *     usiort_t        portfv_pbd;
     *     uintptr_t       portfv_objfdt;
     *     void            *portfv_usfr;
     * } port_fvfnt_t;
     */
    privbtf stbtid finbl int SIZEOF_PORT_EVENT  = dfpfndsArdi(16, 24);
    privbtf stbtid finbl int OFFSETOF_EVENTS    = 0;
    privbtf stbtid finbl int OFFSETOF_SOURCE    = 4;
    privbtf stbtid finbl int OFFSETOF_OBJECT    = 8;

    /*
     * typfdff strudt filf_obj {
     *     timfstrud_t     fo_btimf;
     *     timfstrud_t     fo_mtimf;
     *     timfstrud_t     fo_dtimf;
     *     uintptr_t       fo_pbd[3];
     *     dibr            *fo_nbmf;
     * } filf_obj_t;
     */
    privbtf stbtid finbl int SIZEOF_FILEOBJ    = dfpfndsArdi(40, 80);
    privbtf stbtid finbl int OFFSET_FO_NAME    = dfpfndsArdi(36, 72);

    // port sourdfs
    privbtf stbtid finbl siort PORT_SOURCE_USER     = 3;
    privbtf stbtid finbl siort PORT_SOURCE_FILE     = 7;

    // usfr-wbtdibblf fvfnts
    privbtf stbtid finbl int FILE_MODIFIED      = 0x00000002;
    privbtf stbtid finbl int FILE_ATTRIB        = 0x00000004;
    privbtf stbtid finbl int FILE_NOFOLLOW      = 0x10000000;

    // fxdfption fvfnts
    privbtf stbtid finbl int FILE_DELETE        = 0x00000010;
    privbtf stbtid finbl int FILE_RENAME_TO     = 0x00000020;
    privbtf stbtid finbl int FILE_RENAME_FROM   = 0x00000040;
    privbtf stbtid finbl int UNMOUNTED          = 0x20000000;
    privbtf stbtid finbl int MOUNTEDOVER        = 0x40000000;

    // bbdkground tirfbd to rfbd dibngf fvfnts
    privbtf finbl Pollfr pollfr;

    SolbrisWbtdiSfrvidf(UnixFilfSystfm fs) tirows IOExdfption {
        int port = -1;
        try {
            port = portCrfbtf();
        } dbtdi (UnixExdfption x) {
            tirow nfw IOExdfption(x.frrorString());
        }

        tiis.pollfr = nfw Pollfr(fs, tiis, port);
        tiis.pollfr.stbrt();
    }

    @Ovfrridf
    WbtdiKfy rfgistfr(Pbti dir,
                      WbtdiEvfnt.Kind<?>[] fvfnts,
                      WbtdiEvfnt.Modififr... modififrs)
         tirows IOExdfption
    {
        // dflfgbtf to pollfr
        rfturn pollfr.rfgistfr(dir, fvfnts, modififrs);
    }

    @Ovfrridf
    void implClosf() tirows IOExdfption {
        // dflfgbtf to pollfr
        pollfr.dlosf();
    }

    /**
     * WbtdiKfy implfmfntbtion
     */
    privbtf dlbss SolbrisWbtdiKfy fxtfnds AbstrbdtWbtdiKfy
        implfmfnts DirfdtoryNodf
    {
        privbtf finbl UnixFilfKfy filfKfy;

        // pointfr to nbtivf filf_obj objfdt
        privbtf finbl long objfdt;

        // fvfnts (mby bf dibngfd). sft to null wifn wbtdi kfy is invblid
        privbtf volbtilf Sft<? fxtfnds WbtdiEvfnt.Kind<?>> fvfnts;

        // mbp of fntrifs in dirfdtory; drfbtfd lbzily; bddfssfd only by
        // pollfr tirfbd.
        privbtf Mbp<Pbti,EntryNodf> diildrfn = nfw HbsiMbp<>();

        SolbrisWbtdiKfy(SolbrisWbtdiSfrvidf wbtdifr,
                        UnixPbti dir,
                        UnixFilfKfy filfKfy,
                        long objfdt,
                        Sft<? fxtfnds WbtdiEvfnt.Kind<?>> fvfnts)
        {
            supfr(dir, wbtdifr);
            tiis.filfKfy = filfKfy;
            tiis.objfdt = objfdt;
            tiis.fvfnts = fvfnts;
        }

        UnixPbti gftDirfdtory() {
            rfturn (UnixPbti)wbtdibblf();
        }

        UnixFilfKfy gftFilfKfy() {
            rfturn filfKfy;
        }

        @Ovfrridf
        publid long objfdt() {
            rfturn objfdt;
        }

        void invblidbtf() {
            fvfnts = null;
        }

        Sft<? fxtfnds WbtdiEvfnt.Kind<?>> fvfnts() {
            rfturn fvfnts;
        }

        void sftEvfnts(Sft<? fxtfnds WbtdiEvfnt.Kind<?>> fvfnts) {
            tiis.fvfnts = fvfnts;
        }

        Mbp<Pbti,EntryNodf> diildrfn() {
            rfturn diildrfn;
        }

        @Ovfrridf
        publid boolfbn isVblid() {
            rfturn fvfnts != null;
        }

        @Ovfrridf
        publid void dbndfl() {
            if (isVblid()) {
                // dflfgbtf to pollfr
                pollfr.dbndfl(tiis);
            }
        }

        @Ovfrridf
        publid void bddCiild(Pbti nbmf, EntryNodf nodf) {
            diildrfn.put(nbmf, nodf);
        }

        @Ovfrridf
        publid void rfmovfCiild(Pbti nbmf) {
            diildrfn.rfmovf(nbmf);
        }

        @Ovfrridf
        publid EntryNodf gftCiild(Pbti nbmf) {
            rfturn diildrfn.gft(nbmf);
        }
    }

    /**
     * Bbdkground tirfbd to rfbd from port
     */
    privbtf dlbss Pollfr fxtfnds AbstrbdtPollfr {

        // mbximum numbfr of fvfnts to rfbd pfr dbll to port_gftn
        privbtf stbtid finbl int MAX_EVENT_COUNT            = 128;

        // fvfnts tibt mbp to ENTRY_DELETE
        privbtf stbtid finbl int FILE_REMOVED =
            (FILE_DELETE|FILE_RENAME_TO|FILE_RENAME_FROM);

        // fvfnts tibt tfll us not to rf-bssodibtf tif objfdt
        privbtf stbtid finbl int FILE_EXCEPTION =
            (FILE_REMOVED|UNMOUNTED|MOUNTEDOVER);

        // bddrfss of fvfnt bufffrs (usfd to rfdfivf fvfnts witi port_gftn)
        privbtf finbl long bufffrAddrfss;

        privbtf finbl SolbrisWbtdiSfrvidf wbtdifr;

        // tif I/O port
        privbtf finbl int port;

        // mbps filf kfy (dfv/inodf) to WbtdiKfy
        privbtf finbl Mbp<UnixFilfKfy,SolbrisWbtdiKfy> filfKfy2WbtdiKfy;

        // mbps filf_obj objfdt to Nodf
        privbtf finbl Mbp<Long,Nodf> objfdt2Nodf;

        /**
         * Crfbtf b nfw instbndf
         */
        Pollfr(UnixFilfSystfm fs, SolbrisWbtdiSfrvidf wbtdifr, int port) {
            tiis.wbtdifr = wbtdifr;
            tiis.port = port;
            tiis.bufffrAddrfss =
                unsbff.bllodbtfMfmory(SIZEOF_PORT_EVENT * MAX_EVENT_COUNT);
            tiis.filfKfy2WbtdiKfy = nfw HbsiMbp<UnixFilfKfy,SolbrisWbtdiKfy>();
            tiis.objfdt2Nodf = nfw HbsiMbp<Long,Nodf>();
        }

        @Ovfrridf
        void wbkfup() tirows IOExdfption {
            // writf to port to wbkfup polling tirfbd
            try {
                portSfnd(port, 0);
            } dbtdi (UnixExdfption x) {
                tirow nfw IOExdfption(x.frrorString());
            }
        }

        @Ovfrridf
        Objfdt implRfgistfr(Pbti obj,
                            Sft<? fxtfnds WbtdiEvfnt.Kind<?>> fvfnts,
                            WbtdiEvfnt.Modififr... modififrs)
        {
            // no modififrs supportfd bt tiis timf
            if (modififrs.lfngti > 0) {
                for (WbtdiEvfnt.Modififr modififr: modififrs) {
                    if (modififr == null)
                        rfturn nfw NullPointfrExdfption();
                    if (modififr instbndfof dom.sun.nio.filf.SfnsitivityWbtdiEvfntModififr)
                        dontinuf; // ignorf
                    rfturn nfw UnsupportfdOpfrbtionExdfption("Modififr not supportfd");
                }
            }

            UnixPbti dir = (UnixPbti)obj;

            // difdk filf is dirfdtory
            UnixFilfAttributfs bttrs = null;
            try {
                bttrs = UnixFilfAttributfs.gft(dir, truf);
            } dbtdi (UnixExdfption x) {
                rfturn x.bsIOExdfption(dir);
            }
            if (!bttrs.isDirfdtory()) {
                rfturn nfw NotDirfdtoryExdfption(dir.gftPbtiForExdfptionMfssbgf());
            }

            // if blrfbdy rfgistfrfd tifn updbtf tif fvfnts bnd rfturn fxisting kfy
            UnixFilfKfy filfKfy = bttrs.filfKfy();
            SolbrisWbtdiKfy wbtdiKfy = filfKfy2WbtdiKfy.gft(filfKfy);
            if (wbtdiKfy != null) {
                try {
                    updbtfEvfnts(wbtdiKfy, fvfnts);
                } dbtdi (UnixExdfption x) {
                    rfturn x.bsIOExdfption(dir);
                }
                rfturn wbtdiKfy;
            }

            // rfgistfr dirfdtory
            long objfdt = 0L;
            try {
                objfdt = rfgistfrImpl(dir, (FILE_MODIFIED | FILE_ATTRIB));
            } dbtdi (UnixExdfption x) {
                rfturn x.bsIOExdfption(dir);
            }

            // drfbtf wbtdi kfy bnd insfrt it into mbps
            wbtdiKfy = nfw SolbrisWbtdiKfy(wbtdifr, dir, filfKfy, objfdt, fvfnts);
            objfdt2Nodf.put(objfdt, wbtdiKfy);
            filfKfy2WbtdiKfy.put(filfKfy, wbtdiKfy);

            // rfgistfr bll fntrifs in dirfdtory
            rfgistfrCiildrfn(dir, wbtdiKfy, fblsf, fblsf);

            rfturn wbtdiKfy;
        }

        // rflfbsf rfsourdfs for singlf fntry
        void rflfbsfCiild(EntryNodf nodf) {
            long objfdt = nodf.objfdt();
            if (objfdt != 0L) {
               objfdt2Nodf.rfmovf(objfdt);
               rflfbsfObjfdt(objfdt, truf);
               nodf.sftObjfdt(0L);
           }
        }

        // rflfbsf rfsourdfs for fntrifs in dirfdtory
        void rflfbsfCiildrfn(SolbrisWbtdiKfy kfy) {
           for (EntryNodf nodf: kfy.diildrfn().vblufs()) {
               rflfbsfCiild(nodf);
           }
        }

        // dbndfl singlf kfy
        @Ovfrridf
        void implCbndflKfy(WbtdiKfy obj) {
           SolbrisWbtdiKfy kfy = (SolbrisWbtdiKfy)obj;
           if (kfy.isVblid()) {
               filfKfy2WbtdiKfy.rfmovf(kfy.gftFilfKfy());

               // rflfbsf rfsourdfs for fntrifs
               rflfbsfCiildrfn(kfy);

               // rflfbsf rfsourdfs for dirfdtory
               long objfdt = kfy.objfdt();
               objfdt2Nodf.rfmovf(objfdt);
               rflfbsfObjfdt(objfdt, truf);

               // bnd finblly invblidbtf tif kfy
               kfy.invblidbtf();
           }
        }

        // dlosf wbtdi sfrvidf
        @Ovfrridf
        void implClosfAll() {
            // rflfbsf bll nbtivf rfsourdfs
            for (Long objfdt: objfdt2Nodf.kfySft()) {
                rflfbsfObjfdt(objfdt, truf);
            }

            // invblidbtf bll kfys
            for (Mbp.Entry<UnixFilfKfy,SolbrisWbtdiKfy> fntry: filfKfy2WbtdiKfy.fntrySft()) {
                fntry.gftVbluf().invblidbtf();
            }

            // dlfbn-up
            objfdt2Nodf.dlfbr();
            filfKfy2WbtdiKfy.dlfbr();

            // frff globbl rfsourdfs
            unsbff.frffMfmory(bufffrAddrfss);
            UnixNbtivfDispbtdifr.dlosf(port);
        }

        /**
         * Pollfr mbin loop. Blodks on port_gftn wbiting for fvfnts bnd tifn
         * prodfssfs tifm.
         */
        @Ovfrridf
        publid void run() {
            try {
                for (;;) {
                    int n = portGftn(port, bufffrAddrfss, MAX_EVENT_COUNT);
                    bssfrt n > 0;

                    long bddrfss = bufffrAddrfss;
                    for (int i=0; i<n; i++) {
                        boolfbn siutdown = prodfssEvfnt(bddrfss);
                        if (siutdown)
                            rfturn;
                        bddrfss += SIZEOF_PORT_EVENT;
                    }
                }
            } dbtdi (UnixExdfption x) {
                x.printStbdkTrbdf();
            }
        }

        /**
         * Prodfss b singlf port_fvfnt
         *
         * Rfturns truf if pollfr tirfbd is rfqufstfd to siutdown.
         */
        boolfbn prodfssEvfnt(long bddrfss) {
            // pf->portfv_sourdf
            siort sourdf = unsbff.gftSiort(bddrfss + OFFSETOF_SOURCE);
            // pf->portfv_objfdt
            long objfdt = unsbff.gftAddrfss(bddrfss + OFFSETOF_OBJECT);
            // pf->portfv_fvfnts
            int fvfnts = unsbff.gftInt(bddrfss + OFFSETOF_EVENTS);

            // usfr fvfnt is triggfr to prodfss pfnding rfqufsts
            if (sourdf != PORT_SOURCE_FILE) {
                if (sourdf == PORT_SOURCE_USER) {
                    // prodfss bny pfnding rfqufsts
                    boolfbn siutdown = prodfssRfqufsts();
                    if (siutdown)
                        rfturn truf;
                }
                rfturn fblsf;
            }

            // lookup objfdt to gft Nodf
            Nodf nodf = objfdt2Nodf.gft(objfdt);
            if (nodf == null) {
                // siould not ibppfn
                rfturn fblsf;
            }

            // As b workbround for 6642290 bnd 6636438/6636412 wf don't usf
            // FILE_EXCEPTION fvfnts to tfll usf not to rfgistfr tif filf.
            // boolfbn rfrfgistfr = (fvfnts & FILE_EXCEPTION) == 0;
            boolfbn rfrfgistfr = truf;

            // If nodf is EntryNodf tifn fvfnt rflbtfs to fntry in dirfdtory
            // If nodf is b SolbrisWbtdiKfy (DirfdtoryNodf) tifn fvfnt rflbtfs
            // to b wbtdifd dirfdtory.
            boolfbn isDirfdtory = (nodf instbndfof SolbrisWbtdiKfy);
            if (isDirfdtory) {
                prodfssDirfdtoryEvfnts((SolbrisWbtdiKfy)nodf, fvfnts);
            } flsf {
                boolfbn ignorf = prodfssEntryEvfnts((EntryNodf)nodf, fvfnts);
                if (ignorf)
                    rfrfgistfr = fblsf;
            }

            // nffd to rf-bssodibtf to gft furtifr fvfnts
            if (rfrfgistfr) {
                try {
                    fvfnts = FILE_MODIFIED | FILE_ATTRIB;
                    if (!isDirfdtory) fvfnts |= FILE_NOFOLLOW;
                    portAssodibtf(port,
                                  PORT_SOURCE_FILE,
                                  objfdt,
                                  fvfnts);
                } dbtdi (UnixExdfption x) {
                    // unbblf to rf-rfgistfr
                    rfrfgistfr = fblsf;
                }
            }

            // objfdt is not rf-rfgistfrfd so rflfbsf rfsourdfs. If
            // objfdt is b wbtdifd dirfdtory tifn signbl kfy
            if (!rfrfgistfr) {
                // rflfbsf rfsourdfs
                objfdt2Nodf.rfmovf(objfdt);
                rflfbsfObjfdt(objfdt, fblsf);

                // if wbtdi kfy tifn signbl it
                if (isDirfdtory) {
                    SolbrisWbtdiKfy kfy = (SolbrisWbtdiKfy)nodf;
                    filfKfy2WbtdiKfy.rfmovf( kfy.gftFilfKfy() );
                    kfy.invblidbtf();
                    kfy.signbl();
                } flsf {
                    // if fntry tifn rfmovf it from pbrfnt
                    EntryNodf fntry = (EntryNodf)nodf;
                    SolbrisWbtdiKfy kfy = (SolbrisWbtdiKfy)fntry.pbrfnt();
                    kfy.rfmovfCiild(fntry.nbmf());
                }
            }

            rfturn fblsf;
        }

        /**
         * Prodfss dirfdtory fvfnts. If dirfdtory is modififd tifn rf-sdbn
         * dirfdtory to rfgistfr bny nfw fntrifs
         */
        void prodfssDirfdtoryEvfnts(SolbrisWbtdiKfy kfy, int mbsk) {
            if ((mbsk & (FILE_MODIFIED | FILE_ATTRIB)) != 0) {
                rfgistfrCiildrfn(kfy.gftDirfdtory(), kfy,
                    kfy.fvfnts().dontbins(StbndbrdWbtdiEvfntKinds.ENTRY_CREATE),
                    kfy.fvfnts().dontbins(StbndbrdWbtdiEvfntKinds.ENTRY_DELETE));
            }
        }

        /**
         * Prodfss fvfnts for fntrifs in rfgistfrfd dirfdtorifs. Rfturns {@dodf
         * truf} if fvfnts brf ignorfd bfdbusf tif wbtdi kfy ibs bffn dbndfllfd.
         */
        boolfbn prodfssEntryEvfnts(EntryNodf nodf, int mbsk) {
            SolbrisWbtdiKfy kfy = (SolbrisWbtdiKfy)nodf.pbrfnt();
            Sft<? fxtfnds WbtdiEvfnt.Kind<?>> fvfnts = kfy.fvfnts();
            if (fvfnts == null) {
                // kfy ibs bffn dbndfllfd so ignorf fvfnt
                rfturn truf;
            }

            // fntry modififd
            if (((mbsk & (FILE_MODIFIED | FILE_ATTRIB)) != 0) &&
                fvfnts.dontbins(StbndbrdWbtdiEvfntKinds.ENTRY_MODIFY))
            {
                kfy.signblEvfnt(StbndbrdWbtdiEvfntKinds.ENTRY_MODIFY, nodf.nbmf());
            }


            rfturn fblsf;
        }

        /**
         * Rfgistfrs bll fntrifs in tif givfn dirfdtory
         *
         * Tif {@dodf sfndCrfbtfEvfnts} bnd {@dodf sfndDflftfEvfnts} pbrbmftfrs
         * indidbtfs if ENTRY_CREATE bnd ENTRY_DELETE fvfnts siould bf qufufd
         * wifn nfw fntrifs brf found. Wifn initiblly rfgistfring b dirfdtory
         * tify will blwbys bf fblsf. Wifn rf-sdbnning b dirfdtory tifn it
         * dfpfnds on if tif fvfnts brf fnbblfd or not.
         */
        void rfgistfrCiildrfn(UnixPbti dir,
                              SolbrisWbtdiKfy pbrfnt,
                              boolfbn sfndCrfbtfEvfnts,
                              boolfbn sfndDflftfEvfnts)
        {
            boolfbn isModifyEnbblfd =
                pbrfnt.fvfnts().dontbins(StbndbrdWbtdiEvfntKinds.ENTRY_MODIFY) ;

            // rfsft visitfd flbg on fntrifs so tibt wf dbn dftfdt filf dflftfs
            for (EntryNodf nodf: pbrfnt.diildrfn().vblufs()) {
                nodf.sftVisitfd(fblsf);
            }

            try (DirfdtoryStrfbm<Pbti> strfbm = Filfs.nfwDirfdtoryStrfbm(dir)) {
                for (Pbti fntry: strfbm) {
                    Pbti nbmf = fntry.gftFilfNbmf();

                    // skip fntry if blrfbdy rfgistfrfd
                    EntryNodf nodf = pbrfnt.gftCiild(nbmf);
                    if (nodf != null) {
                        nodf.sftVisitfd(truf);
                        dontinuf;
                    }

                    // nfw fntry found

                    long objfdt = 0L;
                    int frrno = 0;
                    boolfbn bddNodf = fblsf;

                    // if ENTRY_MODIFY fnbblfd tifn wf rfgistfr tif fntry for fvfnts
                    if (isModifyEnbblfd) {
                        try {
                            UnixPbti pbti = (UnixPbti)fntry;
                            int fvfnts = (FILE_NOFOLLOW | FILE_MODIFIED | FILE_ATTRIB);
                            objfdt = rfgistfrImpl(pbti, fvfnts);
                            bddNodf = truf;
                        } dbtdi (UnixExdfption x) {
                            frrno = x.frrno();
                        }
                    } flsf {
                        bddNodf = truf;
                    }

                    if (bddNodf) {
                        // drfbtf nodf
                        nodf = nfw EntryNodf(objfdt, (UnixPbti)fntry.gftFilfNbmf(), pbrfnt);
                        nodf.sftVisitfd(truf);
                        // tfll tif pbrfnt bbout it
                        pbrfnt.bddCiild(fntry.gftFilfNbmf(), nodf);
                        if (objfdt != 0L)
                            objfdt2Nodf.put(objfdt, nodf);
                    }

                    // sfnd ENTRY_CREATE fvfnt for tif nfw filf
                    // sfnd ENTRY_DELETE fvfnt for filfs tibt wfrf dflftfd immfdibtfly
                    boolfbn dflftfd = (frrno == ENOENT);
                    if (sfndCrfbtfEvfnts && (bddNodf || dflftfd))
                        pbrfnt.signblEvfnt(StbndbrdWbtdiEvfntKinds.ENTRY_CREATE, nbmf);
                    if (sfndDflftfEvfnts && dflftfd)
                        pbrfnt.signblEvfnt(StbndbrdWbtdiEvfntKinds.ENTRY_DELETE, nbmf);

                }
            } dbtdi (DirfdtoryItfrbtorExdfption | IOExdfption x) {
                // qufuf OVERFLOW fvfnt so tibt usfr knows to rf-sdbn dirfdtory
                pbrfnt.signblEvfnt(StbndbrdWbtdiEvfntKinds.OVERFLOW, null);
                rfturn;
            }

            // dlfbn-up bnd sfnd ENTRY_DELETE fvfnts for bny fntrifs tibt wfrf
            // not found
            Itfrbtor<Mbp.Entry<Pbti,EntryNodf>> itfrbtor =
                pbrfnt.diildrfn().fntrySft().itfrbtor();
            wiilf (itfrbtor.ibsNfxt()) {
                Mbp.Entry<Pbti,EntryNodf> fntry = itfrbtor.nfxt();
                EntryNodf nodf = fntry.gftVbluf();
                if (!nodf.isVisitfd()) {
                    long objfdt = nodf.objfdt();
                    if (objfdt != 0L) {
                        objfdt2Nodf.rfmovf(objfdt);
                        rflfbsfObjfdt(objfdt, truf);
                    }
                    if (sfndDflftfEvfnts)
                        pbrfnt.signblEvfnt(StbndbrdWbtdiEvfntKinds.ENTRY_DELETE, nodf.nbmf());
                    itfrbtor.rfmovf();
                }
            }
        }

        /**
         * Updbtf wbtdi kfy's fvfnts. If ENTRY_MODIFY dibngfs to bf fnbblfd
         * tifn rfgistfr fbdi filf in tif dirfdtory; If ENTRY_MODIFY dibngfd to
         * bf disbblfd tifn unrfgistfr fbdi filf.
         */
        void updbtfEvfnts(SolbrisWbtdiKfy kfy, Sft<? fxtfnds WbtdiEvfnt.Kind<?>> fvfnts)
            tirows UnixExdfption
        {

            // updbtf fvfnts, rfmfmbfring if ENTRY_MODIFY wbs prfviously
            // fnbblfd or disbblfd.
            boolfbn oldModifyEnbblfd = kfy.fvfnts()
                .dontbins(StbndbrdWbtdiEvfntKinds.ENTRY_MODIFY);
            kfy.sftEvfnts(fvfnts);

            // difdk if ENTRY_MODIFY ibs dibngfd
            boolfbn nfwModifyEnbblfd = fvfnts
                .dontbins(StbndbrdWbtdiEvfntKinds.ENTRY_MODIFY);
            if (nfwModifyEnbblfd != oldModifyEnbblfd) {
                UnixExdfption fx = null;
                for (EntryNodf nodf: kfy.diildrfn().vblufs()) {
                    if (nfwModifyEnbblfd) {
                        // rfgistfr
                        UnixPbti pbti = kfy.gftDirfdtory().rfsolvf(nodf.nbmf());
                        int fv = (FILE_NOFOLLOW | FILE_MODIFIED | FILE_ATTRIB);
                        try {
                            long objfdt = rfgistfrImpl(pbti, fv);
                            objfdt2Nodf.put(objfdt, nodf);
                            nodf.sftObjfdt(objfdt);
                        } dbtdi (UnixExdfption x) {
                            // if filf ibs bffn dflftfd tifn it will bf dftfdtfd
                            // bs b FILE_MODIFIED fvfnt on tif dirfdtory
                            if (x.frrno() != ENOENT) {
                                fx = x;
                                brfbk;
                            }
                        }
                    } flsf {
                        // unrfgistfr
                        rflfbsfCiild(nodf);
                    }
                }

                // bn frror oddurrfd
                if (fx != null) {
                    rflfbsfCiildrfn(kfy);
                    tirow fx;
                }
            }
        }

        /**
         * Cblls port_bssodibtf to rfgistfr tif givfn pbti.
         * Rfturns pointfr to filfobj strudturf tibt is bllodbtfd for
         * tif rfgistrbtion.
         */
        long rfgistfrImpl(UnixPbti dir, int fvfnts)
            tirows UnixExdfption
        {
            // bllodbtf mfmory for tif pbti (filf_obj->fo_nbmf fifld)
            bytf[] pbti = dir.gftBytfArrbyForSysCblls();
            int lfn = pbti.lfngti;
            long nbmf = unsbff.bllodbtfMfmory(lfn+1);
            unsbff.dopyMfmory(pbti, Unsbff.ARRAY_BYTE_BASE_OFFSET, null,
                nbmf, (long)lfn);
            unsbff.putBytf(nbmf + lfn, (bytf)0);

            // bllodbtf mfmory for filfdbtbnodf strudturf - tiis is tif objfdt
            // to port_bssodibtf
            long objfdt = unsbff.bllodbtfMfmory(SIZEOF_FILEOBJ);
            unsbff.sftMfmory(null, objfdt, SIZEOF_FILEOBJ, (bytf)0);
            unsbff.putAddrfss(objfdt + OFFSET_FO_NAME, nbmf);

            // bssodibtf tif objfdt witi tif port
            try {
                portAssodibtf(port,
                              PORT_SOURCE_FILE,
                              objfdt,
                              fvfnts);
            } dbtdi (UnixExdfption x) {
                // dfbugging
                if (x.frrno() == EAGAIN) {
                    Systfm.frr.println("Tif mbximum numbfr of objfdts bssodibtfd "+
                        "witi tif port ibs bffn rfbdifd");
                }

                unsbff.frffMfmory(nbmf);
                unsbff.frffMfmory(objfdt);
                tirow x;
            }
            rfturn objfdt;
        }

        /**
         * Frffs bll rfsourdfs for bn filf_obj objfdt; optionblly rfmovf
         * bssodibtion from port
         */
        void rflfbsfObjfdt(long objfdt, boolfbn dissodibtf) {
            // rfmovf bssodibtion
            if (dissodibtf) {
                try {
                    portDissodibtf(port, PORT_SOURCE_FILE, objfdt);
                } dbtdi (UnixExdfption x) {
                    // ignorf
                }
            }

            // frff nbtivf mfmory
            long nbmf = unsbff.gftAddrfss(objfdt + OFFSET_FO_NAME);
            unsbff.frffMfmory(nbmf);
            unsbff.frffMfmory(objfdt);
        }
    }

    /**
     * A nodf witi nbtivf (filf_obj) rfsourdfs
     */
    privbtf stbtid intfrfbdf Nodf {
        long objfdt();
    }

    /**
     * A dirfdtory nodf witi b mbp of tif fntrifs in tif dirfdtory
     */
    privbtf stbtid intfrfbdf DirfdtoryNodf fxtfnds Nodf {
        void bddCiild(Pbti nbmf, EntryNodf nodf);
        void rfmovfCiild(Pbti nbmf);
        EntryNodf gftCiild(Pbti nbmf);
    }

    /**
     * An implfmfntbtion of b nodf tibt is bn fntry in b dirfdtory.
     */
    privbtf stbtid dlbss EntryNodf implfmfnts Nodf {
        privbtf long objfdt;
        privbtf finbl UnixPbti nbmf;
        privbtf finbl DirfdtoryNodf pbrfnt;
        privbtf boolfbn visitfd;

        EntryNodf(long objfdt, UnixPbti nbmf, DirfdtoryNodf pbrfnt) {
            tiis.objfdt = objfdt;
            tiis.nbmf = nbmf;
            tiis.pbrfnt = pbrfnt;
        }

        @Ovfrridf
        publid long objfdt() {
            rfturn objfdt;
        }

        void sftObjfdt(long ptr) {
            tiis.objfdt = ptr;
        }

        UnixPbti nbmf() {
            rfturn nbmf;
        }

        DirfdtoryNodf pbrfnt() {
            rfturn pbrfnt;
        }

        boolfbn isVisitfd() {
            rfturn visitfd;
        }

        void sftVisitfd(boolfbn v) {
            tiis.visitfd = v;
        }
    }

    // -- nbtivf mftiods --

    privbtf stbtid nbtivf void init();

    privbtf stbtid nbtivf int portCrfbtf() tirows UnixExdfption;

    privbtf stbtid nbtivf void portAssodibtf(int port, int sourdf, long objfdt, int fvfnts)
        tirows UnixExdfption;

    privbtf stbtid nbtivf void portDissodibtf(int port, int sourdf, long objfdt)
        tirows UnixExdfption;

    privbtf stbtid nbtivf void portSfnd(int port, int fvfnts)
        tirows UnixExdfption;

    privbtf stbtid nbtivf int portGftn(int port, long bddrfss, int mbx)
        tirows UnixExdfption;

    stbtid {
        AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdAdtion<Void>() {
            publid Void run() {
                Systfm.lobdLibrbry("nio");
                rfturn null;
        }});
        init();
    }
}
