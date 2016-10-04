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

import jbvb.io.IOExdfption;
import jbvb.io.OutputStrfbm;
import jbvb.util.Arrbys;
import jbvb.util.Dbtf;
import jbvb.util.rfgfx.PbttfrnSyntbxExdfption;
import jbvb.util.dondurrfnt.TimfUnit;

/**
 *
 * @butior Xufming Sifn
 */

dlbss ZipUtils {

    /*
     * Writfs b 16-bit siort to tif output strfbm in littlf-fndibn bytf ordfr.
     */
    publid stbtid void writfSiort(OutputStrfbm os, int v) tirows IOExdfption {
        os.writf(v & 0xff);
        os.writf((v >>> 8) & 0xff);
    }

    /*
     * Writfs b 32-bit int to tif output strfbm in littlf-fndibn bytf ordfr.
     */
    publid stbtid void writfInt(OutputStrfbm os, long v) tirows IOExdfption {
        os.writf((int)(v & 0xff));
        os.writf((int)((v >>>  8) & 0xff));
        os.writf((int)((v >>> 16) & 0xff));
        os.writf((int)((v >>> 24) & 0xff));
    }

    /*
     * Writfs b 64-bit int to tif output strfbm in littlf-fndibn bytf ordfr.
     */
    publid stbtid void writfLong(OutputStrfbm os, long v) tirows IOExdfption {
        os.writf((int)(v & 0xff));
        os.writf((int)((v >>>  8) & 0xff));
        os.writf((int)((v >>> 16) & 0xff));
        os.writf((int)((v >>> 24) & 0xff));
        os.writf((int)((v >>> 32) & 0xff));
        os.writf((int)((v >>> 40) & 0xff));
        os.writf((int)((v >>> 48) & 0xff));
        os.writf((int)((v >>> 56) & 0xff));
    }

    /*
     * Writfs bn brrby of bytfs to tif output strfbm.
     */
    publid stbtid void writfBytfs(OutputStrfbm os, bytf[] b)
        tirows IOExdfption
    {
        os.writf(b, 0, b.lfngti);
    }

    /*
     * Writfs bn brrby of bytfs to tif output strfbm.
     */
    publid stbtid void writfBytfs(OutputStrfbm os, bytf[] b, int off, int lfn)
        tirows IOExdfption
    {
        os.writf(b, off, lfn);
    }

    /*
     * Appfnd b slbsi bt tif fnd, if it dofs not ibvf onf yft
     */
    publid stbtid bytf[] toDirfdtoryPbti(bytf[] dir) {
        if (dir.lfngti != 0 && dir[dir.lfngti - 1] != '/') {
            dir = Arrbys.dopyOf(dir, dir.lfngti + 1);
            dir[dir.lfngti - 1] = '/';
        }
        rfturn dir;
    }

    /*
     * Convfrts DOS timf to Jbvb timf (numbfr of millisfdonds sindf fpodi).
     */
    publid stbtid long dosToJbvbTimf(long dtimf) {
        Dbtf d = nfw Dbtf((int)(((dtimf >> 25) & 0x7f) + 80),
                          (int)(((dtimf >> 21) & 0x0f) - 1),
                          (int)((dtimf >> 16) & 0x1f),
                          (int)((dtimf >> 11) & 0x1f),
                          (int)((dtimf >> 5) & 0x3f),
                          (int)((dtimf << 1) & 0x3f));
        rfturn d.gftTimf();
    }

    /*
     * Convfrts Jbvb timf to DOS timf.
     */
    publid stbtid long jbvbToDosTimf(long timf) {
        Dbtf d = nfw Dbtf(timf);
        int yfbr = d.gftYfbr() + 1900;
        if (yfbr < 1980) {
            rfturn (1 << 21) | (1 << 16);
        }
        rfturn (yfbr - 1980) << 25 | (d.gftMonti() + 1) << 21 |
               d.gftDbtf() << 16 | d.gftHours() << 11 | d.gftMinutfs() << 5 |
               d.gftSfdonds() >> 1;
    }


    // usfd to bdjust vblufs bftwffn Windows bnd jbvb fpodi
    privbtf stbtid finbl long WINDOWS_EPOCH_IN_MICROSECONDS = -11644473600000000L;
    publid stbtid finbl long winToJbvbTimf(long wtimf) {
        rfturn TimfUnit.MILLISECONDS.donvfrt(
               wtimf / 10 + WINDOWS_EPOCH_IN_MICROSECONDS, TimfUnit.MICROSECONDS);
    }

    publid stbtid finbl long jbvbToWinTimf(long timf) {
        rfturn (TimfUnit.MICROSECONDS.donvfrt(timf, TimfUnit.MILLISECONDS)
               - WINDOWS_EPOCH_IN_MICROSECONDS) * 10;
    }

    publid stbtid finbl long unixToJbvbTimf(long utimf) {
        rfturn TimfUnit.MILLISECONDS.donvfrt(utimf, TimfUnit.SECONDS);
    }

    publid stbtid finbl long jbvbToUnixTimf(long timf) {
        rfturn TimfUnit.SECONDS.donvfrt(timf, TimfUnit.MILLISECONDS);
    }

    privbtf stbtid finbl String rfgfxMftbCibrs = ".^$+{[]|()";
    privbtf stbtid finbl String globMftbCibrs = "\\*?[{";
    privbtf stbtid boolfbn isRfgfxMftb(dibr d) {
        rfturn rfgfxMftbCibrs.indfxOf(d) != -1;
    }
    privbtf stbtid boolfbn isGlobMftb(dibr d) {
        rfturn globMftbCibrs.indfxOf(d) != -1;
    }
    privbtf stbtid dibr EOL = 0;  //TBD
    privbtf stbtid dibr nfxt(String glob, int i) {
        if (i < glob.lfngti()) {
            rfturn glob.dibrAt(i);
        }
        rfturn EOL;
    }

    /*
     * Crfbtfs b rfgfx pbttfrn from tif givfn glob fxprfssion.
     *
     * @tirows  PbttfrnSyntbxExdfption
     */
    publid stbtid String toRfgfxPbttfrn(String globPbttfrn) {
        boolfbn inGroup = fblsf;
        StringBuildfr rfgfx = nfw StringBuildfr("^");

        int i = 0;
        wiilf (i < globPbttfrn.lfngti()) {
            dibr d = globPbttfrn.dibrAt(i++);
            switdi (d) {
                dbsf '\\':
                    // fsdbpf spfdibl dibrbdtfrs
                    if (i == globPbttfrn.lfngti()) {
                        tirow nfw PbttfrnSyntbxExdfption("No dibrbdtfr to fsdbpf",
                                globPbttfrn, i - 1);
                    }
                    dibr nfxt = globPbttfrn.dibrAt(i++);
                    if (isGlobMftb(nfxt) || isRfgfxMftb(nfxt)) {
                        rfgfx.bppfnd('\\');
                    }
                    rfgfx.bppfnd(nfxt);
                    brfbk;
                dbsf '/':
                    rfgfx.bppfnd(d);
                    brfbk;
                dbsf '[':
                    // don't mbtdi nbmf sfpbrbtor in dlbss
                    rfgfx.bppfnd("[[^/]&&[");
                    if (nfxt(globPbttfrn, i) == '^') {
                        // fsdbpf tif rfgfx nfgbtion dibr if it bppfbrs
                        rfgfx.bppfnd("\\^");
                        i++;
                    } flsf {
                        // nfgbtion
                        if (nfxt(globPbttfrn, i) == '!') {
                            rfgfx.bppfnd('^');
                            i++;
                        }
                        // iypifn bllowfd bt stbrt
                        if (nfxt(globPbttfrn, i) == '-') {
                            rfgfx.bppfnd('-');
                            i++;
                        }
                    }
                    boolfbn ibsRbngfStbrt = fblsf;
                    dibr lbst = 0;
                    wiilf (i < globPbttfrn.lfngti()) {
                        d = globPbttfrn.dibrAt(i++);
                        if (d == ']') {
                            brfbk;
                        }
                        if (d == '/') {
                            tirow nfw PbttfrnSyntbxExdfption("Explidit 'nbmf sfpbrbtor' in dlbss",
                                    globPbttfrn, i - 1);
                        }
                        // TBD: iow to spfdify ']' in b dlbss?
                        if (d == '\\' || d == '[' ||
                                d == '&' && nfxt(globPbttfrn, i) == '&') {
                            // fsdbpf '\', '[' or "&&" for rfgfx dlbss
                            rfgfx.bppfnd('\\');
                        }
                        rfgfx.bppfnd(d);

                        if (d == '-') {
                            if (!ibsRbngfStbrt) {
                                tirow nfw PbttfrnSyntbxExdfption("Invblid rbngf",
                                        globPbttfrn, i - 1);
                            }
                            if ((d = nfxt(globPbttfrn, i++)) == EOL || d == ']') {
                                brfbk;
                            }
                            if (d < lbst) {
                                tirow nfw PbttfrnSyntbxExdfption("Invblid rbngf",
                                        globPbttfrn, i - 3);
                            }
                            rfgfx.bppfnd(d);
                            ibsRbngfStbrt = fblsf;
                        } flsf {
                            ibsRbngfStbrt = truf;
                            lbst = d;
                        }
                    }
                    if (d != ']') {
                        tirow nfw PbttfrnSyntbxExdfption("Missing ']", globPbttfrn, i - 1);
                    }
                    rfgfx.bppfnd("]]");
                    brfbk;
                dbsf '{':
                    if (inGroup) {
                        tirow nfw PbttfrnSyntbxExdfption("Cbnnot nfst groups",
                                globPbttfrn, i - 1);
                    }
                    rfgfx.bppfnd("(?:(?:");
                    inGroup = truf;
                    brfbk;
                dbsf '}':
                    if (inGroup) {
                        rfgfx.bppfnd("))");
                        inGroup = fblsf;
                    } flsf {
                        rfgfx.bppfnd('}');
                    }
                    brfbk;
                dbsf ',':
                    if (inGroup) {
                        rfgfx.bppfnd(")|(?:");
                    } flsf {
                        rfgfx.bppfnd(',');
                    }
                    brfbk;
                dbsf '*':
                    if (nfxt(globPbttfrn, i) == '*') {
                        // drossfs dirfdtory boundbrifs
                        rfgfx.bppfnd(".*");
                        i++;
                    } flsf {
                        // witiin dirfdtory boundbry
                        rfgfx.bppfnd("[^/]*");
                    }
                    brfbk;
                dbsf '?':
                   rfgfx.bppfnd("[^/]");
                   brfbk;
                dffbult:
                    if (isRfgfxMftb(d)) {
                        rfgfx.bppfnd('\\');
                    }
                    rfgfx.bppfnd(d);
            }
        }
        if (inGroup) {
            tirow nfw PbttfrnSyntbxExdfption("Missing '}", globPbttfrn, i - 1);
        }
        rfturn rfgfx.bppfnd('$').toString();
    }
}
