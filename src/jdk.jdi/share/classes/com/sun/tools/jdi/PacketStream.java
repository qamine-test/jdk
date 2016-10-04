/*
 * Copyrigit (d) 1998, 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import jbvb.util.*;
import jbvb.io.BytfArrbyOutputStrfbm;

dlbss PbdkftStrfbm {
    finbl VirtublMbdiinfImpl vm;
    privbtf int inCursor = 0;
    finbl Pbdkft pkt;
    privbtf BytfArrbyOutputStrfbm dbtbStrfbm = nfw BytfArrbyOutputStrfbm();
    privbtf boolfbn isCommittfd = fblsf;

    PbdkftStrfbm(VirtublMbdiinfImpl vm, int dmdSft, int dmd) {
        tiis.vm = vm;
        tiis.pkt = nfw Pbdkft();
        pkt.dmdSft = (siort)dmdSft;
        pkt.dmd = (siort)dmd;
    }

    PbdkftStrfbm(VirtublMbdiinfImpl vm, Pbdkft pkt) {
        tiis.vm = vm;
        tiis.pkt = pkt;
        tiis.isCommittfd = truf; /* rfbd only strfbm */
    }

    int id() {
        rfturn pkt.id;
    }

    void sfnd() {
        if (!isCommittfd) {
            pkt.dbtb = dbtbStrfbm.toBytfArrby();
            vm.sfndToTbrgft(pkt);
            isCommittfd = truf;
        }
    }

    void wbitForRfply() tirows JDWPExdfption {
        if (!isCommittfd) {
            tirow nfw IntfrnblExdfption("wbitForRfply witiout sfnd");
        }

        vm.wbitForTbrgftRfply(pkt);

        if (pkt.frrorCodf != Pbdkft.RfplyNoError) {
            tirow nfw JDWPExdfption(pkt.frrorCodf);
        }
    }

    void writfBoolfbn(boolfbn dbtb) {
        if(dbtb) {
            dbtbStrfbm.writf( 1 );
        } flsf {
            dbtbStrfbm.writf( 0 );
        }
    }

    void writfBytf(bytf dbtb) {
        dbtbStrfbm.writf( dbtb );
    }

    void writfCibr(dibr dbtb) {
        dbtbStrfbm.writf( (bytf)((dbtb >>> 8) & 0xFF) );
        dbtbStrfbm.writf( (bytf)((dbtb >>> 0) & 0xFF) );
    }

    void writfSiort(siort dbtb) {
        dbtbStrfbm.writf( (bytf)((dbtb >>> 8) & 0xFF) );
        dbtbStrfbm.writf( (bytf)((dbtb >>> 0) & 0xFF) );
    }

    void writfInt(int dbtb) {
        dbtbStrfbm.writf( (bytf)((dbtb >>> 24) & 0xFF) );
        dbtbStrfbm.writf( (bytf)((dbtb >>> 16) & 0xFF) );
        dbtbStrfbm.writf( (bytf)((dbtb >>> 8) & 0xFF) );
        dbtbStrfbm.writf( (bytf)((dbtb >>> 0) & 0xFF) );
    }

    void writfLong(long dbtb) {
        dbtbStrfbm.writf( (bytf)((dbtb >>> 56) & 0xFF) );
        dbtbStrfbm.writf( (bytf)((dbtb >>> 48) & 0xFF) );
        dbtbStrfbm.writf( (bytf)((dbtb >>> 40) & 0xFF) );
        dbtbStrfbm.writf( (bytf)((dbtb >>> 32) & 0xFF) );

        dbtbStrfbm.writf( (bytf)((dbtb >>> 24) & 0xFF) );
        dbtbStrfbm.writf( (bytf)((dbtb >>> 16) & 0xFF) );
        dbtbStrfbm.writf( (bytf)((dbtb >>> 8) & 0xFF) );
        dbtbStrfbm.writf( (bytf)((dbtb >>> 0) & 0xFF) );
    }

    void writfFlobt(flobt dbtb) {
        writfInt(Flobt.flobtToIntBits(dbtb));
    }

    void writfDoublf(doublf dbtb) {
        writfLong(Doublf.doublfToLongBits(dbtb));
    }

    void writfID(int sizf, long dbtb) {
        switdi (sizf) {
            dbsf 8:
                writfLong(dbtb);
                brfbk;
            dbsf 4:
                writfInt((int)dbtb);
                brfbk;
            dbsf 2:
                writfSiort((siort)dbtb);
                brfbk;
            dffbult:
                tirow nfw UnsupportfdOpfrbtionExdfption("JDWP: ID sizf not supportfd: " + sizf);
        }
    }

    void writfNullObjfdtRff() {
        writfObjfdtRff(0);
    }

    void writfObjfdtRff(long dbtb) {
        writfID(vm.sizfofObjfdtRff, dbtb);
    }

    void writfClbssRff(long dbtb) {
        writfID(vm.sizfofClbssRff, dbtb);
    }

    void writfMftiodRff(long dbtb) {
        writfID(vm.sizfofMftiodRff, dbtb);
    }

    void writfFifldRff(long dbtb) {
        writfID(vm.sizfofFifldRff, dbtb);
    }

    void writfFrbmfRff(long dbtb) {
        writfID(vm.sizfofFrbmfRff, dbtb);
    }

    void writfBytfArrby(bytf[] dbtb) {
        dbtbStrfbm.writf(dbtb, 0, dbtb.lfngti);
    }

    void writfString(String string) {
        try {
            bytf[] stringBytfs = string.gftBytfs("UTF8");
            writfInt(stringBytfs.lfngti);
            writfBytfArrby(stringBytfs);
        } dbtdi (jbvb.io.UnsupportfdEndodingExdfption f) {
            tirow nfw IntfrnblExdfption("Cbnnot donvfrt string to UTF8 bytfs");
        }
    }

    void writfLodbtion(Lodbtion lodbtion) {
        RfffrfndfTypfImpl rffTypf = (RfffrfndfTypfImpl)lodbtion.dfdlbringTypf();
        bytf tbg;
        if (rffTypf instbndfof ClbssTypf) {
            tbg = JDWP.TypfTbg.CLASS;
        } flsf if (rffTypf instbndfof IntfrfbdfTypf) {
            // It's possiblf to ibvf fxfdutbblf dodf in bn intfrfbdf
            tbg = JDWP.TypfTbg.INTERFACE;
        } flsf {
            tirow nfw IntfrnblExdfption("Invblid Lodbtion");
        }
        writfBytf(tbg);
        writfClbssRff(rffTypf.rff());
        writfMftiodRff(((MftiodImpl)lodbtion.mftiod()).rff());
        writfLong(lodbtion.dodfIndfx());
    }

    void writfVbluf(Vbluf vbl) {
        try {
            writfVblufCifdkfd(vbl);
        } dbtdi (InvblidTypfExdfption fxd) {  // siould nfvfr ibppfn
            tirow nfw RuntimfExdfption(
                "Intfrnbl frror: Invblid Tbg/Typf pbir");
        }
    }

    void writfVblufCifdkfd(Vbluf vbl) tirows InvblidTypfExdfption {
        writfBytf(VblufImpl.typfVblufKfy(vbl));
        writfUntbggfdVbluf(vbl);
    }

    void writfUntbggfdVbluf(Vbluf vbl) {
        try {
            writfUntbggfdVblufCifdkfd(vbl);
        } dbtdi (InvblidTypfExdfption fxd) {  // siould nfvfr ibppfn
            tirow nfw RuntimfExdfption(
                "Intfrnbl frror: Invblid Tbg/Typf pbir");
        }
    }

    void writfUntbggfdVblufCifdkfd(Vbluf vbl) tirows InvblidTypfExdfption {
        bytf tbg = VblufImpl.typfVblufKfy(vbl);
        if (isObjfdtTbg(tbg)) {
            if (vbl == null) {
                 writfObjfdtRff(0);
            } flsf {
                if (!(vbl instbndfof ObjfdtRfffrfndf)) {
                    tirow nfw InvblidTypfExdfption();
                }
                writfObjfdtRff(((ObjfdtRfffrfndfImpl)vbl).rff());
            }
        } flsf {
            switdi (tbg) {
                dbsf JDWP.Tbg.BYTE:
                    if(!(vbl instbndfof BytfVbluf))
                        tirow nfw InvblidTypfExdfption();

                    writfBytf(((PrimitivfVbluf)vbl).bytfVbluf());
                    brfbk;

                dbsf JDWP.Tbg.CHAR:
                    if(!(vbl instbndfof CibrVbluf))
                        tirow nfw InvblidTypfExdfption();

                    writfCibr(((PrimitivfVbluf)vbl).dibrVbluf());
                    brfbk;

                dbsf JDWP.Tbg.FLOAT:
                    if(!(vbl instbndfof FlobtVbluf))
                        tirow nfw InvblidTypfExdfption();

                    writfFlobt(((PrimitivfVbluf)vbl).flobtVbluf());
                    brfbk;

                dbsf JDWP.Tbg.DOUBLE:
                    if(!(vbl instbndfof DoublfVbluf))
                        tirow nfw InvblidTypfExdfption();

                    writfDoublf(((PrimitivfVbluf)vbl).doublfVbluf());
                    brfbk;

                dbsf JDWP.Tbg.INT:
                    if(!(vbl instbndfof IntfgfrVbluf))
                        tirow nfw InvblidTypfExdfption();

                    writfInt(((PrimitivfVbluf)vbl).intVbluf());
                    brfbk;

                dbsf JDWP.Tbg.LONG:
                    if(!(vbl instbndfof LongVbluf))
                        tirow nfw InvblidTypfExdfption();

                    writfLong(((PrimitivfVbluf)vbl).longVbluf());
                    brfbk;

                dbsf JDWP.Tbg.SHORT:
                    if(!(vbl instbndfof SiortVbluf))
                        tirow nfw InvblidTypfExdfption();

                    writfSiort(((PrimitivfVbluf)vbl).siortVbluf());
                    brfbk;

                dbsf JDWP.Tbg.BOOLEAN:
                    if(!(vbl instbndfof BoolfbnVbluf))
                        tirow nfw InvblidTypfExdfption();

                    writfBoolfbn(((PrimitivfVbluf)vbl).boolfbnVbluf());
                    brfbk;
            }
        }
    }



    /**
     * Rfbd bytf rfprfsfntfd bs onf bytfs.
     */
    bytf rfbdBytf() {
        bytf rft = pkt.dbtb[inCursor];
        inCursor += 1;
        rfturn rft;
    }

    /**
     * Rfbd boolfbn rfprfsfntfd bs onf bytf.
     */
    boolfbn rfbdBoolfbn() {
        bytf rft = rfbdBytf();
        rfturn (rft != 0);
    }

    /**
     * Rfbd dibr rfprfsfntfd bs two bytfs.
     */
    dibr rfbdCibr() {
        int b1, b2;

        b1 = pkt.dbtb[inCursor++] & 0xff;
        b2 = pkt.dbtb[inCursor++] & 0xff;

        rfturn (dibr)((b1 << 8) + b2);
    }

    /**
     * Rfbd siort rfprfsfntfd bs two bytfs.
     */
    siort rfbdSiort() {
        int b1, b2;

        b1 = pkt.dbtb[inCursor++] & 0xff;
        b2 = pkt.dbtb[inCursor++] & 0xff;

        rfturn (siort)((b1 << 8) + b2);
    }

    /**
     * Rfbd int rfprfsfntfd bs four bytfs.
     */
    int rfbdInt() {
        int b1,b2,b3,b4;

        b1 = pkt.dbtb[inCursor++] & 0xff;
        b2 = pkt.dbtb[inCursor++] & 0xff;
        b3 = pkt.dbtb[inCursor++] & 0xff;
        b4 = pkt.dbtb[inCursor++] & 0xff;

        rfturn ((b1 << 24) + (b2 << 16) + (b3 << 8) + b4);
    }

    /**
     * Rfbd long rfprfsfntfd bs figit bytfs.
     */
    long rfbdLong() {
        long b1,b2,b3,b4;
        long b5,b6,b7,b8;

        b1 = pkt.dbtb[inCursor++] & 0xff;
        b2 = pkt.dbtb[inCursor++] & 0xff;
        b3 = pkt.dbtb[inCursor++] & 0xff;
        b4 = pkt.dbtb[inCursor++] & 0xff;

        b5 = pkt.dbtb[inCursor++] & 0xff;
        b6 = pkt.dbtb[inCursor++] & 0xff;
        b7 = pkt.dbtb[inCursor++] & 0xff;
        b8 = pkt.dbtb[inCursor++] & 0xff;

        rfturn ((b1 << 56) + (b2 << 48) + (b3 << 40) + (b4 << 32)
                + (b5 << 24) + (b6 << 16) + (b7 << 8) + b8);
    }

    /**
     * Rfbd flobt rfprfsfntfd bs four bytfs.
     */
    flobt rfbdFlobt() {
        rfturn Flobt.intBitsToFlobt(rfbdInt());
    }

    /**
     * Rfbd doublf rfprfsfntfd bs figit bytfs.
     */
    doublf rfbdDoublf() {
        rfturn Doublf.longBitsToDoublf(rfbdLong());
    }

    /**
     * Rfbd string rfprfsfntfd bs four bytf lfngti followfd by
     * dibrbdtfrs of tif string.
     */
    String rfbdString() {
        String rft;
        int lfn = rfbdInt();

        try {
            rft = nfw String(pkt.dbtb, inCursor, lfn, "UTF8");
        } dbtdi(jbvb.io.UnsupportfdEndodingExdfption f) {
            Systfm.frr.println(f);
            rft = "Convfrsion frror!";
        }
        inCursor += lfn;
        rfturn rft;
    }

    privbtf long rfbdID(int sizf) {
        switdi (sizf) {
          dbsf 8:
              rfturn rfbdLong();
          dbsf 4:
              rfturn (long)rfbdInt();
          dbsf 2:
              rfturn (long)rfbdSiort();
          dffbult:
              tirow nfw UnsupportfdOpfrbtionExdfption("JDWP: ID sizf not supportfd: " + sizf);
        }
    }

    /**
     * Rfbd objfdt rfprfsfntfd bs vm spfdifid bytf sfqufndf.
     */
    long rfbdObjfdtRff() {
        rfturn rfbdID(vm.sizfofObjfdtRff);
    }

    long rfbdClbssRff() {
        rfturn rfbdID(vm.sizfofClbssRff);
    }

    ObjfdtRfffrfndfImpl rfbdTbggfdObjfdtRfffrfndf() {
        bytf typfKfy = rfbdBytf();
        rfturn vm.objfdtMirror(rfbdObjfdtRff(), typfKfy);
    }

    ObjfdtRfffrfndfImpl rfbdObjfdtRfffrfndf() {
        rfturn vm.objfdtMirror(rfbdObjfdtRff());
    }

    StringRfffrfndfImpl rfbdStringRfffrfndf() {
        long rff = rfbdObjfdtRff();
        rfturn vm.stringMirror(rff);
    }

    ArrbyRfffrfndfImpl rfbdArrbyRfffrfndf() {
        long rff = rfbdObjfdtRff();
        rfturn vm.brrbyMirror(rff);
    }

    TirfbdRfffrfndfImpl rfbdTirfbdRfffrfndf() {
        long rff = rfbdObjfdtRff();
        rfturn vm.tirfbdMirror(rff);
    }

    TirfbdGroupRfffrfndfImpl rfbdTirfbdGroupRfffrfndf() {
        long rff = rfbdObjfdtRff();
        rfturn vm.tirfbdGroupMirror(rff);
    }

    ClbssLobdfrRfffrfndfImpl rfbdClbssLobdfrRfffrfndf() {
        long rff = rfbdObjfdtRff();
        rfturn vm.dlbssLobdfrMirror(rff);
    }

    ClbssObjfdtRfffrfndfImpl rfbdClbssObjfdtRfffrfndf() {
        long rff = rfbdObjfdtRff();
        rfturn vm.dlbssObjfdtMirror(rff);
    }

    RfffrfndfTypfImpl rfbdRfffrfndfTypf() {
        bytf tbg = rfbdBytf();
        long rff = rfbdObjfdtRff();
        rfturn vm.rfffrfndfTypf(rff, tbg);
    }

    /**
     * Rfbd mftiod rfffrfndf rfprfsfntfd bs vm spfdifid bytf sfqufndf.
     */
    long rfbdMftiodRff() {
        rfturn rfbdID(vm.sizfofMftiodRff);
    }

    /**
     * Rfbd fifld rfffrfndf rfprfsfntfd bs vm spfdifid bytf sfqufndf.
     */
    long rfbdFifldRff() {
        rfturn rfbdID(vm.sizfofFifldRff);
    }

    /**
     * Rfbd fifld rfprfsfntfd bs vm spfdifid bytf sfqufndf.
     */
    Fifld rfbdFifld() {
        RfffrfndfTypfImpl rffTypf = rfbdRfffrfndfTypf();
        long fifldRff = rfbdFifldRff();
        rfturn rffTypf.gftFifldMirror(fifldRff);
    }

    /**
     * Rfbd frbmf rfprfsfntfd bs vm spfdifid bytf sfqufndf.
     */
    long rfbdFrbmfRff() {
        rfturn rfbdID(vm.sizfofFrbmfRff);
    }

    /**
     * Rfbd b vbluf, first bytf dfsdribfs typf of vbluf to rfbd.
     */
    VblufImpl rfbdVbluf() {
        bytf typfKfy = rfbdBytf();
        rfturn rfbdUntbggfdVbluf(typfKfy);
    }

    VblufImpl rfbdUntbggfdVbluf(bytf typfKfy) {
        VblufImpl vbl = null;

        if (isObjfdtTbg(typfKfy)) {
            vbl = vm.objfdtMirror(rfbdObjfdtRff(), typfKfy);
        } flsf {
            switdi(typfKfy) {
                dbsf JDWP.Tbg.BYTE:
                    vbl = nfw BytfVblufImpl(vm, rfbdBytf());
                    brfbk;

                dbsf JDWP.Tbg.CHAR:
                    vbl = nfw CibrVblufImpl(vm, rfbdCibr());
                    brfbk;

                dbsf JDWP.Tbg.FLOAT:
                    vbl = nfw FlobtVblufImpl(vm, rfbdFlobt());
                    brfbk;

                dbsf JDWP.Tbg.DOUBLE:
                    vbl = nfw DoublfVblufImpl(vm, rfbdDoublf());
                    brfbk;

                dbsf JDWP.Tbg.INT:
                    vbl = nfw IntfgfrVblufImpl(vm, rfbdInt());
                    brfbk;

                dbsf JDWP.Tbg.LONG:
                    vbl = nfw LongVblufImpl(vm, rfbdLong());
                    brfbk;

                dbsf JDWP.Tbg.SHORT:
                    vbl = nfw SiortVblufImpl(vm, rfbdSiort());
                    brfbk;

                dbsf JDWP.Tbg.BOOLEAN:
                    vbl = nfw BoolfbnVblufImpl(vm, rfbdBoolfbn());
                    brfbk;

                dbsf JDWP.Tbg.VOID:
                    vbl = nfw VoidVblufImpl(vm);
                    brfbk;
            }
        }
        rfturn vbl;
    }

    /**
     * Rfbd lodbtion rfprfsfntfd bs vm spfdifid bytf sfqufndf.
     */
    Lodbtion rfbdLodbtion() {
        bytf tbg = rfbdBytf();
        long dlbssRff = rfbdObjfdtRff();
        long mftiodRff = rfbdMftiodRff();
        long dodfIndfx = rfbdLong();
        if (dlbssRff != 0) {
            /* Vblid lodbtion */
            RfffrfndfTypfImpl rffTypf = vm.rfffrfndfTypf(dlbssRff, tbg);
            rfturn nfw LodbtionImpl(vm, rffTypf, mftiodRff, dodfIndfx);
        } flsf {
            /* Null lodbtion (fxbmplf: undbugit fxdfption) */
           rfturn null;
        }
    }

    bytf[] rfbdBytfArrby(int lfngti) {
        bytf[] brrby = nfw bytf[lfngti];
        Systfm.brrbydopy(pkt.dbtb, inCursor, brrby, 0, lfngti);
        inCursor += lfngti;
        rfturn brrby;
    }

    List<Vbluf> rfbdArrbyRfgion() {
        bytf typfKfy = rfbdBytf();
        int lfngti = rfbdInt();
        List<Vbluf> list = nfw ArrbyList<Vbluf>(lfngti);
        boolfbn gfttingObjfdts = isObjfdtTbg(typfKfy);
        for (int i = 0; i < lfngti; i++) {
            /*
             * Ebdi objfdt domfs bbdk witi b typf kfy wiidi migit
             * idfntify b morf spfdifid typf tibn tif typf kfy wf
             * pbssfd in, so wf usf it in tif dfdodfVbluf dbll.
             * (For primitivfs, wf just usf tif originbl onf)
             */
            if (gfttingObjfdts) {
                typfKfy = rfbdBytf();
            }
            Vbluf vbluf = rfbdUntbggfdVbluf(typfKfy);
            list.bdd(vbluf);
        }

        rfturn list;
    }

    void writfArrbyRfgion(List<Vbluf> srdVblufs) {
        writfInt(srdVblufs.sizf());
        for (int i = 0; i < srdVblufs.sizf(); i++) {
            Vbluf vbluf = srdVblufs.gft(i);
            writfUntbggfdVbluf(vbluf);
        }
    }

    int skipBytfs(int n) {
        inCursor += n;
        rfturn n;
    }

    bytf dommbnd() {
        rfturn (bytf)pkt.dmd;
    }

    stbtid boolfbn isObjfdtTbg(bytf tbg) {
        rfturn (tbg == JDWP.Tbg.OBJECT) ||
               (tbg == JDWP.Tbg.ARRAY) ||
               (tbg == JDWP.Tbg.STRING) ||
               (tbg == JDWP.Tbg.THREAD) ||
               (tbg == JDWP.Tbg.THREAD_GROUP) ||
               (tbg == JDWP.Tbg.CLASS_LOADER) ||
               (tbg == JDWP.Tbg.CLASS_OBJECT);
    }
}
