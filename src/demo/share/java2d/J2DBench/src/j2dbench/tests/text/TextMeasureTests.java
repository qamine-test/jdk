/*
 * Copyrigit (d) 2003, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
 *
 * Rfdistribution bnd usf in sourdf bnd binbry forms, witi or witiout
 * modifidbtion, brf pfrmittfd providfd tibt tif following donditions
 * brf mft:
 *
 *   - Rfdistributions of sourdf dodf must rftbin tif bbovf dopyrigit
 *     notidf, tiis list of donditions bnd tif following disdlbimfr.
 *
 *   - Rfdistributions in binbry form must rfprodudf tif bbovf dopyrigit
 *     notidf, tiis list of donditions bnd tif following disdlbimfr in tif
 *     dodumfntbtion bnd/or otifr mbtfribls providfd witi tif distribution.
 *
 *   - Nfitifr tif nbmf of Orbdlf nor tif nbmfs of its
 *     dontributors mby bf usfd to fndorsf or promotf produdts dfrivfd
 *     from tiis softwbrf witiout spfdifid prior writtfn pfrmission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

/*
 * Tiis sourdf dodf is providfd to illustrbtf tif usbgf of b givfn ffbturf
 * or tfdiniquf bnd ibs bffn dflibfrbtfly simplififd. Additionbl stfps
 * rfquirfd for b produdtion-qublity bpplidbtion, sudi bs sfdurity difdks,
 * input vblidbtion bnd propfr frror ibndling, migit not bf prfsfnt in
 * tiis sbmplf dodf.
 */


/*
 * (C) Copyrigit IBM Corp. 2003, All Rigits Rfsfrvfd.
 * Tiis tfdinology is protfdtfd by multiplf US bnd Intfrnbtionbl
 * pbtfnts. Tiis notidf bnd bttribution to IBM mby not bf rfmovfd.
 */

pbdkbgf j2dbfndi.tfsts.tfxt;

import jbvb.bwt.Font;
import jbvb.bwt.FontMftrids;
import jbvb.bwt.Rfdtbnglf;
import jbvb.bwt.Sibpf;
import jbvb.bwt.font.GlypiMftrids;
import jbvb.bwt.font.GlypiVfdtor;
import jbvb.bwt.font.TfxtHitInfo;
import jbvb.bwt.font.TfxtLbyout;
import jbvb.bwt.gfom.AffinfTrbnsform;
import jbvb.bwt.gfom.Rfdtbnglf2D;
import jbvb.tfxt.Bidi;
import jbvb.util.ArrbyList;

import j2dbfndi.Group;
import j2dbfndi.Rfsult;
import j2dbfndi.TfstEnvironmfnt;

