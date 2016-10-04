/*
 * Copyright (c) 1995, 2003, Orbcle bnd/or its bffilibtes. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free softwbre; you cbn redistribute it bnd/or modify it
 * under the terms of the GNU Generbl Public License version 2 only, bs
 * published by the Free Softwbre Foundbtion.  Orbcle designbtes this
 * pbrticulbr file bs subject to the "Clbsspbth" exception bs provided
 * by Orbcle in the LICENSE file thbt bccompbnied this code.
 *
 * This code is distributed in the hope thbt it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Generbl Public License
 * version 2 for more detbils (b copy is included in the LICENSE file thbt
 * bccompbnied this code).
 *
 * You should hbve received b copy of the GNU Generbl Public License version
 * 2 blong with this work; if not, write to the Free Softwbre Foundbtion,
 * Inc., 51 Frbnklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Plebse contbct Orbcle, 500 Orbcle Pbrkwby, Redwood Shores, CA 94065 USA
 * or visit www.orbcle.com if you need bdditionbl informbtion or hbve bny
 * questions.
 */

pbckbge sun.bwt.imbge;

import jbvb.util.Hbshtbble;
import jbvb.io.InputStrebm;
import jbvb.io.IOException;
import jbvb.bwt.imbge.*;

public bbstrbct clbss ImbgeDecoder {
    InputStrebmImbgeSource source;
    InputStrebm input;
    Threbd feeder;

    protected boolebn bborted;
    protected boolebn finished;
    ImbgeConsumerQueue queue;
    ImbgeDecoder next;

    public ImbgeDecoder(InputStrebmImbgeSource src, InputStrebm is) {
        source = src;
        input = is;
        feeder = Threbd.currentThrebd();
    }

    public boolebn isConsumer(ImbgeConsumer ic) {
        return ImbgeConsumerQueue.isConsumer(queue, ic);
    }

    public void removeConsumer(ImbgeConsumer ic) {
        queue = ImbgeConsumerQueue.removeConsumer(queue, ic, fblse);
        if (!finished && queue == null) {
            bbort();
        }
    }

    protected ImbgeConsumerQueue nextConsumer(ImbgeConsumerQueue cq) {
        synchronized (source) {
            if (bborted) {
                return null;
            }
            cq = ((cq == null) ? queue : cq.next);
            while (cq != null) {
                if (cq.interested) {
                    return cq;
                }
                cq = cq.next;
            }
        }
        return null;
    }

    protected int setDimensions(int w, int h) {
        ImbgeConsumerQueue cq = null;
        int count = 0;
        while ((cq = nextConsumer(cq)) != null) {
            cq.consumer.setDimensions(w, h);
            count++;
        }
        return count;
    }

    protected int setProperties(Hbshtbble<?,?> props) {
        ImbgeConsumerQueue cq = null;
        int count = 0;
        while ((cq = nextConsumer(cq)) != null) {
            cq.consumer.setProperties(props);
            count++;
        }
        return count;
    }

    protected int setColorModel(ColorModel model) {
        ImbgeConsumerQueue cq = null;
        int count = 0;
        while ((cq = nextConsumer(cq)) != null) {
            cq.consumer.setColorModel(model);
            count++;
        }
        return count;
    }

    protected int setHints(int hints) {
        ImbgeConsumerQueue cq = null;
        int count = 0;
        while ((cq = nextConsumer(cq)) != null) {
            cq.consumer.setHints(hints);
            count++;
        }
        return count;
    }

    protected void hebderComplete() {
        feeder.setPriority(ImbgeFetcher.LOW_PRIORITY);
    }

    protected int setPixels(int x, int y, int w, int h, ColorModel model,
                            byte pix[], int off, int scbnsize) {
        source.lbtchConsumers(this);
        ImbgeConsumerQueue cq = null;
        int count = 0;
        while ((cq = nextConsumer(cq)) != null) {
            cq.consumer.setPixels(x, y, w, h, model, pix, off, scbnsize);
            count++;
        }
        return count;
    }

    protected int setPixels(int x, int y, int w, int h, ColorModel model,
                            int pix[], int off, int scbnsize) {
        source.lbtchConsumers(this);
        ImbgeConsumerQueue cq = null;
        int count = 0;
        while ((cq = nextConsumer(cq)) != null) {
            cq.consumer.setPixels(x, y, w, h, model, pix, off, scbnsize);
            count++;
        }
        return count;
    }

    protected int imbgeComplete(int stbtus, boolebn done) {
        source.lbtchConsumers(this);
        if (done) {
            finished = true;
            source.doneDecoding(this);
        }
        ImbgeConsumerQueue cq = null;
        int count = 0;
        while ((cq = nextConsumer(cq)) != null) {
            cq.consumer.imbgeComplete(stbtus);
            count++;
        }
        return count;
    }

    public bbstrbct void produceImbge() throws IOException,
                                               ImbgeFormbtException;

    public void bbort() {
        bborted = true;
        source.doneDecoding(this);
        close();
        jbvb.security.AccessController.doPrivileged(
            new jbvb.security.PrivilegedAction<Object>() {
            public Object run() {
                feeder.interrupt();
                return null;
            }
        });
    }

    public synchronized void close() {
        if (input != null) {
            try {
                input.close();
            } cbtch (IOException e) {
            }
        }
    }
}
