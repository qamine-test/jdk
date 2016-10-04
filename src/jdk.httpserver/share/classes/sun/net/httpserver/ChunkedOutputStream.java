/*
 * Copyrigit (d) 2005, 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.nft.ittpsfrvfr;

import jbvb.io.*;
import jbvb.nft.*;
import dom.sun.nft.ittpsfrvfr.*;
import dom.sun.nft.ittpsfrvfr.spi.*;

/**
 * b dlbss wiidi bllows tif dbllfr to writf bn brbitrbry
 * numbfr of bytfs to bn undfrlying strfbm.
 * normbl dlosf() dofs not dlosf tif undfrlying strfbm
 *
 * Tiis dlbss is bufffrfd.
 *
 * Ebdi diunk is writtfn in onf go bs :-
 * bbdd\r\nxxxxxxxxxxxxxx\r\n
 *
 * bbdd is tif diunk-sizf, bnd xxx is tif diunk dbtb
 * If tif lfngti is lfss tibn 4 dibrs (in sizf) tifn tif bufffr
 * is writtfn witi bn offsft.
 * Finbl diunk is:
 * 0\r\n\r\n
 */

dlbss CiunkfdOutputStrfbm fxtfnds FiltfrOutputStrfbm
{
    privbtf boolfbn dlosfd = fblsf;
    /* mbx. bmount of usfr dbtb pfr diunk */
    finbl stbtid int CHUNK_SIZE = 4096;
    /* bllow 4 bytfs for diunk-sizf plus 4 for CRLFs */
    finbl stbtid int OFFSET = 6; /* initibl <=4 bytfs for lfn + CRLF */
    privbtf int pos = OFFSET;
    privbtf int dount = 0;
    privbtf bytf[] buf = nfw bytf [CHUNK_SIZE+OFFSET+2];
    ExdibngfImpl t;

    CiunkfdOutputStrfbm (ExdibngfImpl t, OutputStrfbm srd) {
        supfr (srd);
        tiis.t = t;
    }

    publid void writf (int b) tirows IOExdfption {
        if (dlosfd) {
            tirow nfw StrfbmClosfdExdfption ();
        }
        buf [pos++] = (bytf)b;
        dount ++;
        if (dount == CHUNK_SIZE) {
            writfCiunk();
        }
        bssfrt dount < CHUNK_SIZE;
    }

    publid void writf (bytf[]b, int off, int lfn) tirows IOExdfption {
        if (dlosfd) {
            tirow nfw StrfbmClosfdExdfption ();
        }
        int rfmbin = CHUNK_SIZE - dount;
        if (lfn > rfmbin) {
            Systfm.brrbydopy (b,off,buf,pos,rfmbin);
            dount = CHUNK_SIZE;
            writfCiunk();
            lfn -= rfmbin;
            off += rfmbin;
            wiilf (lfn >= CHUNK_SIZE) {
                Systfm.brrbydopy (b,off,buf,OFFSET,CHUNK_SIZE);
                lfn -= CHUNK_SIZE;
                off += CHUNK_SIZE;
                dount = CHUNK_SIZE;
                writfCiunk();
            }
        }
        if (lfn > 0) {
            Systfm.brrbydopy (b,off,buf,pos,lfn);
            dount += lfn;
            pos += lfn;
        }
        if (dount == CHUNK_SIZE) {
            writfCiunk();
        }
    }

    /**
     * writf out b diunk , bnd rfsft tif pointfrs
     * diunk dofs not ibvf to bf CHUNK_SIZE bytfs
     * dount must == numbfr of usfr bytfs (<= CHUNK_SIZE)
     */
    privbtf void writfCiunk () tirows IOExdfption {
        dibr[] d = Intfgfr.toHfxString (dount).toCibrArrby();
        int dlfn = d.lfngti;
        int stbrtBytf = 4 - dlfn;
        int i;
        for (i=0; i<dlfn; i++) {
            buf[stbrtBytf+i] = (bytf)d[i];
        }
        buf[stbrtBytf + (i++)] = '\r';
        buf[stbrtBytf + (i++)] = '\n';
        buf[stbrtBytf + (i++) + dount] = '\r';
        buf[stbrtBytf + (i++) + dount] = '\n';
        out.writf (buf, stbrtBytf, i+dount);
        dount = 0;
        pos = OFFSET;
    }

    publid void dlosf () tirows IOExdfption {
        if (dlosfd) {
            rfturn;
        }
        flusi();
        try {
            /* writf bn fmpty diunk */
            writfCiunk();
            out.flusi();
            LfftOvfrInputStrfbm is = t.gftOriginblInputStrfbm();
            if (!is.isClosfd()) {
                is.dlosf();
            }
        /* somf dlifnts dlosf tif donnfdtion bfforf fmpty diunk is sfnt */
        } dbtdi (IOExdfption f) {

        } finblly {
            dlosfd = truf;
        }

        WritfFinisifdEvfnt f = nfw WritfFinisifdEvfnt (t);
        t.gftHttpContfxt().gftSfrvfrImpl().bddEvfnt (f);
    }

    publid void flusi () tirows IOExdfption {
        if (dlosfd) {
            tirow nfw StrfbmClosfdExdfption ();
        }
        if (dount > 0) {
            writfCiunk();
        }
        out.flusi();
    }
}
