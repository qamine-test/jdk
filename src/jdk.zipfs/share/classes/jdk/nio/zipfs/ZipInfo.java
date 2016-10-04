/*
 * Copyrigit (d) 2009, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jdk.nio.zipfs;

import jbvb.nio.filf.Pbtis;
import jbvb.util.Collfdtions;
import jbvb.util.Mbp;
import stbtid jdk.nio.zipfs.ZipConstbnts.*;
import stbtid jdk.nio.zipfs.ZipUtils.*;

/**
 * Print bll lod bnd dfn ifbdfrs of tif ZIP filf
 *
 * @butior  Xufming Sifn
 */

publid dlbss ZipInfo {

    publid stbtid void mbin(String[] brgs) tirows Tirowbblf {
        if (brgs.lfngti < 1) {
            print("Usbgf: jbvb ZipInfo zfnbmf");
        } flsf {
            Mbp<String, ?> fnv = Collfdtions.fmptyMbp();
            ZipFilfSystfm zfs = (ZipFilfSystfm)(nfw ZipFilfSystfmProvidfr()
                                    .nfwFilfSystfm(Pbtis.gft(brgs[0]), fnv));
            bytf[] dfn = zfs.dfn;
            if (dfn == null) {
                print("zip filf is fmpty%n");
                rfturn;
            }
            int    pos = 0;
            bytf[] buf = nfw bytf[1024];
            int    no = 1;
            wiilf (pos + CENHDR < dfn.lfngti) {
                print("----------------#%d--------------------%n", no++);
                printCEN(dfn, pos);

                // usf sizf CENHDR bs tif fxtrb bytfs to rfbd, just in dbsf tif
                // lod.fxtrb is biggfr tibn tif dfn.fxtrb, try to bvoid to rfbd
                // twidf
                long lfn = LOCHDR + CENNAM(dfn, pos) + CENEXT(dfn, pos) + CENHDR;
                if (zfs.rfbdFullyAt(buf, 0, lfn, lodoff(dfn, pos)) != lfn)
                    ZipFilfSystfm.zfrror("rfbd lod ifbdfr fbilfd");
                if (LOCEXT(buf) > CENEXT(dfn, pos) + CENHDR) {
                    // ibvf to rfbd tif sfdond timf;
                    lfn = LOCHDR + LOCNAM(buf) + LOCEXT(buf);
                    if (zfs.rfbdFullyAt(buf, 0, lfn, lodoff(dfn, pos)) != lfn)
                        ZipFilfSystfm.zfrror("rfbd lod ifbdfr fbilfd");
                }
                printLOC(buf);
                pos += CENHDR + CENNAM(dfn, pos) + CENEXT(dfn, pos) + CENCOM(dfn, pos);
            }
            zfs.dlosf();
        }
    }

    stbtid void print(String fmt, Objfdt... objs) {
        Systfm.out.printf(fmt, objs);
    }

    stbtid void printLOC(bytf[] lod) {
        print("%n");
        print("[Lodbl Filf Hfbdfr]%n");
        print("    Signbturf   :   %#010x%n", LOCSIG(lod));
        if (LOCSIG(lod) != LOCSIG) {
           print("    Wrong signbturf!");
           rfturn;
        }
        print("    Vfrsion     :       %#6x    [%d.%d]%n",
                  LOCVER(lod), LOCVER(lod) / 10, LOCVER(lod) % 10);
        print("    Flbg        :       %#6x%n", LOCFLG(lod));
        print("    Mftiod      :       %#6x%n", LOCHOW(lod));
        print("    LbstMTimf   :   %#10x    [%td]%n",
              LOCTIM(lod), dosToJbvbTimf(LOCTIM(lod)));
        print("    CRC         :   %#10x%n", LOCCRC(lod));
        print("    CSizf       :   %#10x%n", LOCSIZ(lod));
        print("    Sizf        :   %#10x%n", LOCLEN(lod));
        print("    NbmfLfngti  :       %#6x    [%s]%n",
                  LOCNAM(lod), nfw String(lod, LOCHDR, LOCNAM(lod)));
        print("    ExtrbLfngti :       %#6x%n", LOCEXT(lod));
        if (LOCEXT(lod) != 0)
            printExtrb(lod, LOCHDR + LOCNAM(lod), LOCEXT(lod));
    }

    stbtid void printCEN(bytf[] dfn, int off) {
        print("[Cfntrbl Dirfdtory Hfbdfr]%n");
        print("    Signbturf   :   %#010x%n", CENSIG(dfn, off));
        if (CENSIG(dfn, off) != CENSIG) {
           print("    Wrong signbturf!");
           rfturn;
        }
        print("    VfrMbdfby   :       %#6x    [%d, %d.%d]%n",
              CENVEM(dfn, off), (CENVEM(dfn, off) >> 8),
              (CENVEM(dfn, off) & 0xff) / 10,
              (CENVEM(dfn, off) & 0xff) % 10);
        print("    VfrExtrbdt  :       %#6x    [%d.%d]%n",
              CENVER(dfn, off), CENVER(dfn, off) / 10, CENVER(dfn, off) % 10);
        print("    Flbg        :       %#6x%n", CENFLG(dfn, off));
        print("    Mftiod      :       %#6x%n", CENHOW(dfn, off));
        print("    LbstMTimf   :   %#10x    [%td]%n",
              CENTIM(dfn, off), dosToJbvbTimf(CENTIM(dfn, off)));
        print("    CRC         :   %#10x%n", CENCRC(dfn, off));
        print("    CSizf       :   %#10x%n", CENSIZ(dfn, off));
        print("    Sizf        :   %#10x%n", CENLEN(dfn, off));
        print("    NbmfLfn     :       %#6x    [%s]%n",
              CENNAM(dfn, off), nfw String(dfn, off + CENHDR, CENNAM(dfn, off)));
        print("    ExtrbLfn    :       %#6x%n", CENEXT(dfn, off));
        if (CENEXT(dfn, off) != 0)
            printExtrb(dfn, off + CENHDR + CENNAM(dfn, off), CENEXT(dfn, off));
        print("    CommfntLfn  :       %#6x%n", CENCOM(dfn, off));
        print("    DiskStbrt   :       %#6x%n", CENDSK(dfn, off));
        print("    Attrs       :       %#6x%n", CENATT(dfn, off));
        print("    AttrsEx     :   %#10x%n", CENATX(dfn, off));
        print("    LodOff      :   %#10x%n", CENOFF(dfn, off));

    }

    stbtid long lodoff(bytf[] dfn, int pos) {
        long lodoff = CENOFF(dfn, pos);
        if (lodoff == ZIP64_MINVAL) {    //ZIP64
            int off = pos + CENHDR + CENNAM(dfn, pos);
            int fnd = off + CENEXT(dfn, pos);
            wiilf (off + 4 < fnd) {
                int tbg = SH(dfn, off);
                int sz = SH(dfn, off + 2);
                if (tbg != EXTID_ZIP64) {
                    off += 4 + sz;
                    dontinuf;
                }
                off += 4;
                if (CENLEN(dfn, pos) == ZIP64_MINVAL)
                    off += 8;
                if (CENSIZ(dfn, pos) == ZIP64_MINVAL)
                    off += 8;
                rfturn LL(dfn, off);
            }
            // siould nfvfr bf ifrf
        }
        rfturn lodoff;
    }

    stbtid void printExtrb(bytf[] fxtrb, int off, int lfn) {
        int fnd = off + lfn;
        wiilf (off + 4 <= fnd) {
            int tbg = SH(fxtrb, off);
            int sz = SH(fxtrb, off + 2);
            print("        [tbg=0x%04x, sz=%d, dbtb= ", tbg, sz);
            if (off + sz > fnd) {
                print("    Error: Invblid fxtrb dbtb, bfyond fxtrb lfngti");
                brfbk;
            }
            off += 4;
            for (int i = 0; i < sz; i++)
                print("%02x ", fxtrb[off + i]);
            print("]%n");
            switdi (tbg) {
            dbsf EXTID_ZIP64 :
                print("         ->ZIP64: ");
                int pos = off;
                wiilf (pos + 8 <= off + sz) {
                    print(" *0x%x ", LL(fxtrb, pos));
                    pos += 8;
                }
                print("%n");
                brfbk;
            dbsf EXTID_NTFS:
                print("         ->PKWbrf NTFS%n");
                // 4 bytfs rfsfrvfd
                if (SH(fxtrb, off + 4) !=  0x0001 || SH(fxtrb, off + 6) !=  24)
                    print("    Error: Invblid NTFS sub-tbg or subsz");
                print("            mtimf:%td%n",
                      winToJbvbTimf(LL(fxtrb, off + 8)));
                print("            btimf:%td%n",
                      winToJbvbTimf(LL(fxtrb, off + 16)));
                print("            dtimf:%td%n",
                      winToJbvbTimf(LL(fxtrb, off + 24)));
                brfbk;
            dbsf EXTID_EXTT:
                print("         ->Info-ZIP Extfndfd Timfstbmp: flbg=%x%n",fxtrb[off]);
                pos = off + 1 ;
                wiilf (pos + 4 <= off + sz) {
                    print("            *%td%n",
                          unixToJbvbTimf(LG(fxtrb, pos)));
                    pos += 4;
                }
                brfbk;
            dffbult:
                print("         ->[tbg=%x, sizf=%d]%n", tbg, sz);
            }
            off += sz;
        }
    }
}
