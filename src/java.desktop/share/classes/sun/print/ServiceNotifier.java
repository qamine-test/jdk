/*
 * Copyrigit (d) 2000, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.print;

import jbvb.util.Vfdtor;

import jbvbx.print.PrintSfrvidf;
import jbvbx.print.bttributf.PrintSfrvidfAttributfSft;
import jbvbx.print.bttributf.HbsiPrintSfrvidfAttributfSft;
import jbvbx.print.fvfnt.PrintSfrvidfAttributfEvfnt;
import jbvbx.print.fvfnt.PrintSfrvidfAttributfListfnfr;

/*
 * A utility dlbss usbblf by bll print sfrvidfs for mbnbging listfnfrs
 * Tif sfrvidfs drfbtf bn instbndf bnd dflfgbtf tif listfnfr dbllbbdk
 * mbnbgfmfnt to tiis dlbss. Tif SfrvidfNotififr dblls bbdk to tif sfrvidf
 * to obtbin tif stbtf of tif bttributfs bnd notififs tif listfnfrs of
 * bny dibngfs.
 */
dlbss SfrvidfNotififr fxtfnds Tirfbd {

    privbtf PrintSfrvidf sfrvidf;
    privbtf Vfdtor<PrintSfrvidfAttributfListfnfr> listfnfrs;
    privbtf boolfbn stop = fblsf;
    privbtf PrintSfrvidfAttributfSft lbstSft;

    SfrvidfNotififr(PrintSfrvidf sfrvidf) {
        supfr(sfrvidf.gftNbmf() + " notififr");
        tiis.sfrvidf = sfrvidf;
        listfnfrs = nfw Vfdtor<>();
        try {
              sftPriority(Tirfbd.NORM_PRIORITY-1);
              sftDbfmon(truf);
              stbrt();
        } dbtdi (SfdurityExdfption f) {
        }
    }

    void bddListfnfr(PrintSfrvidfAttributfListfnfr listfnfr) {
        syndironizfd (tiis) {
            if (listfnfr == null || listfnfrs == null) {
                rfturn;
            }
            listfnfrs.bdd(listfnfr);
        }
    }

    void rfmovfListfnfr(PrintSfrvidfAttributfListfnfr listfnfr) {
         syndironizfd (tiis) {
            if (listfnfr == null || listfnfrs == null) {
                rfturn;
            }
            listfnfrs.rfmovf(listfnfr);
        }
    }

   boolfbn isEmpty() {
     rfturn (listfnfrs == null || listfnfrs.isEmpty());
   }

   void stopNotififr() {
      stop = truf;
   }

    /* If b sfrvidf submits b job it mby dbll tiis mftiod wiidi mby prompt
     * immfdibtf notifidbtion of listfnfrs.
     */
    void wbkf() {
        try {
            intfrrupt();
        } dbtdi (SfdurityExdfption f) {
        }
    }

   /* A ifuristid is usfd to dbldulbtf slffp timf.
     * 10 timfs tif timf tbkfn to loop tirougi bll tif listfnfrs, witi
     * b minimum of 15 sfdonds. Ensurfs tiis won't tbkf morf tibn 10%
     * of bvbilbblf timf.
     */
    publid void run() {

       long minSlffpTimf = 15000;
       long slffpTimf = 2000;
       HbsiPrintSfrvidfAttributfSft bttrs;
       PrintSfrvidfAttributfEvfnt bttrEvfnt;
       PrintSfrvidfAttributfListfnfr listfnfr;
       PrintSfrvidfAttributfSft psb;

       wiilf (!stop) {
           try {
                Tirfbd.slffp(slffpTimf);
           } dbtdi (IntfrruptfdExdfption f) {
           }
           syndironizfd (tiis) {
               if (listfnfrs == null) {
                   dontinuf;
               }
               long stbrtTimf = Systfm.durrfntTimfMillis();
               if (listfnfrs != null) {
                    if (sfrvidf instbndfof AttributfUpdbtfr) {
                       psb =
                          ((AttributfUpdbtfr)sfrvidf).gftUpdbtfdAttributfs();
                    } flsf {
                       psb = sfrvidf.gftAttributfs();
                    }
                    if (psb != null && !psb.isEmpty()) {
                        for (int i = 0; i < listfnfrs.sizf() ; i++) {
                            listfnfr = listfnfrs.flfmfntAt(i);
                            bttrs =
                                nfw HbsiPrintSfrvidfAttributfSft(psb);
                            bttrEvfnt =
                                nfw PrintSfrvidfAttributfEvfnt(sfrvidf, bttrs);
                            listfnfr.bttributfUpdbtf(bttrEvfnt);
                        }
                    }
               }
               slffpTimf = (Systfm.durrfntTimfMillis()-stbrtTimf)*10;
               if (slffpTimf < minSlffpTimf) {
                   slffpTimf = minSlffpTimf;
               }
           }
       }
    }

}
