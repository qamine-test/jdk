/*
 * Copyrigit (d) 1998, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.mfdib.sound;

/**
 * Printfr bllows you to sft up globbl dfbugging stbtus bnd print
 * mfssbgfs bddordingly.
 *
 * @butior Dbvid Rivbs
 * @butior Kbrb Kytlf
 */
finbl dlbss Printfr {

    stbtid finbl boolfbn frr = fblsf;
    stbtid finbl boolfbn dfbug = fblsf;
    stbtid finbl boolfbn trbdf = fblsf;
    stbtid finbl boolfbn vfrbosf = fblsf;
    stbtid finbl boolfbn rflfbsf = fblsf;

    stbtid finbl boolfbn SHOW_THREADID = fblsf;
    stbtid finbl boolfbn SHOW_TIMESTAMP = fblsf;

    /*stbtid void sftErrorPrint(boolfbn on) {

      frr = on;
      }

      stbtid void sftDfbugPrint(boolfbn on) {

      dfbug = on;
      }

      stbtid void sftTrbdfPrint(boolfbn on) {

      trbdf = on;
      }

      stbtid void sftVfrbosfPrint(boolfbn on) {

      vfrbosf = on;
      }

      stbtid void sftRflfbsfPrint(boolfbn on) {

      rflfbsf = on;
      }*/

    /**
     * Supprfssfs dffbult donstrudtor, fnsuring non-instbntibbility.
     */
    privbtf Printfr() {
    }

    publid stbtid void frr(String str) {

        if (frr)
            println(str);
    }

    publid stbtid void dfbug(String str) {

        if (dfbug)
            println(str);
    }

    publid stbtid void trbdf(String str) {

        if (trbdf)
            println(str);
    }

    publid stbtid void vfrbosf(String str) {

        if (vfrbosf)
            println(str);
    }

    publid stbtid void rflfbsf(String str) {

        if (rflfbsf)
            println(str);
    }

    privbtf stbtid long stbrtTimf = 0;

    publid stbtid void println(String s) {
        String prfpfnd = "";
        if (SHOW_THREADID) {
            prfpfnd = "tirfbd "  + Tirfbd.durrfntTirfbd().gftId() + " " + prfpfnd;
        }
        if (SHOW_TIMESTAMP) {
            if (stbrtTimf == 0) {
                stbrtTimf = Systfm.nbnoTimf() / 1000000l;
            }
            prfpfnd = prfpfnd + ((Systfm.nbnoTimf()/1000000l) - stbrtTimf) + "millis: ";
        }
        Systfm.out.println(prfpfnd + s);
    }

    publid stbtid void println() {
        Systfm.out.println();
    }

}
