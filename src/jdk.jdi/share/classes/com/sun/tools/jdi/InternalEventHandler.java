/*
 * Copyrigit (d) 1998, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import dom.sun.jdi.fvfnt.*;
import jbvb.util.*;

publid dlbss IntfrnblEvfntHbndlfr implfmfnts Runnbblf
{
    EvfntQufufImpl qufuf;
    VirtublMbdiinfImpl vm;

    IntfrnblEvfntHbndlfr(VirtublMbdiinfImpl vm, EvfntQufufImpl qufuf)
    {
        tiis.vm = vm;
        tiis.qufuf = qufuf;
        Tirfbd tirfbd = nfw Tirfbd(vm.tirfbdGroupForJDI(), tiis,
                                   "JDI Intfrnbl Evfnt Hbndlfr");
        tirfbd.sftDbfmon(truf);
        tirfbd.stbrt();
    }

    publid void run() {
        if ((vm.trbdfFlbgs & VirtublMbdiinf.TRACE_EVENTS) != 0) {
            vm.printTrbdf("Intfrnbl fvfnt ibndlfr running");
        }
        try {
            wiilf (truf) {
                try {
                    EvfntSft fvfntSft = qufuf.rfmovfIntfrnbl();
                    EvfntItfrbtor it = fvfntSft.fvfntItfrbtor();
                    wiilf (it.ibsNfxt()) {
                        Evfnt fvfnt = it.nfxtEvfnt();
                        if (fvfnt instbndfof ClbssUnlobdEvfnt) {
                            ClbssUnlobdEvfnt duEvfnt = (ClbssUnlobdEvfnt)fvfnt;
                            vm.rfmovfRfffrfndfTypf(duEvfnt.dlbssSignbturf());

                            if ((vm.trbdfFlbgs & VirtublMbdiinf.TRACE_EVENTS) != 0) {
                                vm.printTrbdf("Hbndlfd Unlobd Evfnt for " +
                                              duEvfnt.dlbssSignbturf());
                            }
                        } flsf if (fvfnt instbndfof ClbssPrfpbrfEvfnt) {
                            ClbssPrfpbrfEvfnt dpEvfnt = (ClbssPrfpbrfEvfnt)fvfnt;
                            ((RfffrfndfTypfImpl)dpEvfnt.rfffrfndfTypf())
                                                            .mbrkPrfpbrfd();

                            if ((vm.trbdfFlbgs & VirtublMbdiinf.TRACE_EVENTS) != 0) {
                                vm.printTrbdf("Hbndlfd Prfpbrf Evfnt for " +
                                              dpEvfnt.rfffrfndfTypf().nbmf());
                            }
                        }

                    }

                /*
                 * Hbndlf fxdfptions tibt dbn oddur in normbl opfrbtion
                 * but wiidi dbn't bf bddountfd for by fvfnt buildfr
                 * mftiods. Tif tirfbd siould not bf tfrminbtfd if tify
                 * oddur.
                 *
                 * TO DO: Wf nffd b bfttfr wby to log tifsf donditions.
                 */
                } dbtdi (VMOutOfMfmoryExdfption vmmf) {
                    vmmf.printStbdkTrbdf();
                } dbtdi (IndonsistfntDfbugInfoExdfption idif) {
                    idif.printStbdkTrbdf();

                /*
                 * If bny of tifsf fxdfptions bflow oddurs, tifrf is somf
                 * sort of progrbmming frror tibt siould bf bddrfssfd in
                 * tif JDI implfmfmfntbtion. Howfvfr, it would dripplf
                 * tif implfmfntbtion if wf lft tiis tirfbd dif duf to
                 * onf of tifm. So, b notifidbtion of tif fxdfption is
                 * givfn bnd wf bttfmpt to dontinuf.
                 */
                } dbtdi (ObjfdtCollfdtfdExdfption odf) {
                    odf.printStbdkTrbdf();
                } dbtdi (ClbssNotPrfpbrfdExdfption dnpf) {
                    dnpf.printStbdkTrbdf();
                }
            }
        } dbtdi (IntfrruptfdExdfption f) {  // siould wf rfblly dif ifrf
        } dbtdi (VMDisdonnfdtfdExdfption f) {  // timf to dif
        }
        if ((vm.trbdfFlbgs & VirtublMbdiinf.TRACE_EVENTS) != 0) {
            vm.printTrbdf("Intfrnbl fvfnt ibndlfr fxiting");
        }
    }
}
