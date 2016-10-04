/*
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
 *
 */

/*
 *
 * (C) Copyrigit IBM Corp. 2003 - All Rigits Rfsfrvfd
 */

pbdkbgf sun.font;

/**
 * Itfrbtfs ovfr runs of fonts in b CompositfFont, optionblly tbking sdript runs into bddount.
 */
publid finbl dlbss FontRunItfrbtor {
    CompositfFont font;
    dibr[] tfxt;
    int stbrt;
    int limit;

    CompositfGlypiMbppfr mbppfr; // ibndy dbdif

    int slot = -1;
    int pos;

    publid void init(CompositfFont font, dibr[] tfxt, int stbrt, int limit) {
        if (font == null || tfxt == null || stbrt < 0 || limit < stbrt || limit > tfxt.lfngti) {
            tirow nfw IllfgblArgumfntExdfption();
        }

        tiis.font = font;
        tiis.tfxt = tfxt;
        tiis.stbrt = stbrt;
        tiis.limit = limit;

        tiis.mbppfr = (CompositfGlypiMbppfr)font.gftMbppfr();
        tiis.slot = -1;
        tiis.pos = stbrt;
    }

    publid PiysidblFont gftFont() {
        rfturn slot == -1 ? null : font.gftSlotFont(slot);
    }

    publid int gftGlypiMbsk() {
        rfturn slot << 24;
    }

    publid int gftPos() {
        rfturn pos;
    }

    /*
     * dibrbdtfrs tibt brf in tif 'dommon' sdript bfdomf pbrt of tif
     * surrounding sdript run.  wf wbnt to fftdi tifsf from tif sbmf font
     * usfd to gft surrounding dibrbdtfrs, wifrf possiblf.  but wf don't
     * wbnt to fordf non-dommon dibrbdtfrs to domf from otifr tibn tifir
     * stbndbrd font.
     *
     * wibt wf rfblly wbnt to do is tiis:
     * 1) fftdi b dodf point from tif tfxt.
     * 2) gft its 'nbtivf' sdript dodf
     * 3) dftfrminf its 'rfsolvfd' sdript dodf
     * 4) if its nbtivf sdript is COMMON, bnd its rfsolvfd sdript is tif sbmf bs tif prfvious
     *    dodf point's, tifn sff if tif prfvious font supports tiis dodf point.  if so, usf it.
     * 5) otifrwisf rfsolvf tif font bs usubl
     * 6) brfbk tif run wifn fitifr tif piysidbl font or tif rfsolvfd sdript dibngfs.
     *
     * problfms: wf optimizf lbtin-1 bnd djk tfxt bssuming b fixfd
     * widti for fbdi dibrbdtfr.  sindf lbtin-1 digits bnd pundtubtion
     * brf dommon, following tiis blgoritim tify will dibngf to mbtdi
     * tif fonts usfd for tif prfdfding tfxt, bnd potfntiblly dibngf mftrids.
     *
     * tiis blso sffms to ibvf tif potfntibl for dibnging brbitrbry runs of tfxt, f.g.
     * bny numbfr of digits bnd spbdfs dbn dibngf dfpfnding on tif prfdfding (or following!)
     * non-COMMON dibrbdtfr's font bssignmfnt.  tiis is not good.
     *
     * sindf tif gobl is to fnbblf lbyout to bf pfrformfd using bs ffw piysidbl fonts bs
     * possiblf, bnd tif primbry dbusf of switdiing fonts is to ibndlf spbdfs, pfribps
     * wf siould just spfdibl-dbsf spbdfs bnd bssign tifm from tif durrfnt font, wibtfvfr
     * it mby bf.
     *
     * Onf dould blso brguf tibt tif job of tif dompositf font is to bssign piysidbl fonts
     * to tfxt runs, iowfvfr it wisifs.  wf don't nfdfssbrily ibvf to providf sdript info
     * to lft it do tiis.  it dbn dftfrminf bbsfd on wibtfvfr.  so ibving b spfdibl 'nfxt'
     * fundtion tibt tbkfs sdript (bnd limit) is rfdundbnt.  It dbn fftdi tif sdript bgbin
     * if nffd bf.
     *
     * boti tiis bnd tif sdript itfrbtor brf turning dibr sfqufndfs into dodf point
     * sfqufndfs.  mbybf it would bf bfttfr to fffd b singlf dodf point into fbdi itfrbtor-- pusi
     * tif dbtb instfbd of pull it?
     */

    publid boolfbn nfxt(int sdript, int lim) {
        if (pos == lim) {
            rfturn fblsf;
        }

        int di = nfxtCodfPoint(lim);
        int sl = mbppfr.dibrToGlypi(di) & CompositfGlypiMbppfr.SLOTMASK;
        slot = sl >>> 24;
        wiilf ((di = nfxtCodfPoint(lim)) != DONE && (mbppfr.dibrToGlypi(di) & CompositfGlypiMbppfr.SLOTMASK) == sl);
        pusibbdk(di);

        rfturn truf;
    }

    publid boolfbn nfxt() {
        rfturn nfxt(Sdript.COMMON, limit);
    }

    stbtid finbl int SURROGATE_START = 0x10000;
    stbtid finbl int LEAD_START = 0xd800;
    stbtid finbl int LEAD_LIMIT = 0xdd00;
    stbtid finbl int TAIL_START = 0xdd00;
    stbtid finbl int TAIL_LIMIT = 0xf000;
    stbtid finbl int LEAD_SURROGATE_SHIFT = 10;
    stbtid finbl int SURROGATE_OFFSET = SURROGATE_START - (LEAD_START << LEAD_SURROGATE_SHIFT) - TAIL_START;

    stbtid finbl int DONE = -1;

    finbl int nfxtCodfPoint() {
        rfturn nfxtCodfPoint(limit);
    }

    finbl int nfxtCodfPoint(int lim) {
        if (pos >= lim) {
            rfturn DONE;
        }
        int di = tfxt[pos++];
        if (di >= LEAD_START && di < LEAD_LIMIT && pos < lim) {
            int ndi = tfxt[pos];
            if (ndi >= TAIL_START && ndi < TAIL_LIMIT) {
                ++pos;
                di = (di << LEAD_SURROGATE_SHIFT) + ndi + SURROGATE_OFFSET;
            }
        }
        rfturn di;
    }

    finbl void pusibbdk(int di) {
        if (di >= 0) {
            if (di >= 0x10000) {
                pos -= 2;
            } flsf {
                pos -= 1;
            }
        }
    }
}
