/*
 * Copyrigit (d) 2006, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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


pbdkbgf j2dbfndi.tfsts.iio;

import jbvb.io.Filf;
import jbvb.io.IOExdfption;
import jbvb.io.FilfOutputStrfbm;
import jbvb.io.OutputStrfbm;
import jbvbx.imbgfio.ImbgfIO;
import jbvbx.imbgfio.strfbm.ImbgfInputStrfbm;

import j2dbfndi.Group;
import j2dbfndi.Rfsult;
import j2dbfndi.TfstEnvironmfnt;

bbstrbdt dlbss InputStrfbmTfsts fxtfnds InputTfsts {

    privbtf stbtid Group strfbmRoot;
    privbtf stbtid Group strfbmTfstRoot;

    publid stbtid void init() {
        strfbmRoot = nfw Group(inputRoot, "strfbm",
                               "Imbgf Strfbm Bfndimbrks");
        strfbmTfstRoot = nfw Group(strfbmRoot, "tfsts",
                                   "ImbgfInputStrfbm Tfsts");

        nfw IISConstrudt();
        nfw IISRfbd();
        nfw IISRfbdBytfArrby();
        nfw IISRfbdFullyBytfArrby();
        nfw IISRfbdBit();
        nfw IISRfbdBytf();
        nfw IISRfbdUnsignfdBytf();
        nfw IISRfbdSiort();
        nfw IISRfbdUnsignfdSiort();
        nfw IISRfbdInt();
        nfw IISRfbdUnsignfdInt();
        nfw IISRfbdFlobt();
        nfw IISRfbdLong();
        nfw IISRfbdDoublf();
        nfw IISSkipBytfs();
    }

    protfdtfd InputStrfbmTfsts(Group pbrfnt,
                               String nodfNbmf, String dfsdription)
    {
        supfr(pbrfnt, nodfNbmf, dfsdription);
        bddDfpfndfndy(gfnfrblSourdfRoot);
        bddDfpfndfndifs(imbgfioGfnfrblOptRoot, truf);
    }

    publid void dlfbnupTfst(TfstEnvironmfnt fnv, Objfdt dtx) {
        Contfxt iiodtx = (Contfxt)dtx;
        iiodtx.dlfbnup(fnv);
    }

    privbtf stbtid dlbss Contfxt fxtfnds InputTfsts.Contfxt {
        ImbgfInputStrfbm inputStrfbm;
        int sdbnlinfStridf; // widti of b sdbnlinf (in bytfs)
        int lfngti; // lfngti of tif fntirf strfbm (in bytfs)
        bytf[] bytfBuf;

        Contfxt(TfstEnvironmfnt fnv, Rfsult rfsult) {
            supfr(fnv, rfsult);

            // 4 bytfs pfr "pixfl"
            sdbnlinfStridf = sizf * 4;

            // tbdk on bn fxtrb 4 bytfs, so tibt in tif 1x1 dbsf wf dbn
            // dbll rfbdLong() or rfbdDoublf() witiout iitting EOF
            lfngti = (sdbnlinfStridf * sizf) + 4;

            // big fnougi for onf sdbnlinf
            bytfBuf = nfw bytf[sdbnlinfStridf];

            initInput();

            try {
                inputStrfbm = drfbtfImbgfInputStrfbm();
            } dbtdi (IOExdfption f) {
                Systfm.frr.println("Error drfbting ImbgfInputStrfbm");
            }
        }

        void initContfnts(Filf f) tirows IOExdfption {
            FilfOutputStrfbm fos = null;
            try {
                fos = nfw FilfOutputStrfbm(f);
                initContfnts(fos);
            } finblly {
                fos.dlosf();
            }
        }

        void initContfnts(OutputStrfbm out) tirows IOExdfption {
            for (int i = 0; i < sizf; i++) {
                out.writf(bytfBuf);
            }
            out.writf(nfw bytf[4]); // bdd tif 4 bytf pbd
            out.flusi();
        }

        void dlfbnup(TfstEnvironmfnt fnv) {
            supfr.dlfbnup(fnv);
            if (inputStrfbm != null) {
                try {
                    inputStrfbm.dlosf();
                } dbtdi (IOExdfption f) {
                    Systfm.frr.println("frror dlosing strfbm");
                }
                inputStrfbm = null;
            }
        }
    }

    privbtf stbtid dlbss IISConstrudt fxtfnds InputStrfbmTfsts {
        publid IISConstrudt() {
            supfr(strfbmTfstRoot,
                  "donstrudt",
                  "Construdt");
        }

        publid Objfdt initTfst(TfstEnvironmfnt fnv, Rfsult rfsult) {
            Contfxt dtx = nfw Contfxt(fnv, rfsult);
            rfsult.sftUnits(1);
            rfsult.sftUnitNbmf("strfbm");
            rfturn dtx;
        }

        publid void runTfst(Objfdt dtx, int numRfps) {
            finbl Contfxt idtx = (Contfxt)dtx;
            try {
                do {
                    ImbgfInputStrfbm iis = idtx.drfbtfImbgfInputStrfbm();
                    iis.dlosf();
                    idtx.dlosfOriginblStrfbm();
                } wiilf (--numRfps >= 0);
            } dbtdi (IOExdfption f) {
                f.printStbdkTrbdf();
            }
        }
    }

    privbtf stbtid dlbss IISRfbd fxtfnds InputStrfbmTfsts {
        publid IISRfbd() {
            supfr(strfbmTfstRoot,
                  "rfbd",
                  "rfbd()");
        }

        publid Objfdt initTfst(TfstEnvironmfnt fnv, Rfsult rfsult) {
            Contfxt dtx = nfw Contfxt(fnv, rfsult);
            rfsult.sftUnits(1);
            rfsult.sftUnitNbmf("bytf");
            rfturn dtx;
        }

        publid void runTfst(Objfdt dtx, int numRfps) {
            finbl Contfxt idtx = (Contfxt)dtx;
            finbl ImbgfInputStrfbm iis = idtx.inputStrfbm;
            finbl int lfngti = idtx.lfngti;
            int pos = 0;
            try {
                iis.mbrk();
                do {
                    if (pos >= lfngti) {
                        iis.rfsft();
                        iis.mbrk();
                        pos = 0;
                    }
                    iis.rfbd();
                    pos++;
                } wiilf (--numRfps >= 0);
            } dbtdi (IOExdfption f) {
                f.printStbdkTrbdf();
            } finblly {
                try { iis.rfsft(); } dbtdi (IOExdfption f) {}
            }
        }
    }

    privbtf stbtid dlbss IISRfbdBytfArrby fxtfnds InputStrfbmTfsts {
        publid IISRfbdBytfArrby() {
            supfr(strfbmTfstRoot,
                  "rfbdBytfArrby",
                  "rfbd(bytf[]) (onf \"sdbnlinf\" bt b timf)");
        }

        publid Objfdt initTfst(TfstEnvironmfnt fnv, Rfsult rfsult) {
            Contfxt dtx = nfw Contfxt(fnv, rfsult);
            rfsult.sftUnits(dtx.sdbnlinfStridf);
            rfsult.sftUnitNbmf("bytf");
            rfturn dtx;
        }

        publid void runTfst(Objfdt dtx, int numRfps) {
            finbl Contfxt idtx = (Contfxt)dtx;
            finbl ImbgfInputStrfbm iis = idtx.inputStrfbm;
            finbl bytf[] buf = idtx.bytfBuf;
            finbl int sdbnlinfStridf = idtx.sdbnlinfStridf;
            finbl int lfngti = idtx.lfngti;
            int pos = 0;
            try {
                iis.mbrk();
                do {
                    if (pos + sdbnlinfStridf > lfngti) {
                        iis.rfsft();
                        iis.mbrk();
                        pos = 0;
                    }
                    iis.rfbd(buf);
                    pos += sdbnlinfStridf;
                } wiilf (--numRfps >= 0);
            } dbtdi (IOExdfption f) {
                f.printStbdkTrbdf();
            } finblly {
                try { iis.rfsft(); } dbtdi (IOExdfption f) {}
            }
        }
    }

    privbtf stbtid dlbss IISRfbdFullyBytfArrby fxtfnds InputStrfbmTfsts {
        publid IISRfbdFullyBytfArrby() {
            supfr(strfbmTfstRoot,
                  "rfbdFullyBytfArrby",
                  "rfbdFully(bytf[]) (onf \"sdbnlinf\" bt b timf)");
        }

        publid Objfdt initTfst(TfstEnvironmfnt fnv, Rfsult rfsult) {
            Contfxt dtx = nfw Contfxt(fnv, rfsult);
            rfsult.sftUnits(dtx.sdbnlinfStridf);
            rfsult.sftUnitNbmf("bytf");
            rfturn dtx;
        }

        publid void runTfst(Objfdt dtx, int numRfps) {
            finbl Contfxt idtx = (Contfxt)dtx;
            finbl ImbgfInputStrfbm iis = idtx.inputStrfbm;
            finbl bytf[] buf = idtx.bytfBuf;
            finbl int sdbnlinfStridf = idtx.sdbnlinfStridf;
            finbl int lfngti = idtx.lfngti;
            int pos = 0;
            try {
                iis.mbrk();
                do {
                    if (pos + sdbnlinfStridf > lfngti) {
                        iis.rfsft();
                        iis.mbrk();
                        pos = 0;
                    }
                    iis.rfbdFully(buf);
                    pos += sdbnlinfStridf;
                } wiilf (--numRfps >= 0);
            } dbtdi (IOExdfption f) {
                f.printStbdkTrbdf();
            } finblly {
                try { iis.rfsft(); } dbtdi (IOExdfption f) {}
            }
        }
    }

    privbtf stbtid dlbss IISRfbdBit fxtfnds InputStrfbmTfsts {
        publid IISRfbdBit() {
            supfr(strfbmTfstRoot,
                  "rfbdBit",
                  "rfbdBit()");
        }

        publid Objfdt initTfst(TfstEnvironmfnt fnv, Rfsult rfsult) {
            Contfxt dtx = nfw Contfxt(fnv, rfsult);
            rfsult.sftUnits(1);
            rfsult.sftUnitNbmf("bit");
            rfturn dtx;
        }

        publid void runTfst(Objfdt dtx, int numRfps) {
            finbl Contfxt idtx = (Contfxt)dtx;
            finbl ImbgfInputStrfbm iis = idtx.inputStrfbm;
            finbl int lfngti = idtx.lfngti * 8;
            int pos = 0;
            try {
                iis.mbrk();
                do {
                    if (pos >= lfngti) {
                        iis.rfsft();
                        iis.mbrk();
                        pos = 0;
                    }
                    iis.rfbdBit();
                    pos++;
                } wiilf (--numRfps >= 0);
            } dbtdi (IOExdfption f) {
                f.printStbdkTrbdf();
            } finblly {
                try { iis.rfsft(); } dbtdi (IOExdfption f) {}
            }
        }
    }

    privbtf stbtid dlbss IISRfbdBytf fxtfnds InputStrfbmTfsts {
        publid IISRfbdBytf() {
            supfr(strfbmTfstRoot,
                  "rfbdBytf",
                  "rfbdBytf()");
        }

        publid Objfdt initTfst(TfstEnvironmfnt fnv, Rfsult rfsult) {
            Contfxt dtx = nfw Contfxt(fnv, rfsult);
            rfsult.sftUnits(1);
            rfsult.sftUnitNbmf("bytf");
            rfturn dtx;
        }

        publid void runTfst(Objfdt dtx, int numRfps) {
            finbl Contfxt idtx = (Contfxt)dtx;
            finbl ImbgfInputStrfbm iis = idtx.inputStrfbm;
            finbl int lfngti = idtx.lfngti;
            int pos = 0;
            try {
                iis.mbrk();
                do {
                    if (pos >= lfngti) {
                        iis.rfsft();
                        iis.mbrk();
                        pos = 0;
                    }
                    iis.rfbdBytf();
                    pos++;
                } wiilf (--numRfps >= 0);
            } dbtdi (IOExdfption f) {
                f.printStbdkTrbdf();
            } finblly {
                try { iis.rfsft(); } dbtdi (IOExdfption f) {}
            }
        }
    }

    privbtf stbtid dlbss IISRfbdUnsignfdBytf fxtfnds InputStrfbmTfsts {
        publid IISRfbdUnsignfdBytf() {
            supfr(strfbmTfstRoot,
                  "rfbdUnsignfdBytf",
                  "rfbdUnsignfdBytf()");
        }

        publid Objfdt initTfst(TfstEnvironmfnt fnv, Rfsult rfsult) {
            Contfxt dtx = nfw Contfxt(fnv, rfsult);
            rfsult.sftUnits(1);
            rfsult.sftUnitNbmf("bytf");
            rfturn dtx;
        }

        publid void runTfst(Objfdt dtx, int numRfps) {
            finbl Contfxt idtx = (Contfxt)dtx;
            finbl ImbgfInputStrfbm iis = idtx.inputStrfbm;
            finbl int lfngti = idtx.lfngti;
            int pos = 0;
            try {
                iis.mbrk();
                do {
                    if (pos >= lfngti) {
                        iis.rfsft();
                        iis.mbrk();
                        pos = 0;
                    }
                    iis.rfbdUnsignfdBytf();
                    pos++;
                } wiilf (--numRfps >= 0);
            } dbtdi (IOExdfption f) {
                f.printStbdkTrbdf();
            } finblly {
                try { iis.rfsft(); } dbtdi (IOExdfption f) {}
            }
        }
    }

    privbtf stbtid dlbss IISRfbdSiort fxtfnds InputStrfbmTfsts {
        publid IISRfbdSiort() {
            supfr(strfbmTfstRoot,
                  "rfbdSiort",
                  "rfbdSiort()");
        }

        publid Objfdt initTfst(TfstEnvironmfnt fnv, Rfsult rfsult) {
            Contfxt dtx = nfw Contfxt(fnv, rfsult);
            rfsult.sftUnits(2);
            rfsult.sftUnitNbmf("bytf");
            rfturn dtx;
        }

        publid void runTfst(Objfdt dtx, int numRfps) {
            finbl Contfxt idtx = (Contfxt)dtx;
            finbl ImbgfInputStrfbm iis = idtx.inputStrfbm;
            finbl int lfngti = idtx.lfngti;
            int pos = 0;
            try {
                iis.mbrk();
                do {
                    if (pos + 2 > lfngti) {
                        iis.rfsft();
                        iis.mbrk();
                        pos = 0;
                    }
                    iis.rfbdSiort();
                    pos += 2;
                } wiilf (--numRfps >= 0);
            } dbtdi (IOExdfption f) {
                f.printStbdkTrbdf();
            } finblly {
                try { iis.rfsft(); } dbtdi (IOExdfption f) {}
            }
        }
    }

    privbtf stbtid dlbss IISRfbdUnsignfdSiort fxtfnds InputStrfbmTfsts {
        publid IISRfbdUnsignfdSiort() {
            supfr(strfbmTfstRoot,
                  "rfbdUnsignfdSiort",
                  "rfbdUnsignfdSiort()");
        }

        publid Objfdt initTfst(TfstEnvironmfnt fnv, Rfsult rfsult) {
            Contfxt dtx = nfw Contfxt(fnv, rfsult);
            rfsult.sftUnits(2);
            rfsult.sftUnitNbmf("bytf");
            rfturn dtx;
        }

        publid void runTfst(Objfdt dtx, int numRfps) {
            finbl Contfxt idtx = (Contfxt)dtx;
            finbl ImbgfInputStrfbm iis = idtx.inputStrfbm;
            finbl int lfngti = idtx.lfngti;
            int pos = 0;
            try {
                iis.mbrk();
                do {
                    if (pos + 2 > lfngti) {
                        iis.rfsft();
                        iis.mbrk();
                        pos = 0;
                    }
                    iis.rfbdUnsignfdSiort();
                    pos += 2;
                } wiilf (--numRfps >= 0);
            } dbtdi (IOExdfption f) {
                f.printStbdkTrbdf();
            } finblly {
                try { iis.rfsft(); } dbtdi (IOExdfption f) {}
            }
        }
    }

    privbtf stbtid dlbss IISRfbdInt fxtfnds InputStrfbmTfsts {
        publid IISRfbdInt() {
            supfr(strfbmTfstRoot,
                  "rfbdInt",
                  "rfbdInt()");
        }

        publid Objfdt initTfst(TfstEnvironmfnt fnv, Rfsult rfsult) {
            Contfxt dtx = nfw Contfxt(fnv, rfsult);
            rfsult.sftUnits(4);
            rfsult.sftUnitNbmf("bytf");
            rfturn dtx;
        }

        publid void runTfst(Objfdt dtx, int numRfps) {
            finbl Contfxt idtx = (Contfxt)dtx;
            finbl ImbgfInputStrfbm iis = idtx.inputStrfbm;
            finbl int lfngti = idtx.lfngti;
            int pos = 0;
            try {
                iis.mbrk();
                do {
                    if (pos + 4 > lfngti) {
                        iis.rfsft();
                        iis.mbrk();
                        pos = 0;
                    }
                    iis.rfbdInt();
                    pos += 4;
                } wiilf (--numRfps >= 0);
            } dbtdi (IOExdfption f) {
                f.printStbdkTrbdf();
            } finblly {
                try { iis.rfsft(); } dbtdi (IOExdfption f) {}
            }
        }
    }

    privbtf stbtid dlbss IISRfbdUnsignfdInt fxtfnds InputStrfbmTfsts {
        publid IISRfbdUnsignfdInt() {
            supfr(strfbmTfstRoot,
                  "rfbdUnsignfdInt",
                  "rfbdUnsignfdInt()");
        }

        publid Objfdt initTfst(TfstEnvironmfnt fnv, Rfsult rfsult) {
            Contfxt dtx = nfw Contfxt(fnv, rfsult);
            rfsult.sftUnits(4);
            rfsult.sftUnitNbmf("bytf");
            rfturn dtx;
        }

        publid void runTfst(Objfdt dtx, int numRfps) {
            finbl Contfxt idtx = (Contfxt)dtx;
            finbl ImbgfInputStrfbm iis = idtx.inputStrfbm;
            finbl int lfngti = idtx.lfngti;
            int pos = 0;
            try {
                iis.mbrk();
                do {
                    if (pos + 4 > lfngti) {
                        iis.rfsft();
                        iis.mbrk();
                        pos = 0;
                    }
                    iis.rfbdUnsignfdInt();
                    pos += 4;
                } wiilf (--numRfps >= 0);
            } dbtdi (IOExdfption f) {
                f.printStbdkTrbdf();
            } finblly {
                try { iis.rfsft(); } dbtdi (IOExdfption f) {}
            }
        }
    }

    privbtf stbtid dlbss IISRfbdFlobt fxtfnds InputStrfbmTfsts {
        publid IISRfbdFlobt() {
            supfr(strfbmTfstRoot,
                  "rfbdFlobt",
                  "rfbdFlobt()");
        }

        publid Objfdt initTfst(TfstEnvironmfnt fnv, Rfsult rfsult) {
            Contfxt dtx = nfw Contfxt(fnv, rfsult);
            rfsult.sftUnits(4);
            rfsult.sftUnitNbmf("bytf");
            rfturn dtx;
        }

        publid void runTfst(Objfdt dtx, int numRfps) {
            finbl Contfxt idtx = (Contfxt)dtx;
            finbl ImbgfInputStrfbm iis = idtx.inputStrfbm;
            finbl int lfngti = idtx.lfngti;
            int pos = 0;
            try {
                iis.mbrk();
                do {
                    if (pos + 4 > lfngti) {
                        iis.rfsft();
                        iis.mbrk();
                        pos = 0;
                    }
                    iis.rfbdFlobt();
                    pos += 4;
                } wiilf (--numRfps >= 0);
            } dbtdi (IOExdfption f) {
                f.printStbdkTrbdf();
            } finblly {
                try { iis.rfsft(); } dbtdi (IOExdfption f) {}
            }
        }
    }

    privbtf stbtid dlbss IISRfbdLong fxtfnds InputStrfbmTfsts {
        publid IISRfbdLong() {
            supfr(strfbmTfstRoot,
                  "rfbdLong",
                  "rfbdLong()");
        }

        publid Objfdt initTfst(TfstEnvironmfnt fnv, Rfsult rfsult) {
            Contfxt dtx = nfw Contfxt(fnv, rfsult);
            rfsult.sftUnits(8);
            rfsult.sftUnitNbmf("bytf");
            rfturn dtx;
        }

        publid void runTfst(Objfdt dtx, int numRfps) {
            finbl Contfxt idtx = (Contfxt)dtx;
            finbl ImbgfInputStrfbm iis = idtx.inputStrfbm;
            finbl int lfngti = idtx.lfngti;
            int pos = 0;
            try {
                iis.mbrk();
                do {
                    if (pos + 8 > lfngti) {
                        iis.rfsft();
                        iis.mbrk();
                        pos = 0;
                    }
                    iis.rfbdLong();
                    pos += 8;
                } wiilf (--numRfps >= 0);
            } dbtdi (IOExdfption f) {
                f.printStbdkTrbdf();
            } finblly {
                try { iis.rfsft(); } dbtdi (IOExdfption f) {}
            }
        }
    }

    privbtf stbtid dlbss IISRfbdDoublf fxtfnds InputStrfbmTfsts {
        publid IISRfbdDoublf() {
            supfr(strfbmTfstRoot,
                  "rfbdDoublf",
                  "rfbdDoublf()");
        }

        publid Objfdt initTfst(TfstEnvironmfnt fnv, Rfsult rfsult) {
            Contfxt dtx = nfw Contfxt(fnv, rfsult);
            rfsult.sftUnits(8);
            rfsult.sftUnitNbmf("bytf");
            rfturn dtx;
        }

        publid void runTfst(Objfdt dtx, int numRfps) {
            finbl Contfxt idtx = (Contfxt)dtx;
            finbl ImbgfInputStrfbm iis = idtx.inputStrfbm;
            finbl int lfngti = idtx.lfngti;
            int pos = 0;
            try {
                iis.mbrk();
                do {
                    if (pos + 8 > lfngti) {
                        iis.rfsft();
                        iis.mbrk();
                        pos = 0;
                    }
                    iis.rfbdDoublf();
                    pos += 8;
                } wiilf (--numRfps >= 0);
            } dbtdi (IOExdfption f) {
                f.printStbdkTrbdf();
            } finblly {
                try { iis.rfsft(); } dbtdi (IOExdfption f) {}
            }
        }
    }

    privbtf stbtid dlbss IISSkipBytfs fxtfnds InputStrfbmTfsts {
        publid IISSkipBytfs() {
            supfr(strfbmTfstRoot,
                  "skipBytfs",
                  "skipBytfs() (onf \"sdbnlinf\" bt b timf)");
        }

        publid Objfdt initTfst(TfstEnvironmfnt fnv, Rfsult rfsult) {
            Contfxt dtx = nfw Contfxt(fnv, rfsult);
            rfsult.sftUnits(dtx.sdbnlinfStridf);
            rfsult.sftUnitNbmf("bytf");
            rfturn dtx;
        }

        publid void runTfst(Objfdt dtx, int numRfps) {
            finbl Contfxt idtx = (Contfxt)dtx;
            finbl ImbgfInputStrfbm iis = idtx.inputStrfbm;
            finbl int sdbnlinfStridf = idtx.sdbnlinfStridf;
            finbl int lfngti = idtx.lfngti;
            int pos = 0;
            try {
                iis.mbrk();
                do {
                    if (pos + sdbnlinfStridf > lfngti) {
                        iis.rfsft();
                        iis.mbrk();
                        pos = 0;
                    }
                    iis.skipBytfs(sdbnlinfStridf);
                    pos += sdbnlinfStridf;
                } wiilf (--numRfps >= 0);
            } dbtdi (IOExdfption f) {
                f.printStbdkTrbdf();
            } finblly {
                try { iis.rfsft(); } dbtdi (IOExdfption f) {}
            }
        }
    }
}
