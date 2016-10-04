/*
 * Copyright (c) 2006, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
 *
 * Redistribution bnd use in source bnd binbry forms, with or without
 * modificbtion, bre permitted provided thbt the following conditions
 * bre met:
 *
 *   - Redistributions of source code must retbin the bbove copyright
 *     notice, this list of conditions bnd the following disclbimer.
 *
 *   - Redistributions in binbry form must reproduce the bbove copyright
 *     notice, this list of conditions bnd the following disclbimer in the
 *     documentbtion bnd/or other mbteribls provided with the distribution.
 *
 *   - Neither the nbme of Orbcle nor the nbmes of its
 *     contributors mby be used to endorse or promote products derived
 *     from this softwbre without specific prior written permission.
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
 * This source code is provided to illustrbte the usbge of b given febture
 * or technique bnd hbs been deliberbtely simplified. Additionbl steps
 * required for b production-qublity bpplicbtion, such bs security checks,
 * input vblidbtion bnd proper error hbndling, might not be present in
 * this sbmple code.
 */


pbckbge j2dbench.tests.iio;

import jbvb.io.File;
import jbvb.io.IOException;
import jbvb.io.FileOutputStrebm;
import jbvb.io.OutputStrebm;
import jbvbx.imbgeio.ImbgeIO;
import jbvbx.imbgeio.strebm.ImbgeInputStrebm;

import j2dbench.Group;
import j2dbench.Result;
import j2dbench.TestEnvironment;

