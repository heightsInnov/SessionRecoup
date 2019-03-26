/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unionbankng.ussd.jobs;

import com.unionbankng.ussd.constants.Constants;
import com.unionbankng.ussd.db.DbImpl;
import com.unionbankng.ussd.dtos.TransactionDetailDto;
import com.unionbankng.ussd.dtos.TransactionSummaryDto;
import com.unionbankng.ussd.dtos.UssdDetails;

import java.util.List;
import java.util.ArrayList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author oawe
 */
//this class is not in use
public class TransactionProcessingJobV2 {

	static Logger log = LogManager.getLogger(TransactionProcessingJobV2.class);

	public static void processJob() {
		while (true) {
			List<Thread> threads = new ArrayList();
			DbImpl db = new DbImpl();
			{
				{
					try {
						List<UssdDetails> pendingDetailItems = db.getPendingTransactionsDetail();
						log.info("Completed fetching of pendingDetailItems  items from the database");
						if (pendingDetailItems == null || pendingDetailItems.isEmpty()) {
							log.info("Session file has no item to process...");
						} else {

							log.info("Processing session file items......item count: " + pendingDetailItems.size());

							int batchItemCounter = 0;
							for (UssdDetails item : pendingDetailItems) {
								batchItemCounter++;
								ItemPostingProcessor obj = new ItemPostingProcessor(item, batchItemCounter);
								Thread oThread = new Thread(obj);
								oThread.start();
//                                while (!oThread.isAlive()) {
//                                    
//                                }

								threads.add(oThread);

								if (batchItemCounter >= Constants.NUMBER_OF_THREADS) {
									log.info("Processing for session " + item.getPaymet_ref_no()+ " item count=" + batchItemCounter);
									break;
								}
							}
							threads.forEach((item) -> {
								try {
									if (item.isAlive()) {
										item.join(60000);
									}
								} catch (InterruptedException ex) {
									log.error(ex);
								}
							});
							log.info("Finished processing for session file item count=" + batchItemCounter);
							try {
								log.info("Performing cleanup..." + (1) + "s");
								Thread.sleep(1000);
							} catch (InterruptedException ex) {
								log.error(ex);
							}
						}
					} catch (Exception ex) {
						log.error(ex.getMessage());
					}
				}
			}
			try {
				//log("Performing cleanup..." + (Constants.DELAY) + "s",LogType.Info); no need to display this to user
				Thread.sleep(Constants.DELAY * 1000);
			} catch (InterruptedException ex) {
				log.error(ex.getMessage());
			}
		} //end while

	}
}
