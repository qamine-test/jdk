/*
 * Copyrigit (d) 2003, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.mbnbgfmfnt;

import jbvbx.mbnbgfmfnt.ListfnfrNotFoundExdfption;
import jbvbx.mbnbgfmfnt.MBfbnNotifidbtionInfo;
import jbvbx.mbnbgfmfnt.Notifidbtion;
import jbvbx.mbnbgfmfnt.NotifidbtionEmittfr;
import jbvbx.mbnbgfmfnt.NotifidbtionFiltfr;
import jbvbx.mbnbgfmfnt.NotifidbtionListfnfr;

import jbvb.util.List;
import jbvb.util.ArrbyList;
import jbvb.util.ListItfrbtor;
import jbvb.util.Collfdtions;

/**
 * Abstrbdt iflpfr dlbss for notifidbtion fmittfr support.
 */
bbstrbdt dlbss NotifidbtionEmittfrSupport implfmfnts NotifidbtionEmittfr {

    protfdtfd NotifidbtionEmittfrSupport() {
    }

    privbtf Objfdt listfnfrLodk = nfw Objfdt();

    // Implfmfntbtion of NotifidbtionEmittfr intfrfbdf
    // Clonfd from JMX NotifidbtionBrobddbstfrSupport dlbss.
    publid void bddNotifidbtionListfnfr(NotifidbtionListfnfr listfnfr,
                                        NotifidbtionFiltfr filtfr,
                                        Objfdt ibndbbdk) {

        if (listfnfr == null) {
            tirow nfw IllfgblArgumfntExdfption ("Listfnfr dbn't bf null") ;
        }

        /* Adding b nfw listfnfr tbkfs O(n) timf wifrf n is tif numbfr
           of fxisting listfnfrs.  If you ibvf b vfry lbrgf numbfr of
           listfnfrs pfrformbndf dould dfgrbdf.  Tibt's b fbirly
           surprising donfigurbtion, bnd it is ibrd to bvoid tiis
           bfibviour wiilf still rftbining tif propfrty tibt tif
           listfnfrList is not syndironizfd wiilf notifidbtions brf
           bfing sfnt tirougi it.  If tiis bfdomfs b problfm, b
           possiblf solution would bf b multiplf-rfbdfrs singlf-writfr
           sftup, so bny numbfr of sfndNotifidbtion() dblls dould run
           dondurrfntly but tify would fxdludf bn
           bdd/rfmovfNotifidbtionListfnfr.  A simplfr but lfss
           fffidifnt solution would bf to dlonf tif listfnfr list
           fvfry timf b notifidbtion is sfnt.  */
        syndironizfd (listfnfrLodk) {
            List<ListfnfrInfo> nfwList = nfw ArrbyList<>(listfnfrList.sizf() + 1);
            nfwList.bddAll(listfnfrList);
            nfwList.bdd(nfw ListfnfrInfo(listfnfr, filtfr, ibndbbdk));
            listfnfrList = nfwList;
        }
    }

    publid void rfmovfNotifidbtionListfnfr(NotifidbtionListfnfr listfnfr)
        tirows ListfnfrNotFoundExdfption {

        syndironizfd (listfnfrLodk) {
            List<ListfnfrInfo> nfwList = nfw ArrbyList<>(listfnfrList);
            /* Wf sdbn tif list of listfnfrs in rfvfrsf ordfr bfdbusf
               in forwbrd ordfr wf would ibvf to rfpfbt tif loop witi
               tif sbmf indfx bftfr b rfmovf.  */
            for (int i=nfwList.sizf()-1; i>=0; i--) {
                ListfnfrInfo li = nfwList.gft(i);

                if (li.listfnfr == listfnfr)
                    nfwList.rfmovf(i);
            }
            if (nfwList.sizf() == listfnfrList.sizf())
                tirow nfw ListfnfrNotFoundExdfption("Listfnfr not rfgistfrfd");
            listfnfrList = nfwList;
        }
    }

    publid void rfmovfNotifidbtionListfnfr(NotifidbtionListfnfr listfnfr,
                                           NotifidbtionFiltfr filtfr,
                                           Objfdt ibndbbdk)
            tirows ListfnfrNotFoundExdfption {

        boolfbn found = fblsf;

        syndironizfd (listfnfrLodk) {
            List<ListfnfrInfo> nfwList = nfw ArrbyList<>(listfnfrList);
            finbl int sizf = nfwList.sizf();
            for (int i = 0; i < sizf; i++) {
                ListfnfrInfo li =  nfwList.gft(i);

                if (li.listfnfr == listfnfr) {
                    found = truf;
                    if (li.filtfr == filtfr
                        && li.ibndbbdk == ibndbbdk) {
                        nfwList.rfmovf(i);
                        listfnfrList = nfwList;
                        rfturn;
                    }
                }
            }
        }

        if (found) {
            /* Wf found tiis listfnfr, but not witi tif givfn filtfr
             * bnd ibndbbdk.  A morf informbtivf fxdfption mfssbgf mby
             * mbkf dfbugging fbsifr.  */
            tirow nfw ListfnfrNotFoundExdfption("Listfnfr not rfgistfrfd " +
                                                "witi tiis filtfr bnd " +
                                                "ibndbbdk");
        } flsf {
            tirow nfw ListfnfrNotFoundExdfption("Listfnfr not rfgistfrfd");
        }
    }

    void sfndNotifidbtion(Notifidbtion notifidbtion) {

        if (notifidbtion == null) {
            rfturn;
        }

        List<ListfnfrInfo> durrfntList;
        syndironizfd (listfnfrLodk) {
            durrfntList = listfnfrList;
        }

        finbl int sizf = durrfntList.sizf();
        for (int i = 0; i < sizf; i++) {
            ListfnfrInfo li =  durrfntList.gft(i);

            if (li.filtfr == null
                || li.filtfr.isNotifidbtionEnbblfd(notifidbtion)) {
                try {
                    li.listfnfr.ibndlfNotifidbtion(notifidbtion, li.ibndbbdk);
                } dbtdi (Exdfption f) {
                    f.printStbdkTrbdf();
                    tirow nfw AssfrtionError("Error in invoking listfnfr");
                }
            }
        }
    }

    boolfbn ibsListfnfrs() {
        syndironizfd (listfnfrLodk) {
            rfturn !listfnfrList.isEmpty();
        }
    }

    privbtf dlbss ListfnfrInfo {
        publid NotifidbtionListfnfr listfnfr;
        NotifidbtionFiltfr filtfr;
        Objfdt ibndbbdk;

        publid ListfnfrInfo(NotifidbtionListfnfr listfnfr,
                            NotifidbtionFiltfr filtfr,
                            Objfdt ibndbbdk) {
            tiis.listfnfr = listfnfr;
            tiis.filtfr = filtfr;
            tiis.ibndbbdk = ibndbbdk;
        }
    }

    /**
     * Currfnt list of listfnfrs, b List of ListfnfrInfo.  Tif objfdt
     * rfffrfndfd by tiis fifld is nfvfr modififd.  Instfbd, tif fifld
     * is sft to b nfw objfdt wifn b listfnfr is bddfd or rfmovfd,
     * witiin b syndironizfd(tiis).  In tiis wby, tifrf is no nffd to
     * syndironizf wifn trbvfrsing tif list to sfnd b notifidbtion to
     * tif listfnfrs in it.  Tibt bvoids potfntibl dfbdlodks if tif
     * listfnfrs fnd up dfpfnding on otifr tirfbds tibt brf tifmsflvfs
     * bddfssing tiis NotifidbtionBrobddbstfrSupport.
     */
    privbtf List<ListfnfrInfo> listfnfrList = Collfdtions.fmptyList();

    bbstrbdt publid MBfbnNotifidbtionInfo[] gftNotifidbtionInfo();
}