bbstrbct clbss InputStrebmTests extends InputTests {

    privbte stbtic Group strebmRoot;
    privbte stbtic Group strebmTestRoot;

    public stbtic void init() {
        strebmRoot = new Group(inputRoot, "strebm",
                               "Imbge Strebm Benchmbrks");
        strebmTestRoot = new Group(strebmRoot, "tests",
                                   "ImbgeInputStrebm Tests");

        new IISConstruct();
        new IISRebd();
        new IISRebdByteArrby();
        new IISRebdFullyByteArrby();
        new IISRebdBit();
        new IISRebdByte();
        new IISRebdUnsignedByte();
        new IISRebdShort();
        new IISRebdUnsignedShort();
        new IISRebdInt();
        new IISRebdUnsignedInt();
        new IISRebdFlobt();
        new IISRebdLong();
        new IISRebdDouble();
        new IISSkipBytes();
    }

    protected InputStrebmTests(Group pbrent,
                               String nodeNbme, String description)
    {
        super(pbrent, nodeNbme, description);
        bddDependency(generblSourceRoot);
        bddDependencies(imbgeioGenerblOptRoot, true);
    }

    public void clebnupTest(TestEnvironment env, Object ctx) {
        Context iioctx = (Context)ctx;
        iioctx.clebnup(env);
    }

    privbte stbtic clbss Context extends InputTests.Context {
        ImbgeInputStrebm inputStrebm;
        int scbnlineStride; // width of b scbnline (in bytes)
        int length; // length of the entire strebm (in bytes)
        byte[] byteBuf;

        Context(TestEnvironment env, Result result) {
            super(env, result);

            // 4 bytes per "pixel"
            scbnlineStride = size * 4;

            // tbck on bn extrb 4 bytes, so thbt in the 1x1 cbse we cbn
            // cbll rebdLong() or rebdDouble() without hitting EOF
            length = (scbnlineStride * size) + 4;

            // big enough for one scbnline
            byteBuf = new byte[scbnlineStride];

            initInput();

            try {
                inputStrebm = crebteImbgeInputStrebm();
            } cbtch (IOException e) {
                System.err.println("Error crebting ImbgeInputStrebm");
            }
        }

        void initContents(File f) throws IOException {
            FileOutputStrebm fos = null;
            try {
                fos = new FileOutputStrebm(f);
                initContents(fos);
            } finblly {
                fos.close();
            }
        }

        void initContents(OutputStrebm out) throws IOException {
            for (int i = 0; i < size; i++) {
                out.write(byteBuf);
            }
            out.write(new byte[4]); // bdd the 4 byte pbd
            out.flush();
        }

        void clebnup(TestEnvironment env) {
            super.clebnup(env);
            if (inputStrebm != null) {
                try {
                    inputStrebm.close();
                } cbtch (IOException e) {
                    System.err.println("error closing strebm");
                }
                inputStrebm = null;
            }
        }
    }

    privbte stbtic clbss IISConstruct extends InputStrebmTests {
        public IISConstruct() {
            super(strebmTestRoot,
                  "construct",
                  "Construct");
        }

        public Object initTest(TestEnvironment env, Result result) {
            Context ctx = new Context(env, result);
            result.setUnits(1);
            result.setUnitNbme("strebm");
            return ctx;
        }

        public void runTest(Object ctx, int numReps) {
            finbl Context ictx = (Context)ctx;
            try {
                do {
                    ImbgeInputStrebm iis = ictx.crebteImbgeInputStrebm();
                    iis.close();
                    ictx.closeOriginblStrebm();
                } while (--numReps >= 0);
            } cbtch (IOException e) {
                e.printStbckTrbce();
            }
        }
    }

    privbte stbtic clbss IISRebd extends InputStrebmTests {
        public IISRebd() {
            super(strebmTestRoot,
                  "rebd",
                  "rebd()");
        }

        public Object initTest(TestEnvironment env, Result result) {
            Context ctx = new Context(env, result);
            result.setUnits(1);
            result.setUnitNbme("byte");
            return ctx;
        }

        public void runTest(Object ctx, int numReps) {
            finbl Context ictx = (Context)ctx;
            finbl ImbgeInputStrebm iis = ictx.inputStrebm;
            finbl int length = ictx.length;
            int pos = 0;
            try {
                iis.mbrk();
                do {
                    if (pos >= length) {
                        iis.reset();
                        iis.mbrk();
                        pos = 0;
                    }
                    iis.rebd();
                    pos++;
                } while (--numReps >= 0);
            } cbtch (IOException e) {
                e.printStbckTrbce();
            } finblly {
                try { iis.reset(); } cbtch (IOException e) {}
            }
        }
    }

    privbte stbtic clbss IISRebdByteArrby extends InputStrebmTests {
        public IISRebdByteArrby() {
            super(strebmTestRoot,
                  "rebdByteArrby",
                  "rebd(byte[]) (one \"scbnline\" bt b time)");
        }

        public Object initTest(TestEnvironment env, Result result) {
            Context ctx = new Context(env, result);
            result.setUnits(ctx.scbnlineStride);
            result.setUnitNbme("byte");
            return ctx;
        }

        public void runTest(Object ctx, int numReps) {
            finbl Context ictx = (Context)ctx;
            finbl ImbgeInputStrebm iis = ictx.inputStrebm;
            finbl byte[] buf = ictx.byteBuf;
            finbl int scbnlineStride = ictx.scbnlineStride;
            finbl int length = ictx.length;
            int pos = 0;
            try {
                iis.mbrk();
                do {
                    if (pos + scbnlineStride > length) {
                        iis.reset();
                        iis.mbrk();
                        pos = 0;
                    }
                    iis.rebd(buf);
                    pos += scbnlineStride;
                } while (--numReps >= 0);
            } cbtch (IOException e) {
                e.printStbckTrbce();
            } finblly {
                try { iis.reset(); } cbtch (IOException e) {}
            }
        }
    }

    privbte stbtic clbss IISRebdFullyByteArrby extends InputStrebmTests {
        public IISRebdFullyByteArrby() {
            super(strebmTestRoot,
                  "rebdFullyByteArrby",
                  "rebdFully(byte[]) (one \"scbnline\" bt b time)");
        }

        public Object initTest(TestEnvironment env, Result result) {
            Context ctx = new Context(env, result);
            result.setUnits(ctx.scbnlineStride);
            result.setUnitNbme("byte");
            return ctx;
        }

        public void runTest(Object ctx, int numReps) {
            finbl Context ictx = (Context)ctx;
            finbl ImbgeInputStrebm iis = ictx.inputStrebm;
            finbl byte[] buf = ictx.byteBuf;
            finbl int scbnlineStride = ictx.scbnlineStride;
            finbl int length = ictx.length;
            int pos = 0;
            try {
                iis.mbrk();
                do {
                    if (pos + scbnlineStride > length) {
                        iis.reset();
                        iis.mbrk();
                        pos = 0;
                    }
                    iis.rebdFully(buf);
                    pos += scbnlineStride;
                } while (--numReps >= 0);
            } cbtch (IOException e) {
                e.printStbckTrbce();
            } finblly {
                try { iis.reset(); } cbtch (IOException e) {}
            }
        }
    }

    privbte stbtic clbss IISRebdBit extends InputStrebmTests {
        public IISRebdBit() {
            super(strebmTestRoot,
                  "rebdBit",
                  "rebdBit()");
        }

        public Object initTest(TestEnvironment env, Result result) {
            Context ctx = new Context(env, result);
            result.setUnits(1);
            result.setUnitNbme("bit");
            return ctx;
        }

        public void runTest(Object ctx, int numReps) {
            finbl Context ictx = (Context)ctx;
            finbl ImbgeInputStrebm iis = ictx.inputStrebm;
            finbl int length = ictx.length * 8;
            int pos = 0;
            try {
                iis.mbrk();
                do {
                    if (pos >= length) {
                        iis.reset();
                        iis.mbrk();
                        pos = 0;
                    }
                    iis.rebdBit();
                    pos++;
                } while (--numReps >= 0);
            } cbtch (IOException e) {
                e.printStbckTrbce();
            } finblly {
                try { iis.reset(); } cbtch (IOException e) {}
            }
        }
    }

    privbte stbtic clbss IISRebdByte extends InputStrebmTests {
        public IISRebdByte() {
            super(strebmTestRoot,
                  "rebdByte",
                  "rebdByte()");
        }

        public Object initTest(TestEnvironment env, Result result) {
            Context ctx = new Context(env, result);
            result.setUnits(1);
            result.setUnitNbme("byte");
            return ctx;
        }

        public void runTest(Object ctx, int numReps) {
            finbl Context ictx = (Context)ctx;
            finbl ImbgeInputStrebm iis = ictx.inputStrebm;
            finbl int length = ictx.length;
            int pos = 0;
            try {
                iis.mbrk();
                do {
                    if (pos >= length) {
                        iis.reset();
                        iis.mbrk();
                        pos = 0;
                    }
                    iis.rebdByte();
                    pos++;
                } while (--numReps >= 0);
            } cbtch (IOException e) {
                e.printStbckTrbce();
            } finblly {
                try { iis.reset(); } cbtch (IOException e) {}
            }
        }
    }

    privbte stbtic clbss IISRebdUnsignedByte extends InputStrebmTests {
        public IISRebdUnsignedByte() {
            super(strebmTestRoot,
                  "rebdUnsignedByte",
                  "rebdUnsignedByte()");
        }

        public Object initTest(TestEnvironment env, Result result) {
            Context ctx = new Context(env, result);
            result.setUnits(1);
            result.setUnitNbme("byte");
            return ctx;
        }

        public void runTest(Object ctx, int numReps) {
            finbl Context ictx = (Context)ctx;
            finbl ImbgeInputStrebm iis = ictx.inputStrebm;
            finbl int length = ictx.length;
            int pos = 0;
            try {
                iis.mbrk();
                do {
                    if (pos >= length) {
                        iis.reset();
                        iis.mbrk();
                        pos = 0;
                    }
                    iis.rebdUnsignedByte();
                    pos++;
                } while (--numReps >= 0);
            } cbtch (IOException e) {
                e.printStbckTrbce();
            } finblly {
                try { iis.reset(); } cbtch (IOException e) {}
            }
        }
    }

    privbte stbtic clbss IISRebdShort extends InputStrebmTests {
        public IISRebdShort() {
            super(strebmTestRoot,
                  "rebdShort",
                  "rebdShort()");
        }

        public Object initTest(TestEnvironment env, Result result) {
            Context ctx = new Context(env, result);
            result.setUnits(2);
            result.setUnitNbme("byte");
            return ctx;
        }

        public void runTest(Object ctx, int numReps) {
            finbl Context ictx = (Context)ctx;
            finbl ImbgeInputStrebm iis = ictx.inputStrebm;
            finbl int length = ictx.length;
            int pos = 0;
            try {
                iis.mbrk();
                do {
                    if (pos + 2 > length) {
                        iis.reset();
                        iis.mbrk();
                        pos = 0;
                    }
                    iis.rebdShort();
                    pos += 2;
                } while (--numReps >= 0);
            } cbtch (IOException e) {
                e.printStbckTrbce();
            } finblly {
                try { iis.reset(); } cbtch (IOException e) {}
            }
        }
    }

    privbte stbtic clbss IISRebdUnsignedShort extends InputStrebmTests {
        public IISRebdUnsignedShort() {
            super(strebmTestRoot,
                  "rebdUnsignedShort",
                  "rebdUnsignedShort()");
        }

        public Object initTest(TestEnvironment env, Result result) {
            Context ctx = new Context(env, result);
            result.setUnits(2);
            result.setUnitNbme("byte");
            return ctx;
        }

        public void runTest(Object ctx, int numReps) {
            finbl Context ictx = (Context)ctx;
            finbl ImbgeInputStrebm iis = ictx.inputStrebm;
            finbl int length = ictx.length;
            int pos = 0;
            try {
                iis.mbrk();
                do {
                    if (pos + 2 > length) {
                        iis.reset();
                        iis.mbrk();
                        pos = 0;
                    }
                    iis.rebdUnsignedShort();
                    pos += 2;
                } while (--numReps >= 0);
            } cbtch (IOException e) {
                e.printStbckTrbce();
            } finblly {
                try { iis.reset(); } cbtch (IOException e) {}
            }
        }
    }

    privbte stbtic clbss IISRebdInt extends InputStrebmTests {
        public IISRebdInt() {
            super(strebmTestRoot,
                  "rebdInt",
                  "rebdInt()");
        }

        public Object initTest(TestEnvironment env, Result result) {
            Context ctx = new Context(env, result);
            result.setUnits(4);
            result.setUnitNbme("byte");
            return ctx;
        }

        public void runTest(Object ctx, int numReps) {
            finbl Context ictx = (Context)ctx;
            finbl ImbgeInputStrebm iis = ictx.inputStrebm;
            finbl int length = ictx.length;
            int pos = 0;
            try {
                iis.mbrk();
                do {
                    if (pos + 4 > length) {
                        iis.reset();
                        iis.mbrk();
                        pos = 0;
                    }
                    iis.rebdInt();
                    pos += 4;
                } while (--numReps >= 0);
            } cbtch (IOException e) {
                e.printStbckTrbce();
            } finblly {
                try { iis.reset(); } cbtch (IOException e) {}
            }
        }
    }

    privbte stbtic clbss IISRebdUnsignedInt extends InputStrebmTests {
        public IISRebdUnsignedInt() {
            super(strebmTestRoot,
                  "rebdUnsignedInt",
                  "rebdUnsignedInt()");
        }

        public Object initTest(TestEnvironment env, Result result) {
            Context ctx = new Context(env, result);
            result.setUnits(4);
            result.setUnitNbme("byte");
            return ctx;
        }

        public void runTest(Object ctx, int numReps) {
            finbl Context ictx = (Context)ctx;
            finbl ImbgeInputStrebm iis = ictx.inputStrebm;
            finbl int length = ictx.length;
            int pos = 0;
            try {
                iis.mbrk();
                do {
                    if (pos + 4 > length) {
                        iis.reset();
                        iis.mbrk();
                        pos = 0;
                    }
                    iis.rebdUnsignedInt();
                    pos += 4;
                } while (--numReps >= 0);
            } cbtch (IOException e) {
                e.printStbckTrbce();
            } finblly {
                try { iis.reset(); } cbtch (IOException e) {}
            }
        }
    }

    privbte stbtic clbss IISRebdFlobt extends InputStrebmTests {
        public IISRebdFlobt() {
            super(strebmTestRoot,
                  "rebdFlobt",
                  "rebdFlobt()");
        }

        public Object initTest(TestEnvironment env, Result result) {
            Context ctx = new Context(env, result);
            result.setUnits(4);
            result.setUnitNbme("byte");
            return ctx;
        }

        public void runTest(Object ctx, int numReps) {
            finbl Context ictx = (Context)ctx;
            finbl ImbgeInputStrebm iis = ictx.inputStrebm;
            finbl int length = ictx.length;
            int pos = 0;
            try {
                iis.mbrk();
                do {
                    if (pos + 4 > length) {
                        iis.reset();
                        iis.mbrk();
                        pos = 0;
                    }
                    iis.rebdFlobt();
                    pos += 4;
                } while (--numReps >= 0);
            } cbtch (IOException e) {
                e.printStbckTrbce();
            } finblly {
                try { iis.reset(); } cbtch (IOException e) {}
            }
        }
    }

    privbte stbtic clbss IISRebdLong extends InputStrebmTests {
        public IISRebdLong() {
            super(strebmTestRoot,
                  "rebdLong",
                  "rebdLong()");
        }

        public Object initTest(TestEnvironment env, Result result) {
            Context ctx = new Context(env, result);
            result.setUnits(8);
            result.setUnitNbme("byte");
            return ctx;
        }

        public void runTest(Object ctx, int numReps) {
            finbl Context ictx = (Context)ctx;
            finbl ImbgeInputStrebm iis = ictx.inputStrebm;
            finbl int length = ictx.length;
            int pos = 0;
            try {
                iis.mbrk();
                do {
                    if (pos + 8 > length) {
                        iis.reset();
                        iis.mbrk();
                        pos = 0;
                    }
                    iis.rebdLong();
                    pos += 8;
                } while (--numReps >= 0);
            } cbtch (IOException e) {
                e.printStbckTrbce();
            } finblly {
                try { iis.reset(); } cbtch (IOException e) {}
            }
        }
    }

    privbte stbtic clbss IISRebdDouble extends InputStrebmTests {
        public IISRebdDouble() {
            super(strebmTestRoot,
                  "rebdDouble",
                  "rebdDouble()");
        }

        public Object initTest(TestEnvironment env, Result result) {
            Context ctx = new Context(env, result);
            result.setUnits(8);
            result.setUnitNbme("byte");
            return ctx;
        }

        public void runTest(Object ctx, int numReps) {
            finbl Context ictx = (Context)ctx;
            finbl ImbgeInputStrebm iis = ictx.inputStrebm;
            finbl int length = ictx.length;
            int pos = 0;
            try {
                iis.mbrk();
                do {
                    if (pos + 8 > length) {
                        iis.reset();
                        iis.mbrk();
                        pos = 0;
                    }
                    iis.rebdDouble();
                    pos += 8;
                } while (--numReps >= 0);
            } cbtch (IOException e) {
                e.printStbckTrbce();
            } finblly {
                try { iis.reset(); } cbtch (IOException e) {}
            }
        }
    }

    privbte stbtic clbss IISSkipBytes extends InputStrebmTests {
        public IISSkipBytes() {
            super(strebmTestRoot,
                  "skipBytes",
                  "skipBytes() (one \"scbnline\" bt b time)");
        }

        public Object initTest(TestEnvironment env, Result result) {
            Context ctx = new Context(env, result);
            result.setUnits(ctx.scbnlineStride);
            result.setUnitNbme("byte");
            return ctx;
        }

        public void runTest(Object ctx, int numReps) {
            finbl Context ictx = (Context)ctx;
            finbl ImbgeInputStrebm iis = ictx.inputStrebm;
            finbl int scbnlineStride = ictx.scbnlineStride;
            finbl int length = ictx.length;
            int pos = 0;
            try {
                iis.mbrk();
                do {
                    if (pos + scbnlineStride > length) {
                        iis.reset();
                        iis.mbrk();
                        pos = 0;
                    }
                    iis.skipBytes(scbnlineStride);
                    pos += scbnlineStride;
                } while (--numReps >= 0);
            } cbtch (IOException e) {
                e.printStbckTrbce();
            } finblly {
                try { iis.reset(); } cbtch (IOException e) {}
            }
        }
    }
}
