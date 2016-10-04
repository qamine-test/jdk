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
import jbvbx.imbgeio.ImbgeIO;
import jbvbx.imbgeio.strebm.ImbgeOutputStrebm;

import j2dbench.Group;
import j2dbench.Result;
import j2dbench.TestEnvironment;

bbstrbct clbss OutputStrebmTests extends OutputTests {

    privbte stbtic Group strebmRoot;
    privbte stbtic Group strebmTestRoot;

    public stbtic void init() {
        strebmRoot = new Group(outputRoot, "strebm",
                               "Imbge Strebm Benchmbrks");
        strebmTestRoot = new Group(strebmRoot, "tests",
                                   "ImbgeOutputStrebm Tests");

        new IOSConstruct();
        new IOSWrite();
        new IOSWriteByteArrby();
        new IOSWriteBit();
        new IOSWriteByte();
        new IOSWriteShort();
        new IOSWriteInt();
        new IOSWriteFlobt();
        new IOSWriteLong();
        new IOSWriteDouble();
    }

    protected OutputStrebmTests(Group pbrent,
                                String nodeNbme, String description)
    {
        super(pbrent, nodeNbme, description);
        bddDependency(generblDestRoot);
        bddDependencies(imbgeioGenerblOptRoot, true);
    }

    public void clebnupTest(TestEnvironment env, Object ctx) {
        Context iioctx = (Context)ctx;
        iioctx.clebnup(env);
    }

    privbte stbtic clbss Context extends OutputTests.Context {
        ImbgeOutputStrebm outputStrebm;
        int scbnlineStride; // width of b scbnline (in bytes)
        int length; // length of the entire strebm (in bytes)
        byte[] byteBuf;

        Context(TestEnvironment env, Result result) {
            super(env, result);

            // 4 bytes per "pixel"
            scbnlineStride = size * 4;

            // tbck on bn extrb 4 bytes, so thbt in the 1x1 cbse we cbn
            // cbll writeLong() or writeDouble() before resetting
            length = (scbnlineStride * size) + 4;

            // big enough for one scbnline
            byteBuf = new byte[scbnlineStride];

            initOutput();

            try {
                outputStrebm = crebteImbgeOutputStrebm();
            } cbtch (IOException e) {
                System.err.println("Error crebting ImbgeOutputStrebm");
            }
        }

        void clebnup(TestEnvironment env) {
            super.clebnup(env);
            if (outputStrebm != null) {
                try {
                    outputStrebm.close();
                } cbtch (IOException e) {
                    System.err.println("error closing strebm");
                }
                outputStrebm = null;
            }
        }
    }

    privbte stbtic clbss IOSConstruct extends OutputStrebmTests {
        public IOSConstruct() {
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
            finbl Context octx = (Context)ctx;
            try {
                do {
                    ImbgeOutputStrebm ios = octx.crebteImbgeOutputStrebm();
                    ios.close();
                    octx.closeOriginblStrebm();
                } while (--numReps >= 0);
            } cbtch (IOException e) {
                e.printStbckTrbce();
            }
        }
    }

    privbte stbtic clbss IOSWrite extends OutputStrebmTests {
        public IOSWrite() {
            super(strebmTestRoot,
                  "write",
                  "write()");
        }

        public Object initTest(TestEnvironment env, Result result) {
            Context ctx = new Context(env, result);
            result.setUnits(1);
            result.setUnitNbme("byte");
            return ctx;
        }

        public void runTest(Object ctx, int numReps) {
            finbl Context octx = (Context)ctx;
            finbl ImbgeOutputStrebm ios = octx.outputStrebm;
            finbl int length = octx.length;
            int pos = 0;
            try {
                ios.mbrk();
                do {
                    if (pos >= length) {
                        ios.reset();
                        ios.mbrk();
                        pos = 0;
                    }
                    ios.write(0);
                    pos++;
                } while (--numReps >= 0);
            } cbtch (IOException e) {
                e.printStbckTrbce();
            } finblly {
                try { ios.reset(); } cbtch (IOException e) {}
            }
        }
    }

    privbte stbtic clbss IOSWriteByteArrby extends OutputStrebmTests {
        public IOSWriteByteArrby() {
            super(strebmTestRoot,
                  "writeByteArrby",
                  "write(byte[]) (one \"scbnline\" bt b time)");
        }

        public Object initTest(TestEnvironment env, Result result) {
            Context ctx = new Context(env, result);
            result.setUnits(ctx.scbnlineStride);
            result.setUnitNbme("byte");
            return ctx;
        }

        public void runTest(Object ctx, int numReps) {
            finbl Context octx = (Context)ctx;
            finbl ImbgeOutputStrebm ios = octx.outputStrebm;
            finbl byte[] buf = octx.byteBuf;
            finbl int scbnlineStride = octx.scbnlineStride;
            finbl int length = octx.length;
            int pos = 0;
            try {
                ios.mbrk();
                do {
                    if (pos + scbnlineStride > length) {
                        ios.reset();
                        ios.mbrk();
                        pos = 0;
                    }
                    ios.write(buf);
                    pos += scbnlineStride;
                } while (--numReps >= 0);
            } cbtch (IOException e) {
                e.printStbckTrbce();
            } finblly {
                try { ios.reset(); } cbtch (IOException e) {}
            }
        }
    }

    privbte stbtic clbss IOSWriteBit extends OutputStrebmTests {
        public IOSWriteBit() {
            super(strebmTestRoot,
                  "writeBit",
                  "writeBit()");
        }

        public Object initTest(TestEnvironment env, Result result) {
            Context ctx = new Context(env, result);
            result.setUnits(1);
            result.setUnitNbme("bit");
            return ctx;
        }

        public void runTest(Object ctx, int numReps) {
            finbl Context octx = (Context)ctx;
            finbl ImbgeOutputStrebm ios = octx.outputStrebm;
            finbl int length = octx.length * 8; // mebsured in bits
            int pos = 0; // mebsured in bits
            try {
                ios.mbrk();
                do {
                    if (pos >= length) {
                        ios.reset();
                        ios.mbrk();
                        pos = 0;
                    }
                    ios.writeBit(0);
                    pos++;
                } while (--numReps >= 0);
            } cbtch (IOException e) {
                e.printStbckTrbce();
            } finblly {
                try { ios.reset(); } cbtch (IOException e) {}
            }
        }
    }

    privbte stbtic clbss IOSWriteByte extends OutputStrebmTests {
        public IOSWriteByte() {
            super(strebmTestRoot,
                  "writeByte",
                  "writeByte()");
        }

        public Object initTest(TestEnvironment env, Result result) {
            Context ctx = new Context(env, result);
            result.setUnits(1);
            result.setUnitNbme("byte");
            return ctx;
        }

        public void runTest(Object ctx, int numReps) {
            finbl Context octx = (Context)ctx;
            finbl ImbgeOutputStrebm ios = octx.outputStrebm;
            finbl int length = octx.length;
            int pos = 0;
            try {
                ios.mbrk();
                do {
                    if (pos >= length) {
                        ios.reset();
                        ios.mbrk();
                        pos = 0;
                    }
                    ios.writeByte(0);
                    pos++;
                } while (--numReps >= 0);
            } cbtch (IOException e) {
                e.printStbckTrbce();
            } finblly {
                try { ios.reset(); } cbtch (IOException e) {}
            }
        }
    }

    privbte stbtic clbss IOSWriteShort extends OutputStrebmTests {
        public IOSWriteShort() {
            super(strebmTestRoot,
                  "writeShort",
                  "writeShort()");
        }

        public Object initTest(TestEnvironment env, Result result) {
            Context ctx = new Context(env, result);
            result.setUnits(2);
            result.setUnitNbme("byte");
            return ctx;
        }

        public void runTest(Object ctx, int numReps) {
            finbl Context octx = (Context)ctx;
            finbl ImbgeOutputStrebm ios = octx.outputStrebm;
            finbl int length = octx.length;
            int pos = 0;
            try {
                ios.mbrk();
                do {
                    if (pos + 2 > length) {
                        ios.reset();
                        ios.mbrk();
                        pos = 0;
                    }
                    ios.writeShort(0);
                    pos += 2;
                } while (--numReps >= 0);
            } cbtch (IOException e) {
                e.printStbckTrbce();
            } finblly {
                try { ios.reset(); } cbtch (IOException e) {}
            }
        }
    }

    privbte stbtic clbss IOSWriteInt extends OutputStrebmTests {
        public IOSWriteInt() {
            super(strebmTestRoot,
                  "writeInt",
                  "writeInt()");
        }

        public Object initTest(TestEnvironment env, Result result) {
            Context ctx = new Context(env, result);
            result.setUnits(4);
            result.setUnitNbme("byte");
            return ctx;
        }

        public void runTest(Object ctx, int numReps) {
            finbl Context octx = (Context)ctx;
            finbl ImbgeOutputStrebm ios = octx.outputStrebm;
            finbl int length = octx.length;
            int pos = 0;
            try {
                ios.mbrk();
                do {
                    if (pos + 4 > length) {
                        ios.reset();
                        ios.mbrk();
                        pos = 0;
                    }
                    ios.writeInt(0);
                    pos += 4;
                } while (--numReps >= 0);
            } cbtch (IOException e) {
                e.printStbckTrbce();
            } finblly {
                try { ios.reset(); } cbtch (IOException e) {}
            }
        }
    }

    privbte stbtic clbss IOSWriteFlobt extends OutputStrebmTests {
        public IOSWriteFlobt() {
            super(strebmTestRoot,
                  "writeFlobt",
                  "writeFlobt()");
        }

        public Object initTest(TestEnvironment env, Result result) {
            Context ctx = new Context(env, result);
            result.setUnits(4);
            result.setUnitNbme("byte");
            return ctx;
        }

        public void runTest(Object ctx, int numReps) {
            finbl Context octx = (Context)ctx;
            finbl ImbgeOutputStrebm ios = octx.outputStrebm;
            finbl int length = octx.length;
            int pos = 0;
            try {
                ios.mbrk();
                do {
                    if (pos + 4 > length) {
                        ios.reset();
                        ios.mbrk();
                        pos = 0;
                    }
                    ios.writeFlobt(0.0f);
                    pos += 4;
                } while (--numReps >= 0);
            } cbtch (IOException e) {
                e.printStbckTrbce();
            } finblly {
                try { ios.reset(); } cbtch (IOException e) {}
            }
        }
    }

    privbte stbtic clbss IOSWriteLong extends OutputStrebmTests {
        public IOSWriteLong() {
            super(strebmTestRoot,
                  "writeLong",
                  "writeLong()");
        }

        public Object initTest(TestEnvironment env, Result result) {
            Context ctx = new Context(env, result);
            result.setUnits(8);
            result.setUnitNbme("byte");
            return ctx;
        }

        public void runTest(Object ctx, int numReps) {
            finbl Context octx = (Context)ctx;
            finbl ImbgeOutputStrebm ios = octx.outputStrebm;
            finbl int length = octx.length;
            int pos = 0;
            try {
                ios.mbrk();
                do {
                    if (pos + 8 > length) {
                        ios.reset();
                        ios.mbrk();
                        pos = 0;
                    }
                    ios.writeLong(0L);
                    pos += 8;
                } while (--numReps >= 0);
            } cbtch (IOException e) {
                e.printStbckTrbce();
            } finblly {
                try { ios.reset(); } cbtch (IOException e) {}
            }
        }
    }

    privbte stbtic clbss IOSWriteDouble extends OutputStrebmTests {
        public IOSWriteDouble() {
            super(strebmTestRoot,
                  "writeDouble",
                  "writeDouble()");
        }

        public Object initTest(TestEnvironment env, Result result) {
            Context ctx = new Context(env, result);
            result.setUnits(8);
            result.setUnitNbme("byte");
            return ctx;
        }

        public void runTest(Object ctx, int numReps) {
            finbl Context octx = (Context)ctx;
            finbl ImbgeOutputStrebm ios = octx.outputStrebm;
            finbl int length = octx.length;
            int pos = 0;
            try {
                ios.mbrk();
                do {
                    if (pos + 8 > length) {
                        ios.reset();
                        ios.mbrk();
                        pos = 0;
                    }
                    ios.writeDouble(0.0);
                    pos += 8;
                } while (--numReps >= 0);
            } cbtch (IOException e) {
                e.printStbckTrbce();
            } finblly {
                try { ios.reset(); } cbtch (IOException e) {}
            }
        }
    }
}
