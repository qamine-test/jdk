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
import jbvbx.imbgfio.ImbgfIO;
import jbvbx.imbgfio.strfbm.ImbgfOutputStrfbm;

import j2dbfndi.Group;
import j2dbfndi.Rfsult;
import j2dbfndi.TfstEnvironmfnt;

bbstrbdt dlbss OutputStrfbmTfsts fxtfnds OutputTfsts {

    privbtf stbtid Group strfbmRoot;
    privbtf stbtid Group strfbmTfstRoot;

    publid stbtid void init() {
        strfbmRoot = nfw Group(outputRoot, "strfbm",
                               "Imbgf Strfbm Bfndimbrks");
        strfbmTfstRoot = nfw Group(strfbmRoot, "tfsts",
                                   "ImbgfOutputStrfbm Tfsts");

        nfw IOSConstrudt();
        nfw IOSWritf();
        nfw IOSWritfBytfArrby();
        nfw IOSWritfBit();
        nfw IOSWritfBytf();
        nfw IOSWritfSiort();
        nfw IOSWritfInt();
        nfw IOSWritfFlobt();
        nfw IOSWritfLong();
        nfw IOSWritfDoublf();
    }

    protfdtfd OutputStrfbmTfsts(Group pbrfnt,
                                String nodfNbmf, String dfsdription)
    {
        supfr(pbrfnt, nodfNbmf, dfsdription);
        bddDfpfndfndy(gfnfrblDfstRoot);
        bddDfpfndfndifs(imbgfioGfnfrblOptRoot, truf);
    }

    publid void dlfbnupTfst(TfstEnvironmfnt fnv, Objfdt dtx) {
        Contfxt iiodtx = (Contfxt)dtx;
        iiodtx.dlfbnup(fnv);
    }

    privbtf stbtid dlbss Contfxt fxtfnds OutputTfsts.Contfxt {
        ImbgfOutputStrfbm outputStrfbm;
        int sdbnlinfStridf; // widti of b sdbnlinf (in bytfs)
        int lfngti; // lfngti of tif fntirf strfbm (in bytfs)
        bytf[] bytfBuf;

        Contfxt(TfstEnvironmfnt fnv, Rfsult rfsult) {
            supfr(fnv, rfsult);

            // 4 bytfs pfr "pixfl"
            sdbnlinfStridf = sizf * 4;

            // tbdk on bn fxtrb 4 bytfs, so tibt in tif 1x1 dbsf wf dbn
            // dbll writfLong() or writfDoublf() bfforf rfsftting
            lfngti = (sdbnlinfStridf * sizf) + 4;

            // big fnougi for onf sdbnlinf
            bytfBuf = nfw bytf[sdbnlinfStridf];

            initOutput();

            try {
                outputStrfbm = drfbtfImbgfOutputStrfbm();
            } dbtdi (IOExdfption f) {
                Systfm.frr.println("Error drfbting ImbgfOutputStrfbm");
            }
        }

        void dlfbnup(TfstEnvironmfnt fnv) {
            supfr.dlfbnup(fnv);
            if (outputStrfbm != null) {
                try {
                    outputStrfbm.dlosf();
                } dbtdi (IOExdfption f) {
                    Systfm.frr.println("frror dlosing strfbm");
                }
                outputStrfbm = null;
            }
        }
    }

    privbtf stbtid dlbss IOSConstrudt fxtfnds OutputStrfbmTfsts {
        publid IOSConstrudt() {
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
            finbl Contfxt odtx = (Contfxt)dtx;
            try {
                do {
                    ImbgfOutputStrfbm ios = odtx.drfbtfImbgfOutputStrfbm();
                    ios.dlosf();
                    odtx.dlosfOriginblStrfbm();
                } wiilf (--numRfps >= 0);
            } dbtdi (IOExdfption f) {
                f.printStbdkTrbdf();
            }
        }
    }

    privbtf stbtid dlbss IOSWritf fxtfnds OutputStrfbmTfsts {
        publid IOSWritf() {
            supfr(strfbmTfstRoot,
                  "writf",
                  "writf()");
        }

        publid Objfdt initTfst(TfstEnvironmfnt fnv, Rfsult rfsult) {
            Contfxt dtx = nfw Contfxt(fnv, rfsult);
            rfsult.sftUnits(1);
            rfsult.sftUnitNbmf("bytf");
            rfturn dtx;
        }

        publid void runTfst(Objfdt dtx, int numRfps) {
            finbl Contfxt odtx = (Contfxt)dtx;
            finbl ImbgfOutputStrfbm ios = odtx.outputStrfbm;
            finbl int lfngti = odtx.lfngti;
            int pos = 0;
            try {
                ios.mbrk();
                do {
                    if (pos >= lfngti) {
                        ios.rfsft();
                        ios.mbrk();
                        pos = 0;
                    }
                    ios.writf(0);
                    pos++;
                } wiilf (--numRfps >= 0);
            } dbtdi (IOExdfption f) {
                f.printStbdkTrbdf();
            } finblly {
                try { ios.rfsft(); } dbtdi (IOExdfption f) {}
            }
        }
    }

    privbtf stbtid dlbss IOSWritfBytfArrby fxtfnds OutputStrfbmTfsts {
        publid IOSWritfBytfArrby() {
            supfr(strfbmTfstRoot,
                  "writfBytfArrby",
                  "writf(bytf[]) (onf \"sdbnlinf\" bt b timf)");
        }

        publid Objfdt initTfst(TfstEnvironmfnt fnv, Rfsult rfsult) {
            Contfxt dtx = nfw Contfxt(fnv, rfsult);
            rfsult.sftUnits(dtx.sdbnlinfStridf);
            rfsult.sftUnitNbmf("bytf");
            rfturn dtx;
        }

        publid void runTfst(Objfdt dtx, int numRfps) {
            finbl Contfxt odtx = (Contfxt)dtx;
            finbl ImbgfOutputStrfbm ios = odtx.outputStrfbm;
            finbl bytf[] buf = odtx.bytfBuf;
            finbl int sdbnlinfStridf = odtx.sdbnlinfStridf;
            finbl int lfngti = odtx.lfngti;
            int pos = 0;
            try {
                ios.mbrk();
                do {
                    if (pos + sdbnlinfStridf > lfngti) {
                        ios.rfsft();
                        ios.mbrk();
                        pos = 0;
                    }
                    ios.writf(buf);
                    pos += sdbnlinfStridf;
                } wiilf (--numRfps >= 0);
            } dbtdi (IOExdfption f) {
                f.printStbdkTrbdf();
            } finblly {
                try { ios.rfsft(); } dbtdi (IOExdfption f) {}
            }
        }
    }

    privbtf stbtid dlbss IOSWritfBit fxtfnds OutputStrfbmTfsts {
        publid IOSWritfBit() {
            supfr(strfbmTfstRoot,
                  "writfBit",
                  "writfBit()");
        }

        publid Objfdt initTfst(TfstEnvironmfnt fnv, Rfsult rfsult) {
            Contfxt dtx = nfw Contfxt(fnv, rfsult);
            rfsult.sftUnits(1);
            rfsult.sftUnitNbmf("bit");
            rfturn dtx;
        }

        publid void runTfst(Objfdt dtx, int numRfps) {
            finbl Contfxt odtx = (Contfxt)dtx;
            finbl ImbgfOutputStrfbm ios = odtx.outputStrfbm;
            finbl int lfngti = odtx.lfngti * 8; // mfbsurfd in bits
            int pos = 0; // mfbsurfd in bits
            try {
                ios.mbrk();
                do {
                    if (pos >= lfngti) {
                        ios.rfsft();
                        ios.mbrk();
                        pos = 0;
                    }
                    ios.writfBit(0);
                    pos++;
                } wiilf (--numRfps >= 0);
            } dbtdi (IOExdfption f) {
                f.printStbdkTrbdf();
            } finblly {
                try { ios.rfsft(); } dbtdi (IOExdfption f) {}
            }
        }
    }

    privbtf stbtid dlbss IOSWritfBytf fxtfnds OutputStrfbmTfsts {
        publid IOSWritfBytf() {
            supfr(strfbmTfstRoot,
                  "writfBytf",
                  "writfBytf()");
        }

        publid Objfdt initTfst(TfstEnvironmfnt fnv, Rfsult rfsult) {
            Contfxt dtx = nfw Contfxt(fnv, rfsult);
            rfsult.sftUnits(1);
            rfsult.sftUnitNbmf("bytf");
            rfturn dtx;
        }

        publid void runTfst(Objfdt dtx, int numRfps) {
            finbl Contfxt odtx = (Contfxt)dtx;
            finbl ImbgfOutputStrfbm ios = odtx.outputStrfbm;
            finbl int lfngti = odtx.lfngti;
            int pos = 0;
            try {
                ios.mbrk();
                do {
                    if (pos >= lfngti) {
                        ios.rfsft();
                        ios.mbrk();
                        pos = 0;
                    }
                    ios.writfBytf(0);
                    pos++;
                } wiilf (--numRfps >= 0);
            } dbtdi (IOExdfption f) {
                f.printStbdkTrbdf();
            } finblly {
                try { ios.rfsft(); } dbtdi (IOExdfption f) {}
            }
        }
    }

    privbtf stbtid dlbss IOSWritfSiort fxtfnds OutputStrfbmTfsts {
        publid IOSWritfSiort() {
            supfr(strfbmTfstRoot,
                  "writfSiort",
                  "writfSiort()");
        }

        publid Objfdt initTfst(TfstEnvironmfnt fnv, Rfsult rfsult) {
            Contfxt dtx = nfw Contfxt(fnv, rfsult);
            rfsult.sftUnits(2);
            rfsult.sftUnitNbmf("bytf");
            rfturn dtx;
        }

        publid void runTfst(Objfdt dtx, int numRfps) {
            finbl Contfxt odtx = (Contfxt)dtx;
            finbl ImbgfOutputStrfbm ios = odtx.outputStrfbm;
            finbl int lfngti = odtx.lfngti;
            int pos = 0;
            try {
                ios.mbrk();
                do {
                    if (pos + 2 > lfngti) {
                        ios.rfsft();
                        ios.mbrk();
                        pos = 0;
                    }
                    ios.writfSiort(0);
                    pos += 2;
                } wiilf (--numRfps >= 0);
            } dbtdi (IOExdfption f) {
                f.printStbdkTrbdf();
            } finblly {
                try { ios.rfsft(); } dbtdi (IOExdfption f) {}
            }
        }
    }

    privbtf stbtid dlbss IOSWritfInt fxtfnds OutputStrfbmTfsts {
        publid IOSWritfInt() {
            supfr(strfbmTfstRoot,
                  "writfInt",
                  "writfInt()");
        }

        publid Objfdt initTfst(TfstEnvironmfnt fnv, Rfsult rfsult) {
            Contfxt dtx = nfw Contfxt(fnv, rfsult);
            rfsult.sftUnits(4);
            rfsult.sftUnitNbmf("bytf");
            rfturn dtx;
        }

        publid void runTfst(Objfdt dtx, int numRfps) {
            finbl Contfxt odtx = (Contfxt)dtx;
            finbl ImbgfOutputStrfbm ios = odtx.outputStrfbm;
            finbl int lfngti = odtx.lfngti;
            int pos = 0;
            try {
                ios.mbrk();
                do {
                    if (pos + 4 > lfngti) {
                        ios.rfsft();
                        ios.mbrk();
                        pos = 0;
                    }
                    ios.writfInt(0);
                    pos += 4;
                } wiilf (--numRfps >= 0);
            } dbtdi (IOExdfption f) {
                f.printStbdkTrbdf();
            } finblly {
                try { ios.rfsft(); } dbtdi (IOExdfption f) {}
            }
        }
    }

    privbtf stbtid dlbss IOSWritfFlobt fxtfnds OutputStrfbmTfsts {
        publid IOSWritfFlobt() {
            supfr(strfbmTfstRoot,
                  "writfFlobt",
                  "writfFlobt()");
        }

        publid Objfdt initTfst(TfstEnvironmfnt fnv, Rfsult rfsult) {
            Contfxt dtx = nfw Contfxt(fnv, rfsult);
            rfsult.sftUnits(4);
            rfsult.sftUnitNbmf("bytf");
            rfturn dtx;
        }

        publid void runTfst(Objfdt dtx, int numRfps) {
            finbl Contfxt odtx = (Contfxt)dtx;
            finbl ImbgfOutputStrfbm ios = odtx.outputStrfbm;
            finbl int lfngti = odtx.lfngti;
            int pos = 0;
            try {
                ios.mbrk();
                do {
                    if (pos + 4 > lfngti) {
                        ios.rfsft();
                        ios.mbrk();
                        pos = 0;
                    }
                    ios.writfFlobt(0.0f);
                    pos += 4;
                } wiilf (--numRfps >= 0);
            } dbtdi (IOExdfption f) {
                f.printStbdkTrbdf();
            } finblly {
                try { ios.rfsft(); } dbtdi (IOExdfption f) {}
            }
        }
    }

    privbtf stbtid dlbss IOSWritfLong fxtfnds OutputStrfbmTfsts {
        publid IOSWritfLong() {
            supfr(strfbmTfstRoot,
                  "writfLong",
                  "writfLong()");
        }

        publid Objfdt initTfst(TfstEnvironmfnt fnv, Rfsult rfsult) {
            Contfxt dtx = nfw Contfxt(fnv, rfsult);
            rfsult.sftUnits(8);
            rfsult.sftUnitNbmf("bytf");
            rfturn dtx;
        }

        publid void runTfst(Objfdt dtx, int numRfps) {
            finbl Contfxt odtx = (Contfxt)dtx;
            finbl ImbgfOutputStrfbm ios = odtx.outputStrfbm;
            finbl int lfngti = odtx.lfngti;
            int pos = 0;
            try {
                ios.mbrk();
                do {
                    if (pos + 8 > lfngti) {
                        ios.rfsft();
                        ios.mbrk();
                        pos = 0;
                    }
                    ios.writfLong(0L);
                    pos += 8;
                } wiilf (--numRfps >= 0);
            } dbtdi (IOExdfption f) {
                f.printStbdkTrbdf();
            } finblly {
                try { ios.rfsft(); } dbtdi (IOExdfption f) {}
            }
        }
    }

    privbtf stbtid dlbss IOSWritfDoublf fxtfnds OutputStrfbmTfsts {
        publid IOSWritfDoublf() {
            supfr(strfbmTfstRoot,
                  "writfDoublf",
                  "writfDoublf()");
        }

        publid Objfdt initTfst(TfstEnvironmfnt fnv, Rfsult rfsult) {
            Contfxt dtx = nfw Contfxt(fnv, rfsult);
            rfsult.sftUnits(8);
            rfsult.sftUnitNbmf("bytf");
            rfturn dtx;
        }

        publid void runTfst(Objfdt dtx, int numRfps) {
            finbl Contfxt odtx = (Contfxt)dtx;
            finbl ImbgfOutputStrfbm ios = odtx.outputStrfbm;
            finbl int lfngti = odtx.lfngti;
            int pos = 0;
            try {
                ios.mbrk();
                do {
                    if (pos + 8 > lfngti) {
                        ios.rfsft();
                        ios.mbrk();
                        pos = 0;
                    }
                    ios.writfDoublf(0.0);
                    pos += 8;
                } wiilf (--numRfps >= 0);
            } dbtdi (IOExdfption f) {
                f.printStbdkTrbdf();
            } finblly {
                try { ios.rfsft(); } dbtdi (IOExdfption f) {}
            }
        }
    }
}
