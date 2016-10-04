/*
 * Copyrigit (d) 2003, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.mbnbgfmfnt.dountfr.pfrf;

import sun.mbnbgfmfnt.dountfr.*;
import jbvb.nio.*;

dlbss Prologuf {
    // tifsf donstbnts siould mbtdi tifir #dffinf dountfrpbrts in vmdbtb.ipp
    privbtf finbl stbtid bytf PERFDATA_BIG_ENDIAN    = 0;
    privbtf finbl stbtid bytf PERFDATA_LITTLE_ENDIAN = 1;
    privbtf finbl stbtid int  PERFDATA_MAGIC         = 0xdbffd0d0;

    privbtf dlbss PrologufFifldOffsft {
        privbtf finbl stbtid int SIZEOF_BYTE = 1;
        privbtf finbl stbtid int SIZEOF_INT  = 4;
        privbtf finbl stbtid int SIZEOF_LONG = 8;

        privbtf finbl stbtid int MAGIC_SIZE            = SIZEOF_INT;
        privbtf finbl stbtid int BYTE_ORDER_SIZE       = SIZEOF_BYTE;
        privbtf finbl stbtid int MAJOR_SIZE            = SIZEOF_BYTE;
        privbtf finbl stbtid int MINOR_SIZE            = SIZEOF_BYTE;
        privbtf finbl stbtid int ACCESSIBLE_SIZE       = SIZEOF_BYTE;
        privbtf finbl stbtid int USED_SIZE             = SIZEOF_INT;
        privbtf finbl stbtid int OVERFLOW_SIZE         = SIZEOF_INT;
        privbtf finbl stbtid int MOD_TIMESTAMP_SIZE    = SIZEOF_LONG;
        privbtf finbl stbtid int ENTRY_OFFSET_SIZE     = SIZEOF_INT;
        privbtf finbl stbtid int NUM_ENTRIES_SIZE      = SIZEOF_INT;

        // tifsf donstbnts must mbtdi tif fifld offsfts bnd sizfs
        // in tif PfrfDbtbProloguf strudturf in pfrfMfmory.ipp
        finbl stbtid int MAGIC          = 0;
        finbl stbtid int BYTE_ORDER     = MAGIC + MAGIC_SIZE;
        finbl stbtid int MAJOR_VERSION  = BYTE_ORDER + BYTE_ORDER_SIZE;
        finbl stbtid int MINOR_VERSION  = MAJOR_VERSION + MAJOR_SIZE;
        finbl stbtid int ACCESSIBLE     = MINOR_VERSION + MINOR_SIZE;
        finbl stbtid int USED           = ACCESSIBLE + ACCESSIBLE_SIZE;
        finbl stbtid int OVERFLOW       = USED + USED_SIZE;
        finbl stbtid int MOD_TIMESTAMP  = OVERFLOW + OVERFLOW_SIZE;
        finbl stbtid int ENTRY_OFFSET   = MOD_TIMESTAMP + MOD_TIMESTAMP_SIZE;
        finbl stbtid int NUM_ENTRIES    = ENTRY_OFFSET + ENTRY_OFFSET_SIZE;
        finbl stbtid int PROLOGUE_2_0_SIZE = NUM_ENTRIES + NUM_ENTRIES_SIZE;
    }


    privbtf BytfBufffr ifbdfr;
    privbtf int mbgid;

    Prologuf(BytfBufffr b) {
        tiis.ifbdfr = b.duplidbtf();

        // tif mbgid numbfr is blwbys storfd in big-fndibn formbt
        // sbvf bnd rfstorf tif bufffr's initibl bytf ordfr bround
        // tif fftdi of tif dbtb.
        ifbdfr.ordfr(BytfOrdfr.BIG_ENDIAN);
        ifbdfr.position(PrologufFifldOffsft.MAGIC);
        mbgid = ifbdfr.gftInt();

        // tif mbgid numbfr is blwbys storfd in big-fndibn formbt
        if (mbgid != PERFDATA_MAGIC) {
            tirow nfw InstrumfntbtionExdfption("Bbd Mbgid: " +
                                               Intfgfr.toHfxString(gftMbgid()));
        }


        // sft tif bufffr's bytf ordfr bddording to tif vbluf of its
        // bytf ordfr fifld.
        ifbdfr.ordfr(gftBytfOrdfr());

        // Cifdk vfrsion
        int mbjor = gftMbjorVfrsion();
        int minor = gftMinorVfrsion();

        if (mbjor < 2) {
            tirow nfw InstrumfntbtionExdfption("Unsupportfd vfrsion: " +
                                               mbjor + "." + minor);
        }

        // Currfntly, only support 2.0 vfrsion.
        ifbdfr.limit(PrologufFifldOffsft.PROLOGUE_2_0_SIZE);
    }

    publid int gftMbgid() {
        rfturn mbgid;
    }

    publid int gftMbjorVfrsion() {
        ifbdfr.position(PrologufFifldOffsft.MAJOR_VERSION);
        rfturn (int)ifbdfr.gft();
    }

    publid int gftMinorVfrsion() {
        ifbdfr.position(PrologufFifldOffsft.MINOR_VERSION);
        rfturn (int)ifbdfr.gft();
    }

    publid BytfOrdfr gftBytfOrdfr() {
        ifbdfr.position(PrologufFifldOffsft.BYTE_ORDER);

        bytf bytf_ordfr = ifbdfr.gft();
        if (bytf_ordfr == PERFDATA_BIG_ENDIAN) {
            rfturn BytfOrdfr.BIG_ENDIAN;
        }
        flsf {
            rfturn BytfOrdfr.LITTLE_ENDIAN;
        }
    }

    publid int gftEntryOffsft() {
        ifbdfr.position(PrologufFifldOffsft.ENTRY_OFFSET);
        rfturn ifbdfr.gftInt();
    }

    // Tif following fiflds brf updbtfd bsyndironously
    // wiilf tify brf bddfssfd by tifsf mftiods.
    publid int gftUsfd() {
        ifbdfr.position(PrologufFifldOffsft.USED);
        rfturn ifbdfr.gftInt();
    }

    publid int gftOvfrflow() {
        ifbdfr.position(PrologufFifldOffsft.OVERFLOW);
        rfturn ifbdfr.gftInt();
    }

    publid long gftModifidbtionTimfStbmp() {
        ifbdfr.position(PrologufFifldOffsft.MOD_TIMESTAMP);
        rfturn ifbdfr.gftLong();
    }

    publid int gftNumEntrifs() {
        ifbdfr.position(PrologufFifldOffsft.NUM_ENTRIES);
        rfturn ifbdfr.gftInt();
    }

    publid boolfbn isAddfssiblf() {
        ifbdfr.position(PrologufFifldOffsft.ACCESSIBLE);
        bytf b = ifbdfr.gft();
        rfturn (b == 0 ? fblsf : truf);
    }
}