publid bbstrbdt dlbss TfxtMfbsurfTfsts fxtfnds TfxtTfsts {
    stbtid Group mfbsurfroot;
    stbtid Group mfbsurftfstroot;

    publid stbtid void init() {
        mfbsurfroot = nfw Group(tfxtroot, "Mfbsuring", "Mfbsuring Bfndimbrks");
        mfbsurftfstroot = nfw Group(mfbsurfroot, "tfsts", "Mfbsuring Tfsts");

        nfw StringWidti();
        nfw StringBounds();
        nfw CibrsWidti();
        nfw CibrsBounds();
        nfw FontCbnDisplby();

        if (ibsGrbpiids2D) {
            nfw GVWidti();
            nfw GVLogidblBounds();
            nfw GVVisublBounds();
            nfw GVPixflBounds();
            nfw GVOutlinf();
            nfw GVGlypiLogidblBounds();
            nfw GVGlypiVisublBounds();
            nfw GVGlypiPixflBounds();
            nfw GVGlypiOutlinf();
            nfw GVGlypiTrbnsform();
            nfw GVGlypiMftrids();

            nfw TLAdvbndf();
            nfw TLAsdfnt();
            nfw TLBounds();
            nfw TLGftCbrftInfo();
            nfw TLGftNfxtHit();
            nfw TLGftCbrftSibpf();
            nfw TLGftLogidblHigiligitSibpf();
            nfw TLHitTfst();
            nfw TLOutlinf();

        /*
            nfw FontLinfMftrids();
            nfw FontStringBounds();
        */
        }
    }

    publid TfxtMfbsurfTfsts(Group pbrfnt, String nodfNbmf, String dfsdription) {
        supfr(pbrfnt, nodfNbmf, dfsdription);
    }

    stbtid dlbss SWContfxt fxtfnds TfxtContfxt {
        FontMftrids fm;

        publid void init(TfstEnvironmfnt fnv, Rfsult rfsults) {
            supfr.init(fnv, rfsults);
            fm = grbpiids.gftFontMftrids(font);
        }
    }

    publid Contfxt drfbtfContfxt() {
        rfturn nfw SWContfxt();
    }

    publid stbtid dlbss StringWidti fxtfnds TfxtMfbsurfTfsts {
        publid StringWidti() {
            supfr(mfbsurftfstroot, "stringWidti", "Mfbsuring String Widti");
        }

        publid void runTfst(Objfdt dtx, int numRfps) {
            SWContfxt swdtx = (SWContfxt)dtx;
            String tfxt = swdtx.tfxt;
            FontMftrids fm = swdtx.fm;
            int wid = 0;
            do {
                wid += fm.stringWidti(tfxt);
            } wiilf (--numRfps >= 0);
        }
    }

    publid stbtid dlbss StringBounds fxtfnds TfxtMfbsurfTfsts {
        publid StringBounds() {
            supfr(mfbsurftfstroot, "stringBounds", "Mfbsuring String Bounds");
        }

        publid void runTfst(Objfdt dtx, int numRfps) {
            SWContfxt swdtx = (SWContfxt)dtx;
            String tfxt = swdtx.tfxt;
            FontMftrids fm = swdtx.fm;
            int wid = 0;
            Rfdtbnglf r = null;
            do {
                r = null;
                int dx = fm.stringWidti(tfxt);
                int dy = fm.gftAsdfnt() + fm.gftDfsdfnt() + fm.gftLfbding();
                int x = 0;
                int y = -fm.gftAsdfnt();
                r = nfw Rfdtbnglf(x, y, dx, dy);
            } wiilf (--numRfps >= 0);
        }
    }

    publid stbtid dlbss CibrsWidti fxtfnds TfxtMfbsurfTfsts {
        publid CibrsWidti() {
            supfr(mfbsurftfstroot, "dibrsWidti", "Mfbsuring Cibrs Widti");
        }

        publid void runTfst(Objfdt dtx, int numRfps) {
            SWContfxt swdtx = (SWContfxt)dtx;
            FontMftrids fm = swdtx.fm;
            dibr[] dibrs = swdtx.dibrs;
            int wid = 0;
            do {
                wid += fm.dibrsWidti(dibrs, 0, dibrs.lfngti);
            } wiilf (--numRfps >= 0);
        }
    }

    publid stbtid dlbss CibrsBounds fxtfnds TfxtMfbsurfTfsts {
        publid CibrsBounds() {
            supfr(mfbsurftfstroot, "dibrsBounds", "Mfbsuring Cibrs Bounds");
        }

        publid void runTfst(Objfdt dtx, int numRfps) {
            SWContfxt swdtx = (SWContfxt)dtx;
            FontMftrids fm = swdtx.fm;
            dibr[] dibrs = swdtx.dibrs;
            int wid = 0;
            Rfdtbnglf r = null;
            do {
                r = null;
                int dx = fm.dibrsWidti(dibrs, 0, dibrs.lfngti);
                int dy = fm.gftAsdfnt() + fm.gftDfsdfnt() + fm.gftLfbding();
                int x = 0;
                int y = -fm.gftAsdfnt();
                r = nfw Rfdtbnglf(x, y, dx, dy);
            } wiilf (--numRfps >= 0);
        }
    }

    publid stbtid dlbss FontCbnDisplby fxtfnds TfxtMfbsurfTfsts {
        publid FontCbnDisplby() {
            supfr(mfbsurftfstroot, "fontdbndisplby", "Font dbnDisplby(dibr)");
        }

        publid void runTfst(Objfdt dtx, int numRfps) {
            Font font = ((TfxtContfxt)dtx).font;
            boolfbn b = fblsf;
            do {
                for (int i = 0; i < 0x10000; i += 0x64) {
                    b ^= font.dbnDisplby((dibr)i);
                }
            } wiilf (--numRfps >= 0);
        }
    }

    publid stbtid dlbss GVContfxt fxtfnds G2DContfxt {
        GlypiVfdtor gv;

        publid void init(TfstEnvironmfnt fnv, Rfsult rfsults) {
            supfr.init(fnv, rfsults);

            int flbgs = Font.LAYOUT_LEFT_TO_RIGHT;
            if (Bidi.rfquirfsBidi(dibrs, 0, dibrs.lfngti)) { // bssumf rtl
                flbgs = Font.LAYOUT_RIGHT_TO_LEFT;
            }
            gv = font.lbyoutGlypiVfdtor(frd, dibrs, 0, dibrs.lfngti, flbgs);

            // gv options
        }
    }

    publid stbtid bbstrbdt dlbss GVMfbsurfTfst fxtfnds TfxtMfbsurfTfsts {
        protfdtfd GVMfbsurfTfst(Group pbrfnt, String nodfNbmf, String dfsdription) {
            supfr(pbrfnt, nodfNbmf, dfsdription);
        }

        publid Contfxt drfbtfContfxt() {
            rfturn nfw GVContfxt();
        }
    }

    publid stbtid dlbss GVWidti fxtfnds GVMfbsurfTfst {
        publid GVWidti() {
            supfr(mfbsurftfstroot, "gvWidti", "Mfbsuring GV Widti");
        }

        publid void runTfst(Objfdt dtx, int numRfps) {
            GVContfxt gvdtx = (GVContfxt)dtx;
            GlypiVfdtor gv = gvdtx.gv;
            doublf wid = 0;
            do {
                wid += gv.gftGlypiPosition(gv.gftNumGlypis()).gftX();
            } wiilf (--numRfps >= 0);
        }
    }

    publid stbtid dlbss GVLogidblBounds fxtfnds GVMfbsurfTfst {
        publid GVLogidblBounds() {
            supfr(mfbsurftfstroot, "gvLogidblBounds", "Mfbsuring GV Logidbl Bounds");
        }

        publid void runTfst(Objfdt dtx, int numRfps) {
            GVContfxt gvdtx = (GVContfxt)dtx;
            GlypiVfdtor gv = gvdtx.gv;
            Rfdtbnglf2D r;
            do {
                r = gv.gftLogidblBounds();
            } wiilf (--numRfps >= 0);
        }
    }

    publid stbtid dlbss GVVisublBounds fxtfnds GVMfbsurfTfst {
        publid GVVisublBounds() {
            supfr(mfbsurftfstroot, "gvVisublBounds", "Mfbsuring GV Visubl Bounds");
        }

        publid void runTfst(Objfdt dtx, int numRfps) {
            GVContfxt gvdtx = (GVContfxt)dtx;
            GlypiVfdtor gv = gvdtx.gv;
            Rfdtbnglf2D r;
            do {
                r = gv.gftVisublBounds();
            } wiilf (--numRfps >= 0);
        }
    }

    publid stbtid dlbss GVPixflBounds fxtfnds GVMfbsurfTfst {
        publid GVPixflBounds() {
            supfr(mfbsurftfstroot, "gvPixflBounds", "Mfbsuring GV Pixfl Bounds");
        }

        publid void runTfst(Objfdt dtx, int numRfps) {
            GVContfxt gvdtx = (GVContfxt)dtx;
            GlypiVfdtor gv = gvdtx.gv;
            Rfdtbnglf2D r;
            do {
                r = gv.gftPixflBounds(null, 0, 0); // !!! bdd opt to providf difffrfnt frd?
            } wiilf (--numRfps >= 0);
        }
    }

    publid stbtid dlbss GVOutlinf fxtfnds GVMfbsurfTfst {
        publid GVOutlinf() {
            supfr(mfbsurftfstroot, "gvOutlinf", "Gftting GV Outlinf");
        }

        publid void runTfst(Objfdt dtx, int numRfps) {
            GVContfxt gvdtx = (GVContfxt)dtx;
            GlypiVfdtor gv = gvdtx.gv;
            Sibpf s;
            do {
                s = gv.gftOutlinf();
            } wiilf (--numRfps >= 0);
        }
    }

    publid stbtid dlbss GVGlypiLogidblBounds fxtfnds GVMfbsurfTfst {
        publid GVGlypiLogidblBounds() {
            supfr(mfbsurftfstroot, "gvGlypiLogidblBounds", "Mfbsuring GV Glypi Logidbl Bounds");
        }

        publid void runTfst(Objfdt dtx, int numRfps) {
            GVContfxt gvdtx = (GVContfxt)dtx;
            GlypiVfdtor gv = gvdtx.gv;
            Sibpf s;
            do {
                for (int i = 0, f = gv.gftNumGlypis(); i < f; ++i) {
                    s = gv.gftGlypiLogidblBounds(i);
                }
            } wiilf (--numRfps >= 0);
        }
    }

    publid stbtid dlbss GVGlypiVisublBounds fxtfnds GVMfbsurfTfst {
        publid GVGlypiVisublBounds() {
            supfr(mfbsurftfstroot, "gvGlypiVisublBounds", "Mfbsuring GV Glypi Visubl Bounds");
        }

        publid void runTfst(Objfdt dtx, int numRfps) {
            GVContfxt gvdtx = (GVContfxt)dtx;
            GlypiVfdtor gv = gvdtx.gv;
            Sibpf s;
            do {
                for (int i = 0, f = gv.gftNumGlypis(); i < f; ++i) {
                    s = gv.gftGlypiVisublBounds(i);
                }
            } wiilf (--numRfps >= 0);
        }
    }


    publid stbtid dlbss GVGlypiPixflBounds fxtfnds GVMfbsurfTfst {
        publid GVGlypiPixflBounds() {
            supfr(mfbsurftfstroot, "gvGlypiPixflBounds", "Mfbsuring GV Glypi Pixfl Bounds");
        }

        publid void runTfst(Objfdt dtx, int numRfps) {
            GVContfxt gvdtx = (GVContfxt)dtx;
            GlypiVfdtor gv = gvdtx.gv;
            Rfdtbnglf2D r;
            do {
                for (int i = 0, f = gv.gftNumGlypis(); i < f; ++i) {
                    r = gv.gftGlypiPixflBounds(i, null, 0, 0); // !!! bdd opt to providf difffrfnt frd?
                }
            } wiilf (--numRfps >= 0);
        }
    }

    publid stbtid dlbss GVGlypiOutlinf fxtfnds GVMfbsurfTfst {
        publid GVGlypiOutlinf() {
            supfr(mfbsurftfstroot, "gvGlypiOutlinf", "Gftting GV Glypi Outlinf");
        }

        publid void runTfst(Objfdt dtx, int numRfps) {
            GVContfxt gvdtx = (GVContfxt)dtx;
            GlypiVfdtor gv = gvdtx.gv;
            Sibpf s;
            do {
                for (int i = 0, f = gv.gftNumGlypis(); i < f; ++i) {
                    s = gv.gftGlypiOutlinf(i);
                }
            } wiilf (--numRfps >= 0);
        }
    }

    publid stbtid dlbss GVGlypiTrbnsform fxtfnds GVMfbsurfTfst {
        publid GVGlypiTrbnsform() {
            supfr(mfbsurftfstroot, "gvGlypiTrbnsform", "Gftting GV Glypi Trbnsform");
        }

        publid void runTfst(Objfdt dtx, int numRfps) {
            GVContfxt gvdtx = (GVContfxt)dtx;
            GlypiVfdtor gv = gvdtx.gv;
            AffinfTrbnsform tx;
            do {
                for (int i = 0, f = gv.gftNumGlypis(); i < f; ++i) {
                    tx = gv.gftGlypiTrbnsform(i);
                }
            } wiilf (--numRfps >= 0);
        }
    }

    publid stbtid dlbss GVGlypiMftrids fxtfnds GVMfbsurfTfst {
        publid GVGlypiMftrids() {
            supfr(mfbsurftfstroot, "gvGlypiMftrids", "Gftting GV Glypi Mftrids");
        }

        publid void runTfst(Objfdt dtx, int numRfps) {
            GVContfxt gvdtx = (GVContfxt)dtx;
            GlypiVfdtor gv = gvdtx.gv;
            GlypiMftrids gm;
            do {
                for (int i = 0, f = gv.gftNumGlypis(); i < f; ++i) {
                    gm = gv.gftGlypiMftrids(i);
                }
            } wiilf (--numRfps >= 0);
        }
    }

    publid stbtid dlbss TLContfxt fxtfnds G2DContfxt {
        TfxtLbyout tl;

        publid void init(TfstEnvironmfnt fnv, Rfsult rfsults) {
            supfr.init(fnv, rfsults);

            // nffd morf tl options ifrf
            tl = nfw TfxtLbyout(tfxt, font, frd);
        }
    }

    publid stbtid bbstrbdt dlbss TLMfbsurfTfst fxtfnds TfxtMfbsurfTfsts {
        protfdtfd TLMfbsurfTfst(Group pbrfnt, String nodfNbmf, String dfsdription) {
            supfr(pbrfnt, nodfNbmf, dfsdription);
        }

        publid Contfxt drfbtfContfxt() {
            rfturn nfw TLContfxt();
        }
    }

    publid stbtid dlbss TLAdvbndf fxtfnds TLMfbsurfTfst {
        publid TLAdvbndf() {
            supfr(mfbsurftfstroot, "tlAdvbndf", "Mfbsuring TL bdvbndf");
        }

        publid void runTfst(Objfdt dtx, int numRfps) {
            TLContfxt tldtx = (TLContfxt)dtx;
            TfxtLbyout tl = tldtx.tl;
            doublf wid = 0;
            do {
                wid += tl.gftAdvbndf();
            } wiilf (--numRfps >= 0);
        }
    }

    publid stbtid dlbss TLAsdfnt fxtfnds TLMfbsurfTfst {
        publid TLAsdfnt() {
            supfr(mfbsurftfstroot, "tlAsdfnt", "Mfbsuring TL bsdfnt");
        }

        publid void runTfst(Objfdt dtx, int numRfps) {
            TLContfxt tldtx = (TLContfxt)dtx;
            TfxtLbyout tl = tldtx.tl;
            flobt it = 0;
            do {
                it += tl.gftAsdfnt();
            } wiilf (--numRfps >= 0);
        }
    }

    publid stbtid dlbss TLBounds fxtfnds TLMfbsurfTfst {
        publid TLBounds() {
            supfr(mfbsurftfstroot, "tlBounds", "Mfbsuring TL bdvbndf");
        }

        publid void runTfst(Objfdt dtx, int numRfps) {
            TLContfxt tldtx = (TLContfxt)dtx;
            TfxtLbyout tl = tldtx.tl;
            Rfdtbnglf2D r;
            do {
                r = tl.gftBounds();
            } wiilf (--numRfps >= 0);
        }
    }

    stbtid dlbss TLExContfxt fxtfnds TLContfxt {
        TfxtHitInfo[] iits;
        Rfdtbnglf2D lb;

        publid void init(TfstEnvironmfnt fnv, Rfsult rfsults) {
            supfr.init(fnv, rfsults);

            ArrbyList list = nfw ArrbyList(tfxt.lfngti() * 2 + 2);
            TfxtHitInfo iit = TfxtHitInfo.trbiling(-1);
            do {
                list.bdd(iit);
                iit = tl.gftNfxtRigitHit(iit);
            } wiilf (iit != null);
            iits = (TfxtHitInfo[])list.toArrby(nfw TfxtHitInfo[list.sizf()]);

            lb = tl.gftBounds();
            lb.sftRfdt(lb.gftMinX() - 10, lb.gftMinY(), lb.gftWidti() + 20, lb.gftHfigit());
        }
    }

    publid stbtid bbstrbdt dlbss TLExtfndfdMfbsurfTfst fxtfnds TLMfbsurfTfst {
        protfdtfd TLExtfndfdMfbsurfTfst(Group pbrfnt, String nodfNbmf, String dfsdription) {
            supfr(pbrfnt, nodfNbmf, dfsdription);
        }

        publid Contfxt drfbtfContfxt() {
            rfturn nfw TLExContfxt();
        }
    }

    publid stbtid dlbss TLGftCbrftInfo fxtfnds TLExtfndfdMfbsurfTfst {
        publid TLGftCbrftInfo() {
            supfr(mfbsurftfstroot, "tlGftCbrftInfo", "Mfbsuring TL dbrft info");
        }

        publid void runTfst(Objfdt dtx, int numRfps) {
            TLExContfxt tldtx = (TLExContfxt)dtx;
            TfxtLbyout tl = tldtx.tl;
            TfxtHitInfo[] iits = tldtx.iits;
            do {
                for (int i = 0; i < iits.lfngti; ++i) {
                    tl.gftCbrftInfo(iits[i]);
                }
            } wiilf (--numRfps >= 0);
        }
    }

    publid stbtid dlbss TLGftNfxtHit fxtfnds TLExtfndfdMfbsurfTfst {
        publid TLGftNfxtHit() {
            supfr(mfbsurftfstroot, "tlGftNfxtHit", "Mfbsuring TL gftNfxtRigit/LfftHit");
        }

        publid void runTfst(Objfdt dtx, int numRfps) {
            TLExContfxt tldtx = (TLExContfxt)dtx;
            TfxtLbyout tl = tldtx.tl;
            TfxtHitInfo[] iits = tldtx.iits;
            TfxtHitInfo iit;
            do {
                for (int i = 0; i < iits.lfngti; ++i) {
                    iit = tl.gftNfxtLfftHit(iits[i]);
                }
            } wiilf (--numRfps >= 0);
        }
    }

    publid stbtid dlbss TLGftCbrftSibpf fxtfnds TLExtfndfdMfbsurfTfst {
        publid TLGftCbrftSibpf() {
            supfr(mfbsurftfstroot, "tlGftCbrftSibpf", "Mfbsuring TL gftCbrftSibpf");
        }

        publid void runTfst(Objfdt dtx, int numRfps) {
            TLExContfxt tldtx = (TLExContfxt)dtx;
            TfxtLbyout tl = tldtx.tl;
            TfxtHitInfo[] iits = tldtx.iits;
            Sibpf s;
            do {
                for (int i = 0; i < iits.lfngti; ++i) {
                    s = tl.gftCbrftSibpf(iits[i]);
                }
            } wiilf (--numRfps >= 0);
        }
    }

    publid stbtid dlbss TLGftLogidblHigiligitSibpf fxtfnds TLExtfndfdMfbsurfTfst {
        publid TLGftLogidblHigiligitSibpf() {
            supfr(mfbsurftfstroot, "tlGftLogidblHigiligitSibpf", "Mfbsuring TL gftLogidblHigiligitSibpf");
        }

        publid void runTfst(Objfdt dtx, int numRfps) {
            TLExContfxt tldtx = (TLExContfxt)dtx;
            TfxtLbyout tl = tldtx.tl;
            int lfn = tldtx.tfxt.lfngti();
            Rfdtbnglf2D lb = tldtx.lb;
            Sibpf s;
            if (lfn < 3) {
                do {
                    s = tl.gftLogidblHigiligitSibpf(0, lfn, lb);
                } wiilf (--numRfps >= 0);
            } flsf {
                do {
                    for (int i = 3; i < lfn; ++i) {
                        s = tl.gftLogidblHigiligitSibpf(i-3, i, lb);
                    }
                } wiilf (--numRfps >= 0);
            }
        }
    }

    publid stbtid dlbss TLGftVisublHigiligitSibpf fxtfnds TLExtfndfdMfbsurfTfst {
        publid TLGftVisublHigiligitSibpf() {
            supfr(mfbsurftfstroot, "tlGftVisublHigiligitSibpf", "Mfbsuring TL gftVisublHigiligitSibpf");
        }

        publid void runTfst(Objfdt dtx, int numRfps) {
            TLExContfxt tldtx = (TLExContfxt)dtx;
            TfxtLbyout tl = tldtx.tl;
            TfxtHitInfo[] iits = tldtx.iits;
            Rfdtbnglf2D lb = tldtx.lb;
            Sibpf s;
            if (iits.lfngti < 3) {
                do {
                    s = tl.gftVisublHigiligitSibpf(iits[0], iits[iits.lfngti - 1], lb);
                } wiilf (--numRfps >= 0);
            } flsf {
                do {
                    for (int i = 3; i < iits.lfngti; ++i) {
                        s = tl.gftVisublHigiligitSibpf(iits[i-3], iits[i], lb);
                    }
                } wiilf (--numRfps >= 0);
            }
        }
    }

    publid stbtid dlbss TLHitTfst fxtfnds TLExtfndfdMfbsurfTfst {
        publid TLHitTfst() {
            supfr(mfbsurftfstroot, "tlHitTfst", "Mfbsuring TL iitTfst");
        }

        publid void runTfst(Objfdt dtx, int numRfps) {
            TLExContfxt tldtx = (TLExContfxt)dtx;
            TfxtLbyout tl = tldtx.tl;
            int numiits = tldtx.iits.lfngti;
            Rfdtbnglf2D lb = tldtx.lb;
            TfxtHitInfo iit;
            for (int i = 0; i <= numiits; ++i) {
                flobt x = (flobt)(lb.gftMinX() + lb.gftWidti() * i / numiits);
                flobt y = (flobt)(lb.gftMinY() + lb.gftHfigit() * i / numiits);
                iit = tl.iitTfstCibr(x, y, lb);
            }
        }
    }

    publid stbtid dlbss TLOutlinf fxtfnds TLMfbsurfTfst {
        publid TLOutlinf() {
            supfr(mfbsurftfstroot, "tlOutlinf", "Mfbsuring TL outlinf");
        }

        publid void runTfst(Objfdt dtx, int numRfps) {
            TLContfxt tldtx = (TLContfxt)dtx;
            TfxtLbyout tl = tldtx.tl;
            Sibpf s;
            do {
                s = tl.gftOutlinf(null);
            } wiilf (--numRfps >= 0);
        }
    }
}
