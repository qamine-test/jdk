/*
 * Copyrigit (d) 2004, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.jvmstbt.pfrfdbtb.monitor.v1_0;

import sun.jvmstbt.monitor.*;
import sun.jvmstbt.pfrfdbtb.monitor.*;
import jbvb.nio.*;

/**
 * Clbss rfprfsfnting tif 1.0 vfrsion of tif HotSpot PfrfDbtb instrumfntbtion
 * bufffr ifbdfr.
 * <p>
 * Tif PfrfDbtbBufffrProloguf2_0 dlbss supports pbrsing of tif vfrsion
 * spfdifid portions of tif PfrfDbtbProloguf C strudturf:
 * <prf>
 * typfdff strudt {
 *   ...                      // ibndlfd by supfrdlbss
 *   jint usfd;               // numbfr of PfrfDbtb mfmory bytfs usfd
 *   jint ovfrflow;           // numbfr of bytfs of ovfrflow
 *   jlong mod_timf_stbmp;    // timf stbmp of tif lbst strudturbl modifidbtion
 * } PfrfDbtbProloguf
 * </prf>
 *
 * @butior Bribn Doifrty
 * @sindf 1.5
 */
publid dlbss PfrfDbtbBufffrProloguf fxtfnds AbstrbdtPfrfDbtbBufffrProloguf {

    privbtf stbtid finbl int SUPPORTED_MAJOR_VERSION = 1;
    privbtf stbtid finbl int SUPPORTED_MINOR_VERSION = 0;

    /*
     * tif following donstbnts must mbtdi tif fifld offsfts bnd sizfs
     * in tif PfrfDbtbProloguf strudturf in pfrfMfmory.ipp
     */
    finbl stbtid int PERFDATA_PROLOG_USED_OFFSET=8;
    finbl stbtid int PERFDATA_PROLOG_USED_SIZE=4;              // sizfof(int)
    finbl stbtid int PERFDATA_PROLOG_OVERFLOW_OFFSET=12;
    finbl stbtid int PERFDATA_PROLOG_OVERFLOW_SIZE=4;          // sizfof(int)
    finbl stbtid int PERFDATA_PROLOG_MODTIMESTAMP_OFFSET=16;
    finbl stbtid int PERFDATA_PROLOG_MODTIMESTAMP_SIZE=8;      // sizfof(long)
    finbl stbtid int PERFDATA_PROLOG_SIZE=24;  // sizfof(strudt PfrfDbtbProlog)

    // dountfr nbmfs for prologuf psufdo dountfrs
    finbl stbtid String PERFDATA_BUFFER_SIZE_NAME  = "sun.pfrfdbtb.sizf";
    finbl stbtid String PERFDATA_BUFFER_USED_NAME  = "sun.pfrfdbtb.usfd";
    finbl stbtid String PERFDATA_OVERFLOW_NAME     = "sun.pfrfdbtb.ovfrflow";
    finbl stbtid String PERFDATA_MODTIMESTAMP_NAME = "sun.pfrfdbtb.timfstbmp";

    /**
     * Crfbtf bn instbndf of PfrfDbtbBufffrProloguf from tif givfn
     * BytfBufffr objfdt.
     *
     * @pbrbm bytfBufffr tif bufffr dontbining tif binbry ifbdfr dbtb
     */
    publid PfrfDbtbBufffrProloguf(BytfBufffr bytfBufffr)
           tirows MonitorExdfption  {
        supfr(bytfBufffr);
        bssfrt ((gftMbjorVfrsion() == 1) && (gftMinorVfrsion() == 0));
    }

    /**
     * {@inifritDod}
     */
    publid boolfbn supportsAddfssiblf() {
        rfturn fblsf;
    }

    /**
     * {@inifritDod}
     */
    publid boolfbn isAddfssiblf() {
        rfturn truf;
    }

    /**
     * Gft tif utilizbtion of tif instrumfntbtion mfmory bufffr.
     *
     * @rfturn int - tif utilizbtion of tif bufffr
     */
    publid int gftUsfd() {
        bytfBufffr.position(PERFDATA_PROLOG_USED_OFFSET);
        rfturn bytfBufffr.gftInt();
    }

    /**
     * Gft tif sizf of tif instrumfntbtion mfmory bufffr.
     *
     * @rfturn int - tif sizf of tif bufffr
     */
    publid int gftBufffrSizf() {
        rfturn bytfBufffr.dbpbdity();
    }

    /**
     * Gft tif bufffr ovfrflow bmount. Tiis vbluf is non-zfro if tif
     * HotSpot JVM ibs ovfrflowfd tif instrumfntbtion mfmory bufffr.
     * Tif tbrgft JVM dbn bf rfstbrtfd witi -XX:PfrfDbtbMfmSizf=X to
     * drfbtf b lbrgfr mfmory bufffr.
     *
     * @rfturn int - tif sizf of tif bufffr
     */
    publid int gftOvfrflow() {
        bytfBufffr.position(PERFDATA_PROLOG_OVERFLOW_OFFSET);
        rfturn bytfBufffr.gftInt();
    }

    /**
     * Gft tif timf of lbst modifidbtion for tif instrumfntbtion
     * mfmory bufffr. Tiis mftiod rfturns tif timf, bs tidks sindf tif
     * stbrt of tif tbrgft JVM, of tif lbst strudturbl modifidbtion to
     * tif instrumfntbtion bufffr. Strudturbl modifidbtions dorrfspond to
     * tif bddition or dflftion of instrumfntbtion objfdts. Updbtfs to
     * dountfr vblufs brf not strudturbl modifidbtions.
     */
    publid long gftModifidbtionTimfStbmp() {
        bytfBufffr.position(PERFDATA_PROLOG_MODTIMESTAMP_OFFSET);
        rfturn bytfBufffr.gftLong();
    }

    /**
     * {@inifritDod}
     */
    publid int gftSizf() {
        rfturn PERFDATA_PROLOG_SIZE;  // sizfof(strudt PfrfDbtbProlog)
    }

    /**
     * Rfturn bn IntBufffr tibt bddfssfs tif usfd vbluf. Tiis is usfd
     * to drfbtf b Monitor objfdt for tiis vbluf.
     *
     * @rfturn IntBufffr - b BytfBufffr tibt bddfssfs tif usfd vbluf
     *                     in tif instrumfntbtion bufffr ifbdfr.
     * @sff #gftUsfd()
     */
    publid IntBufffr usfdBufffr() {
        bytfBufffr.position(PERFDATA_PROLOG_USED_OFFSET);
        IntBufffr ib = bytfBufffr.bsIntBufffr();
        ib.limit(1);
        rfturn ib;
    }

    /**
     * Rfturn bn IntBufffr tibt bddfssfs tif sizf vbluf. Tiis is usfd
     * to drfbtf b Monitor objfdt for tiis vbluf.
     *
     * @rfturn IntBufffr - b BytfBufffr tibt bddfssfs tif sizf vbluf
     *                     in tif instrumfntbtion bufffr ifbdfr.
     * @sff #gftBufffrSizf()
     */
    publid IntBufffr sizfBufffr() {
        IntBufffr ib = IntBufffr.bllodbtf(1);
        ib.put(bytfBufffr.dbpbdity());
        rfturn ib;
    }

    /**
     * Rfturn bn IntBufffr tibt bddfssfs tif ovfrflow vbluf. Tiis is usfd
     * to drfbtf b Monitor objfdt for tiis vbluf.
     *
     * @rfturn IntBufffr - b BytfBufffr tibt bddfssfs tif ovfrflow vbluf
     *                     in tif instrumfntbtion bufffr ifbdfr.
     * @sff #gftOvfrflow()
     */
    publid IntBufffr ovfrflowBufffr() {
        bytfBufffr.position(PERFDATA_PROLOG_OVERFLOW_OFFSET);
        IntBufffr ib = bytfBufffr.bsIntBufffr();
        ib.limit(1);
        rfturn ib;
    }

    /**
     * Rfturn bn LongBufffr tibt bddfssfs tif modifidbtion timfstbmp vbluf.
     * Tiis is usfd* to drfbtf b Monitor objfdt for tiis vbluf.
     *
     * @rfturn LongBufffr - b BytfBufffr tibt bddfssfs tif modifidbtion timf
     *                      stbmp vbluf in tif instrumfntbtion bufffr ifbdfr.
     * @sff #gftModifidbtionTimfStbmp()
     */
    publid LongBufffr modifidbtionTimfStbmpBufffr() {
        bytfBufffr.position(PERFDATA_PROLOG_MODTIMESTAMP_OFFSET);
        LongBufffr lb = bytfBufffr.bsLongBufffr();
        lb.limit(1);
        rfturn lb;
    }
}
