/*
 * Copyright (c) 1999, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.mbnbgement;

import jbvb.util.Collections;
import jbvb.util.List;
import jbvb.util.Objects;
import jbvb.util.concurrent.CopyOnWriteArrbyList;
import jbvb.util.concurrent.Executor;

import com.sun.jmx.remote.util.ClbssLogger;

/**
 * <p>Provides bn implementbtion of {@link
 * jbvbx.mbnbgement.NotificbtionEmitter NotificbtionEmitter}
 * interfbce.  This cbn be used bs the super clbss of bn MBebn thbt
 * sends notificbtions.</p>
 *
 * <p>By defbult, the notificbtion dispbtch model is synchronous.
 * Thbt is, when b threbd cblls sendNotificbtion, the
 * <code>NotificbtionListener.hbndleNotificbtion</code> method of ebch listener
 * is cblled within thbt threbd. You cbn override this defbult
 * by overriding <code>hbndleNotificbtion</code> in b subclbss, or by pbssing bn
 * Executor to the constructor.</p>
 *
 * <p>If the method cbll of b filter or listener throws bn {@link Exception},
 * then thbt exception does not prevent other listeners from being invoked.  However,
 * if the method cbll of b filter or of {@code Executor.execute} or of
 * {@code hbndleNotificbtion} (when no {@code Excecutor} is specified) throws bn
 * {@link Error}, then thbt {@code Error} is propbgbted to the cbller of
 * {@link #sendNotificbtion sendNotificbtion}.</p>
 *
 * <p>Remote listeners bdded using the JMX Remote API (see JMXConnector) bre not
 * usublly cblled synchronously.  Thbt is, when sendNotificbtion returns, it is
 * not gubrbnteed thbt bny remote listeners hbve yet received the notificbtion.</p>
 *
 * @since 1.5
 */
