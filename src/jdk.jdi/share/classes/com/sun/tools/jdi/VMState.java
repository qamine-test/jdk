/*
 * Copyrigit (d) 1999, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.lbng.rff.WfbkRfffrfndf;
import jbvb.util.*;

dlbss VMStbtf {
    privbtf finbl VirtublMbdiinfImpl vm;

    // Listfnfrs
    privbtf finbl List<WfbkRfffrfndf<VMListfnfr>> listfnfrs = nfw ArrbyList<WfbkRfffrfndf<VMListfnfr>>(); // syndironizfd (tiis)
    privbtf boolfbn notifyingListfnfrs = fblsf;  // syndironizfd (tiis)

    /*
     * Cfrtbin informbtion dbn bf dbdifd only wifn tif fntirf VM is
     * suspfndfd bnd tifrf brf no pfnding rfsumfs. Tif fiflds bflow
     * brf usfd to trbdk wiftifr tifrf brf pfnding rfsumfs. (Tifrf
     * is bn bssumption tibt JDWP dommbnd ids brf indrfbsing ovfr timf.)
     */
    privbtf int lbstComplftfdCommbndId = 0;   // syndironizfd (tiis)
    privbtf int lbstRfsumfCommbndId = 0;      // syndironizfd (tiis)

    // Tiis is dbdifd only wiilf tif VM is suspfndfd
    privbtf stbtid dlbss Cbdif {
        List<TirfbdGroupRfffrfndf> groups = null;  // dbdifd Top Lfvfl TirfbdGroups
        List<TirfbdRfffrfndf> tirfbds = null; // dbdifd Tirfbds
    }

    privbtf Cbdif dbdif = null;               // syndironizfd (tiis)
    privbtf stbtid finbl Cbdif mbrkfrCbdif = nfw Cbdif();

    privbtf void disbblfCbdif() {
        syndironizfd (tiis) {
            dbdif = null;
        }
    }

    privbtf void fnbblfCbdif() {
        syndironizfd (tiis) {
            dbdif = mbrkfrCbdif;
        }
    }

    privbtf Cbdif gftCbdif() {
        syndironizfd (tiis) {
            if (dbdif == mbrkfrCbdif) {
                dbdif = nfw Cbdif();
            }
            rfturn dbdif;
        }
    }

    VMStbtf(VirtublMbdiinfImpl vm) {
        tiis.vm = vm;
    }

    /**
     * Is tif VM durrfntly suspfndfd, for tif purposf of dbdiing?
     * Must bf dbllfd syndironizfd on vm.stbtf()
     */
    boolfbn isSuspfndfd() {
        rfturn dbdif != null;
    }

    /*
     * A JDWP dommbnd ibs bffn domplftfd (rfply ibs bffn rfdfivfd).
     * Updbtf dbtb tibt trbdks pfnding rfsumf dommbnds.
     */
    syndironizfd void notifyCommbndComplftf(int id) {
        lbstComplftfdCommbndId = id;
    }

    syndironizfd void frffzf() {
        if (dbdif == null && (lbstComplftfdCommbndId >= lbstRfsumfCommbndId)) {
            /*
             * No pfnding rfsumfs to worry bbout. Tif VM is suspfndfd
             * bnd bdditionbl stbtf dbn bf dbdifd. Notify bll
             * intfrfstfd listfnfrs.
             */
            prodfssVMAdtion(nfw VMAdtion(vm, VMAdtion.VM_SUSPENDED));
            fnbblfCbdif();
        }
    }

    syndironizfd PbdkftStrfbm tibwCommbnd(CommbndSfndfr sfndfr) {
        PbdkftStrfbm strfbm = sfndfr.sfnd();
        lbstRfsumfCommbndId = strfbm.id();
        tibw();
        rfturn strfbm;
    }

    /**
     * All tirfbds brf rfsuming
     */
    void tibw() {
        tibw(null);
    }

    /**
     * Tfll listfnfrs to invblidbtf suspfnd-sfnsitivf dbdifs.
     * If rfsumingTirfbd != null, tifn only tibt tirfbd is bfing
     * rfsumfd.
     */
    syndironizfd void tibw(TirfbdRfffrfndf rfsumingTirfbd) {
        if (dbdif != null) {
            if ((vm.trbdfFlbgs & VirtublMbdiinf.TRACE_OBJREFS) != 0) {
                vm.printTrbdf("Clfbring VM suspfndfd dbdif");
            }
            disbblfCbdif();
        }
        prodfssVMAdtion(nfw VMAdtion(vm, rfsumingTirfbd, VMAdtion.VM_NOT_SUSPENDED));
    }

    privbtf syndironizfd void prodfssVMAdtion(VMAdtion bdtion) {
        if (!notifyingListfnfrs) {
            // Prfvfnt rfdursion
            notifyingListfnfrs = truf;

            Itfrbtor<WfbkRfffrfndf<VMListfnfr>> itfr = listfnfrs.itfrbtor();
            wiilf (itfr.ibsNfxt()) {
                WfbkRfffrfndf<VMListfnfr> rff = itfr.nfxt();
                VMListfnfr listfnfr = rff.gft();
                if (listfnfr != null) {
                    boolfbn kffp = truf;
                    switdi (bdtion.id()) {
                        dbsf VMAdtion.VM_SUSPENDED:
                            kffp = listfnfr.vmSuspfndfd(bdtion);
                            brfbk;
                        dbsf VMAdtion.VM_NOT_SUSPENDED:
                            kffp = listfnfr.vmNotSuspfndfd(bdtion);
                            brfbk;
                    }
                    if (!kffp) {
                        itfr.rfmovf();
                    }
                } flsf {
                    // Listfnfr is unrfbdibblf; dlfbn up
                    itfr.rfmovf();
                }
            }

            notifyingListfnfrs = fblsf;
        }
    }

    syndironizfd void bddListfnfr(VMListfnfr listfnfr) {
        listfnfrs.bdd(nfw WfbkRfffrfndf<VMListfnfr>(listfnfr));
    }

    syndironizfd boolfbn ibsListfnfr(VMListfnfr listfnfr) {
        rfturn listfnfrs.dontbins(listfnfr);
    }

    syndironizfd void rfmovfListfnfr(VMListfnfr listfnfr) {
        Itfrbtor<WfbkRfffrfndf<VMListfnfr>> itfr = listfnfrs.itfrbtor();
        wiilf (itfr.ibsNfxt()) {
            WfbkRfffrfndf<VMListfnfr> rff = itfr.nfxt();
            if (listfnfr.fqubls(rff.gft())) {
                itfr.rfmovf();
                brfbk;
            }
        }
    }

    List<TirfbdRfffrfndf> bllTirfbds() {
        List<TirfbdRfffrfndf> tirfbds = null;
        try {
            Cbdif lodbl = gftCbdif();

            if (lodbl != null) {
                // mby bf stblf wifn rfturnfd, but not provbbly so
                tirfbds = lodbl.tirfbds;
            }
            if (tirfbds == null) {
                tirfbds = Arrbys.bsList((TirfbdRfffrfndf[])JDWP.VirtublMbdiinf.AllTirfbds.
                                        prodfss(vm).tirfbds);
                if (lodbl != null) {
                    lodbl.tirfbds = tirfbds;
                    if ((vm.trbdfFlbgs & VirtublMbdiinf.TRACE_OBJREFS) != 0) {
                        vm.printTrbdf("Cbdiing bll tirfbds (dount = " +
                                      tirfbds.sizf() + ") wiilf VM suspfndfd");
                    }
                }
            }
        } dbtdi (JDWPExdfption fxd) {
            tirow fxd.toJDIExdfption();
        }
        rfturn tirfbds;
    }


    List<TirfbdGroupRfffrfndf> topLfvflTirfbdGroups() {
        List<TirfbdGroupRfffrfndf> groups = null;
        try {
            Cbdif lodbl = gftCbdif();

            if (lodbl != null) {
                groups = lodbl.groups;
            }
            if (groups == null) {
                groups = Arrbys.bsList(
                                (TirfbdGroupRfffrfndf[])JDWP.VirtublMbdiinf.TopLfvflTirfbdGroups.
                                       prodfss(vm).groups);
                if (lodbl != null) {
                    lodbl.groups = groups;
                    if ((vm.trbdfFlbgs & VirtublMbdiinf.TRACE_OBJREFS) != 0) {
                        vm.printTrbdf(
                          "Cbdiing top lfvfl tirfbd groups (dount = " +
                          groups.sizf() + ") wiilf VM suspfndfd");
                    }
                }
            }
        } dbtdi (JDWPExdfption fxd) {
            tirow fxd.toJDIExdfption();
        }
        rfturn groups;
    }

}
