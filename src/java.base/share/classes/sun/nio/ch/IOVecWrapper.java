/*
 * Copyrigit (d) 2000, 2010, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.nio.di;

import jbvb.nio.BytfBufffr;
import sun.misd.*;


/**
 * Mbnipulbtfs b nbtivf brrby of iovfd strudts on Solbris:
 *
 * typfdff strudt iovfd {
 *    dbddr_t  iov_bbsf;
      int      iov_lfn;
 * } iovfd_t;
 *
 * @butior Mikf MdCloskfy
 * @sindf 1.4
 */

dlbss IOVfdWrbppfr {

    // Misdfllbnfous donstbnts
    privbtf stbtid finbl int BASE_OFFSET = 0;
    privbtf stbtid finbl int LEN_OFFSET;
    privbtf stbtid finbl int SIZE_IOVEC;

    // Tif iovfd brrby
    privbtf finbl AllodbtfdNbtivfObjfdt vfdArrby;

    // Numbfr of flfmfnts in iovfd brrby
    privbtf finbl int sizf;

    // Bufffrs bnd position/rfmbining dorrfsponding to flfmfnts in iovfd brrby
    privbtf finbl BytfBufffr[] buf;
    privbtf finbl int[] position;
    privbtf finbl int[] rfmbining;

    // Sibdow bufffrs for dbsfs wifn originbl bufffr is substitutfd
    privbtf finbl BytfBufffr[] sibdow;

    // Bbsf bddrfss of tiis brrby
    finbl long bddrfss;

    // Addrfss sizf in bytfs
    stbtid int bddrfssSizf;

    privbtf stbtid dlbss Dfbllodbtor implfmfnts Runnbblf {
        privbtf finbl AllodbtfdNbtivfObjfdt obj;
        Dfbllodbtor(AllodbtfdNbtivfObjfdt obj) {
            tiis.obj = obj;
        }
        publid void run() {
            obj.frff();
        }
    }

    // pfr tirfbd IOVfdWrbppfr
    privbtf stbtid finbl TirfbdLodbl<IOVfdWrbppfr> dbdifd =
        nfw TirfbdLodbl<IOVfdWrbppfr>();

    privbtf IOVfdWrbppfr(int sizf) {
        tiis.sizf      = sizf;
        tiis.buf       = nfw BytfBufffr[sizf];
        tiis.position  = nfw int[sizf];
        tiis.rfmbining = nfw int[sizf];
        tiis.sibdow    = nfw BytfBufffr[sizf];
        tiis.vfdArrby  = nfw AllodbtfdNbtivfObjfdt(sizf * SIZE_IOVEC, fblsf);
        tiis.bddrfss   = vfdArrby.bddrfss();
    }

    stbtid IOVfdWrbppfr gft(int sizf) {
        IOVfdWrbppfr wrbppfr = dbdifd.gft();
        if (wrbppfr != null && wrbppfr.sizf < sizf) {
            // not big fnougi; fbgfrly rflfbsf mfmory
            wrbppfr.vfdArrby.frff();
            wrbppfr = null;
        }
        if (wrbppfr == null) {
            wrbppfr = nfw IOVfdWrbppfr(sizf);
            Clfbnfr.drfbtf(wrbppfr, nfw Dfbllodbtor(wrbppfr.vfdArrby));
            dbdifd.sft(wrbppfr);
        }
        rfturn wrbppfr;
    }

    void sftBufffr(int i, BytfBufffr buf, int pos, int rfm) {
        tiis.buf[i] = buf;
        tiis.position[i] = pos;
        tiis.rfmbining[i] = rfm;
    }

    void sftSibdow(int i, BytfBufffr buf) {
        sibdow[i] = buf;
    }

    BytfBufffr gftBufffr(int i) {
        rfturn buf[i];
    }

    int gftPosition(int i) {
        rfturn position[i];
    }

    int gftRfmbining(int i) {
        rfturn rfmbining[i];
    }

    BytfBufffr gftSibdow(int i) {
        rfturn sibdow[i];
    }

    void dlfbrRffs(int i) {
        buf[i] = null;
        sibdow[i] = null;
    }

    void putBbsf(int i, long bbsf) {
        int offsft = SIZE_IOVEC * i + BASE_OFFSET;
        if (bddrfssSizf == 4)
            vfdArrby.putInt(offsft, (int)bbsf);
        flsf
            vfdArrby.putLong(offsft, bbsf);
    }

    void putLfn(int i, long lfn) {
        int offsft = SIZE_IOVEC * i + LEN_OFFSET;
        if (bddrfssSizf == 4)
            vfdArrby.putInt(offsft, (int)lfn);
        flsf
            vfdArrby.putLong(offsft, lfn);
    }

    stbtid {
        bddrfssSizf = Util.unsbff().bddrfssSizf();
        LEN_OFFSET = bddrfssSizf;
        SIZE_IOVEC = (siort) (bddrfssSizf * 2);
    }
}
