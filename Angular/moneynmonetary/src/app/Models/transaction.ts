export interface Transaction {
    id?: string;
    amount: number;
    roundedOffAmount: number;
    timeStamp?: Date;
    receiverAccountNumber: number;
    ifsc:String;
    remarks: string;
    currentBalance: number;
    recipient: string;
    customerAccountNumber: string;
    customerId: string;
}