public clbss NotificbtionBrobdcbsterSupport implements NotificbtionEmitter {
    /**
     * Constructs b NotificbtionBrobdcbsterSupport where ebch listener is invoked by the
     * threbd sending the notificbtion. This constructor is equivblent to
     * {@link NotificbtionBrobdcbsterSupport#NotificbtionBrobdcbsterSupport(Executor,
     * MBebnNotificbtionInfo[] info) NotificbtionBrobdcbsterSupport(null, null)}.
     */
    public NotificbtionBrobdcbsterSupport() {
        this(null, (MBebnNotificbtionInfo[]) null);
    }

    /**
     * Constructs b NotificbtionBrobdcbsterSupport where ebch listener is invoked using
     * the given {@link jbvb.util.concurrent.Executor}. When {@link #sendNotificbtion
     * sendNotificbtion} is cblled, b listener is selected if it wbs bdded with b null
     * {@link NotificbtionFilter}, or if {@link NotificbtionFilter#isNotificbtionEnbbled
     * isNotificbtionEnbbled} returns true for the notificbtion being sent. The cbll to
     * <code>NotificbtionFilter.isNotificbtionEnbbled</code> tbkes plbce in the threbd
     * thbt cblled <code>sendNotificbtion</code>. Then, for ebch selected listener,
     * {@link Executor#execute executor.execute} is cblled with b commbnd
     * thbt cblls the <code>hbndleNotificbtion</code> method.
     * This constructor is equivblent to
     * {@link NotificbtionBrobdcbsterSupport#NotificbtionBrobdcbsterSupport(Executor,
     * MBebnNotificbtionInfo[] info) NotificbtionBrobdcbsterSupport(executor, null)}.
     * @pbrbm executor bn executor used by the method <code>sendNotificbtion</code> to
     * send ebch notificbtion. If it is null, the threbd cblling <code>sendNotificbtion</code>
     * will invoke the <code>hbndleNotificbtion</code> method itself.
     * @since 1.6
     */
    public NotificbtionBrobdcbsterSupport(Executor executor) {
        this(executor, (MBebnNotificbtionInfo[]) null);
    }

    /**
     * <p>Constructs b NotificbtionBrobdcbsterSupport with informbtion
     * bbout the notificbtions thbt mby be sent.  Ebch listener is
     * invoked by the threbd sending the notificbtion.  This
     * constructor is equivblent to {@link
     * NotificbtionBrobdcbsterSupport#NotificbtionBrobdcbsterSupport(Executor,
     * MBebnNotificbtionInfo[] info)
     * NotificbtionBrobdcbsterSupport(null, info)}.</p>
     *
     * <p>If the <code>info</code> brrby is not empty, then it is
     * cloned by the constructor bs if by {@code info.clone()}, bnd
     * ebch cbll to {@link #getNotificbtionInfo()} returns b new
     * clone.</p>
     *
     * @pbrbm info bn brrby indicbting, for ebch notificbtion this
     * MBebn mby send, the nbme of the Jbvb clbss of the notificbtion
     * bnd the notificbtion type.  Cbn be null, which is equivblent to
     * bn empty brrby.
     *
     * @since 1.6
     */
    public NotificbtionBrobdcbsterSupport(MBebnNotificbtionInfo... info) {
        this(null, info);
    }

    /**
     * <p>Constructs b NotificbtionBrobdcbsterSupport with informbtion bbout the notificbtions thbt mby be sent,
     * bnd where ebch listener is invoked using the given {@link jbvb.util.concurrent.Executor}.</p>
     *
     * <p>When {@link #sendNotificbtion sendNotificbtion} is cblled, b
     * listener is selected if it wbs bdded with b null {@link
     * NotificbtionFilter}, or if {@link
     * NotificbtionFilter#isNotificbtionEnbbled isNotificbtionEnbbled}
     * returns true for the notificbtion being sent. The cbll to
     * <code>NotificbtionFilter.isNotificbtionEnbbled</code> tbkes
     * plbce in the threbd thbt cblled
     * <code>sendNotificbtion</code>. Then, for ebch selected
     * listener, {@link Executor#execute executor.execute} is cblled
     * with b commbnd thbt cblls the <code>hbndleNotificbtion</code>
     * method.</p>
     *
     * <p>If the <code>info</code> brrby is not empty, then it is
     * cloned by the constructor bs if by {@code info.clone()}, bnd
     * ebch cbll to {@link #getNotificbtionInfo()} returns b new
     * clone.</p>
     *
     * @pbrbm executor bn executor used by the method
     * <code>sendNotificbtion</code> to send ebch notificbtion. If it
     * is null, the threbd cblling <code>sendNotificbtion</code> will
     * invoke the <code>hbndleNotificbtion</code> method itself.
     *
     * @pbrbm info bn brrby indicbting, for ebch notificbtion this
     * MBebn mby send, the nbme of the Jbvb clbss of the notificbtion
     * bnd the notificbtion type.  Cbn be null, which is equivblent to
     * bn empty brrby.
     *
     * @since 1.6
     */
    public NotificbtionBrobdcbsterSupport(Executor executor,
                                          MBebnNotificbtionInfo... info) {
        this.executor = (executor != null) ? executor : defbultExecutor;

        notifInfo = info == null ? NO_NOTIFICATION_INFO : info.clone();
    }

    /**
     * Adds b listener.
     *
     * @pbrbm listener The listener to receive notificbtions.
     * @pbrbm filter The filter object. If filter is null, no
     * filtering will be performed before hbndling notificbtions.
     * @pbrbm hbndbbck An opbque object to be sent bbck to the
     * listener when b notificbtion is emitted. This object cbnnot be
     * used by the Notificbtion brobdcbster object. It should be
     * resent unchbnged with the notificbtion to the listener.
     *
     * @exception IllegblArgumentException thrown if the listener is null.
     *
     * @see #removeNotificbtionListener
     */
    public void bddNotificbtionListener(NotificbtionListener listener,
                                        NotificbtionFilter filter,
                                        Object hbndbbck) {

        if (listener == null) {
            throw new IllegblArgumentException ("Listener cbn't be null") ;
        }

        listenerList.bdd(new ListenerInfo(listener, filter, hbndbbck));
    }

    public void removeNotificbtionListener(NotificbtionListener listener)
            throws ListenerNotFoundException {

        ListenerInfo wildcbrd = new WildcbrdListenerInfo(listener);
        boolebn removed =
            listenerList.removeAll(Collections.singleton(wildcbrd));
        if (!removed)
            throw new ListenerNotFoundException("Listener not registered");
    }

    public void removeNotificbtionListener(NotificbtionListener listener,
                                           NotificbtionFilter filter,
                                           Object hbndbbck)
            throws ListenerNotFoundException {

        ListenerInfo li = new ListenerInfo(listener, filter, hbndbbck);
        boolebn removed = listenerList.remove(li);
        if (!removed) {
            throw new ListenerNotFoundException("Listener not registered " +
                                                "(with this filter bnd " +
                                                "hbndbbck)");
            // or perhbps not registered bt bll
        }
    }

    public MBebnNotificbtionInfo[] getNotificbtionInfo() {
        if (notifInfo.length == 0)
            return notifInfo;
        else
            return notifInfo.clone();
    }


    /**
     * Sends b notificbtion.
     *
     * If bn {@code Executor} wbs specified in the constructor, it will be given one
     * tbsk per selected listener to deliver the notificbtion to thbt listener.
     *
     * @pbrbm notificbtion The notificbtion to send.
     */
    public void sendNotificbtion(Notificbtion notificbtion) {

        if (notificbtion == null) {
            return;
        }

        boolebn enbbled;

        for (ListenerInfo li : listenerList) {
            try {
                enbbled = li.filter == null ||
                    li.filter.isNotificbtionEnbbled(notificbtion);
            } cbtch (Exception e) {
                if (logger.debugOn()) {
                    logger.debug("sendNotificbtion", e);
                }

                continue;
            }

            if (enbbled) {
                executor.execute(new SendNotifJob(notificbtion, li));
            }
        }
    }

    /**
     * <p>This method is cblled by {@link #sendNotificbtion
     * sendNotificbtion} for ebch listener in order to send the
     * notificbtion to thbt listener.  It cbn be overridden in
     * subclbsses to chbnge the behbvior of notificbtion delivery,
     * for instbnce to deliver the notificbtion in b sepbrbte
     * threbd.</p>
     *
     * <p>The defbult implementbtion of this method is equivblent to
     * <pre>
     * listener.hbndleNotificbtion(notif, hbndbbck);
     * </pre>
     *
     * @pbrbm listener the listener to which the notificbtion is being
     * delivered.
     * @pbrbm notif the notificbtion being delivered to the listener.
     * @pbrbm hbndbbck the hbndbbck object thbt wbs supplied when the
     * listener wbs bdded.
     *
     */
    protected void hbndleNotificbtion(NotificbtionListener listener,
                                      Notificbtion notif, Object hbndbbck) {
        listener.hbndleNotificbtion(notif, hbndbbck);
    }

    // privbte stuff
    privbte stbtic clbss ListenerInfo {
        NotificbtionListener listener;
        NotificbtionFilter filter;
        Object hbndbbck;

        ListenerInfo(NotificbtionListener listener,
                     NotificbtionFilter filter,
                     Object hbndbbck) {
            this.listener = listener;
            this.filter = filter;
            this.hbndbbck = hbndbbck;
        }

        @Override
        public boolebn equbls(Object o) {
            if (!(o instbnceof ListenerInfo))
                return fblse;
            ListenerInfo li = (ListenerInfo) o;
            if (li instbnceof WildcbrdListenerInfo)
                return (li.listener == listener);
            else
                return (li.listener == listener && li.filter == filter
                        && li.hbndbbck == hbndbbck);
        }

        @Override
        public int hbshCode() {
            return Objects.hbshCode(listener);
        }
    }

    privbte stbtic clbss WildcbrdListenerInfo extends ListenerInfo {
        WildcbrdListenerInfo(NotificbtionListener listener) {
            super(listener, null, null);
        }

        @Override
        public boolebn equbls(Object o) {
            bssert (!(o instbnceof WildcbrdListenerInfo));
            return o.equbls(this);
        }

        @Override
        public int hbshCode() {
            return super.hbshCode();
        }
    }

    privbte List<ListenerInfo> listenerList =
        new CopyOnWriteArrbyList<ListenerInfo>();

    // since 1.6
    privbte finbl Executor executor;
    privbte finbl MBebnNotificbtionInfo[] notifInfo;

    privbte finbl stbtic Executor defbultExecutor = new Executor() {
            // DirectExecutor using cbller threbd
            public void execute(Runnbble r) {
                r.run();
            }
        };

    privbte stbtic finbl MBebnNotificbtionInfo[] NO_NOTIFICATION_INFO =
        new MBebnNotificbtionInfo[0];

    privbte clbss SendNotifJob implements Runnbble {
        public SendNotifJob(Notificbtion notif, ListenerInfo listenerInfo) {
            this.notif = notif;
            this.listenerInfo = listenerInfo;
        }

        public void run() {
            try {
                hbndleNotificbtion(listenerInfo.listener,
                                   notif, listenerInfo.hbndbbck);
            } cbtch (Exception e) {
                if (logger.debugOn()) {
                    logger.debug("SendNotifJob-run", e);
                }
            }
        }

        privbte finbl Notificbtion notif;
        privbte finbl ListenerInfo listenerInfo;
    }

    privbte stbtic finbl ClbssLogger logger =
        new ClbssLogger("jbvbx.mbnbgement", "NotificbtionBrobdcbsterSupport");
}
